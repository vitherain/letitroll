import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FeaturesTableDataSource } from '../../data-sources/features.table.data-source';
import { config } from '../../../../config/config';
import { MatDialog, MatPaginator, MatSort } from '@angular/material';
import { Store } from '@ngrx/store';
import { FeaturesState } from '../../store/features.state';
import { DeleteFeatureDialogComponent } from '../delete-feature-dialog/delete-feature-dialog.component';
import { Feature } from '../../models/feature.model';

@Component({
  selector: 'app-features-list',
  templateUrl: './features-list.component.html',
  styleUrls: ['./features-list.component.scss']
})
export class FeaturesListComponent implements OnInit, AfterViewInit {
  displayedColumns = ['name', 'added', 'key', 'targeting', 'actions'];
  dataSource: FeaturesTableDataSource;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  defaultPageSize: Array<number> = config.tables.defaultPageSize;
  defaultPageSizeOptions: Array<number> = config.tables.defaultPageSizeOptions;
  defaultTimeFormat: string = config.defaultTimeFormat;

  constructor(private store: Store<FeaturesState>, private dialog: MatDialog) {}

  ngOnInit(): void {
    this.dataSource = new FeaturesTableDataSource(this.store);
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.dataSource.initialize();
  }

  openDeleteDialog(feature: Feature): void {
    const dialogRef = this.dialog.open(DeleteFeatureDialogComponent, {
      data: { feature: feature }
    });

    dialogRef.afterClosed().subscribe((deleteIt: boolean) => {
      console.log('The dialog was closed', deleteIt);
    });
  }
}
