import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { SelectionModel } from '@angular/cdk/collections';
import { FeaturesTableDataSource } from '../../data-sources/features.table.data-source';
import { config } from '../../../../config/config';
import { MatPaginator, MatSort } from '@angular/material';
import { Store } from '@ngrx/store';
import { FeaturesState } from '../../store/features.state';
import { Feature } from '../../models/feature.model';

@Component({
  selector: 'app-features-list',
  templateUrl: './features-list.component.html',
  styleUrls: ['./features-list.component.scss']
})
export class FeaturesListComponent implements OnInit, OnDestroy {

  displayedColumns = ['select', 'id', 'name', 'version'];
  dataSource: FeaturesTableDataSource;

  selection = new SelectionModel<Feature>(true, []);

  defaultPageSize: Array<number> = config.tables.defaultPageSize;
  defaultPageSizeOptions: Array<number> = config.tables.defaultPageSizeOptions;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private store: Store<FeaturesState>) {
  }

  ngOnInit(): void {
    this.dataSource = new FeaturesTableDataSource(this.store);
    this.dataSource.ngOnInit();
  }

  /** Whether the number of selected elements matches the total number of rows. */
  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected() ?
      this.selection.clear() :
      this.dataSource.data.forEach(row => this.selection.select(row));
  }

  /**
   * Set the sort and paginator after the view init since this component will
   * be able to query its view for the initialized sort and paginator.
   */
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  ngOnDestroy(): void {
    this.dataSource.ngOnDestroy();
  }
}
