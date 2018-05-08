import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { LoadProjects } from '../../../projects/store/projects.actions';
import { getProjectsState, Projects, ProjectsState } from '../../../projects/store/projects.state';
import { Observable } from 'rxjs/Observable';
import { FeaturesState, getSideNavOpened } from '../../store/features.state';
import { SelectEnvironment, SelectProject, ToggleFeaturesSideNav } from '../../store/features.actions';
import { Select } from 'ngrx-actions';
import { Project } from '../../../projects/models/project.model';
import { Environment } from '../../../environments/models/environment.model';
import { Go } from '../../../store/app.actions';
import { ActivatedRoute } from '@angular/router';
import { AppState } from '../../../store/app.state';

@Component({
  selector: 'app-features',
  templateUrl: './features.component.html',
  styleUrls: ['./features.component.scss']
})
export class FeaturesComponent implements OnInit {
  @Select(getProjectsState) projects$: Observable<Projects>;
  @Select(getSideNavOpened) sideNavOpened$: Observable<boolean>;
  selectedProject: Project;
  selectedEnvironment: Environment;

  constructor(
    private featuresStore: Store<FeaturesState>,
    private projectsStore: Store<ProjectsState>,
    private appStore: Store<AppState>,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.projectsStore.dispatch(new LoadProjects());
  }

  toggleSideNav(): void {
    this.featuresStore.dispatch(new ToggleFeaturesSideNav());
  }

  selectProject(project: Project) {
    this.featuresStore.dispatch(new SelectProject(project));
    this.selectedProject = project;
  }

  selectEnvironment(environment: Environment) {
    this.featuresStore.dispatch(new SelectEnvironment(environment));
    this.selectedEnvironment = environment;
    this.appStore.dispatch(
      new Go({
        path: [
          'features',
          this.selectedProject.name.toLowerCase(),
          this.selectedEnvironment.name.toLowerCase()
        ] /*,
        extras: { relativeTo: this.route }*/
      })
    );
  }
}
