import { AfterViewInit, Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { FeaturesTableDataSource } from '../../data-sources/features.table.data-source';
import { config } from '../../../../config/config';
import { MatDialog, MatPaginator, MatSort } from '@angular/material';
import { Store } from '@ngrx/store';
import { FeaturesState } from '../../store/features.state';
import { DeleteFeatureDialogComponent } from '../delete-feature-dialog/delete-feature-dialog.component';
import { Feature } from '../../models/feature.model';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { getProjectsEntities } from '../../../projects/store/projects.state';
import { Observable } from 'rxjs/Observable';
import { Select } from 'ngrx-actions';
import { combineLatest } from 'rxjs/observable/combineLatest';
import { Project } from '../../../projects/models/project.model';
import { Environment } from '../../../environments/models/environment.model';
import { findEnvironment, findProject } from '../../utils/features-utils';
import { SelectEnvironment, SelectProject, ToggleFeaturesSideNav } from '../../store/features.actions';

@Component({
  selector: 'app-features-list',
  templateUrl: './features-list.component.html',
  styleUrls: ['./features-list.component.scss']
})
export class FeaturesListComponent implements OnInit, AfterViewInit, OnDestroy {
  @Select(getProjectsEntities) projects$: Observable<Array<Project>>;

  displayedColumns = ['name', 'added', 'key', 'targeting', 'actions'];
  dataSource: FeaturesTableDataSource;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  defaultPageSize: Array<number> = config.tables.defaultPageSize;
  defaultPageSizeOptions: Array<number> = config.tables.defaultPageSizeOptions;
  defaultTimeFormat: string = config.defaultTimeFormat;

  private initSubscription: Subscription;

  constructor(private store: Store<FeaturesState>, private dialog: MatDialog, private activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.dataSource = new FeaturesTableDataSource(this.store);
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;

    this.initSubscription = combineLatest(this.activatedRoute.params, this.projects$).subscribe(
      ([params, projects]) => {
        if (projects.length) {
          const projectName = params['projectName'];
          const environmentName = params['environmentName'];
          const project: Project = findProject(projectName, projects);
          const environment: Environment = findEnvironment(environmentName, project.environments);
          this.dataSource.environmentId = environment.id;
          this.dataSource.initialize();
          this.store.dispatch(new ToggleFeaturesSideNav()); // close sidenav
          this.store.dispatch(new SelectProject(project));
          this.store.dispatch(new SelectEnvironment(environment));
        }
      }
    );
  }

  openDeleteDialog(feature: Feature): void {
    const dialogRef = this.dialog.open(DeleteFeatureDialogComponent, {
      data: { feature: feature }
    });

    dialogRef.afterClosed().subscribe((deleteIt: boolean) => {
      console.log('The dialog was closed', deleteIt);
    });
  }

  ngOnDestroy(): void {
    this.initSubscription.unsubscribe();
  }
}
