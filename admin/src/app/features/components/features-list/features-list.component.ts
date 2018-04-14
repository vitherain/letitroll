import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FeaturesTableDataSource } from '../../data-sources/features.table.data-source';
import { config } from '../../../../config/config';
import { MatPaginator, MatSort } from '@angular/material';
import { Store } from '@ngrx/store';
import { FeaturesState } from '../../store/features.state';

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

  constructor(private store: Store<FeaturesState>) {}

  ngOnInit(): void {
    this.dataSource = new FeaturesTableDataSource(this.store);
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.dataSource.initialize();
  }
}
