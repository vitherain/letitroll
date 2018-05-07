import { MatPaginator, MatSort, PageEvent, Sort } from '@angular/material';
import {
  FeaturesState,
  getFeaturesContent,
  getFeaturesLoading,
  getFeaturesTotalElements
} from '../store/features.state';
import { Store } from '@ngrx/store';
import { Feature } from '../models/feature.model';
import { Subscription } from 'rxjs/Subscription';
import { LoadFeatures } from '../store/features.actions';
import { SortDefinition } from '../../shared/tables/table-request.payload';
import { DataSource } from '@angular/cdk/table';
import { delay, startWith } from 'rxjs/operators';
import { combineLatest } from 'rxjs/observable/combineLatest';
import { Observable } from 'rxjs/Observable';
import { Select } from 'ngrx-actions';

export class FeaturesTableDataSource extends DataSource<Feature> {
  private _defaultSort: Array<SortDefinition> = [{ property: 'name', direction: 'asc' }];
  private _featuresSubscription$: Subscription;
  private _paginatorAndSortSubscription$: Subscription;

  @Select(getFeaturesContent) private _data$: Observable<Array<Feature>>;
  @Select(getFeaturesLoading) private _loading$: Observable<boolean>;
  @Select(getFeaturesTotalElements) private _totalElements$: Observable<number>;

  private _paginator: MatPaginator;
  private _sort: MatSort;

  set paginator(value: MatPaginator) {
    this._paginator = value;
  }

  set sort(value: MatSort) {
    this._sort = value;
  }

  get loading$() {
    return this._loading$;
  }

  constructor(private store: Store<FeaturesState>) {
    super();
  }

  initialize(): void {
    const defaultPageEvent: PageEvent = { pageIndex: 0, pageSize: this._paginator.pageSize, length: 0 };
    const defaultSort: Sort = { active: 'name', direction: 'asc' };
    const paginator$ = this._paginator.page.pipe(startWith(defaultPageEvent));
    const sort$ = this._sort.sortChange.pipe(startWith(defaultSort));
    this._paginatorAndSortSubscription$ = combineLatest(paginator$, sort$)
      .pipe(delay(0))
      .subscribe(() => this.getFeatures());
    this._featuresSubscription$ = this._totalElements$.subscribe((totalElements: number) => {
      if (this._paginator) {
        this._paginator.length = totalElements;
      }
    });
  }

  connect(): Observable<Array<Feature>> {
    return this._data$;
  }

  disconnect(): void {
    this._featuresSubscription$.unsubscribe();
    this._paginatorAndSortSubscription$.unsubscribe();
  }

  private getFeatures(): void {
    if (this.store) {
      const sortDefined: boolean = !!this._sort && !!this._sort.active && !!this._sort.direction;
      const tableRequest = {
        page: this._paginator ? this._paginator.pageIndex : 0,
        size: this._paginator ? this._paginator.pageSize : 2000,
        sort: sortDefined ? [{ property: this._sort.active, direction: this._sort.direction }] : this._defaultSort
      };
      this.store.dispatch(new LoadFeatures(tableRequest));
    }
  }
}
