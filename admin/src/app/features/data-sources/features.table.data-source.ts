import { MatPaginator, MatSort, PageEvent, Sort } from '@angular/material';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { Features, FeaturesState } from '../store/features.state';
import { Store } from '@ngrx/store';
import { Feature } from '../models/feature.model';
import { Subscription } from 'rxjs/Subscription';
import { LoadFeatures } from '../store/features.actions';
import { SortDefinition } from '../../shared/tables/table-request.payload';
import { DataSource } from '@angular/cdk/table';
import { delay, startWith } from 'rxjs/operators';
import { combineLatest } from 'rxjs/observable/combineLatest';

export class FeaturesTableDataSource extends DataSource<Feature> {
  private _defaultSort: Array<SortDefinition> = [{ property: 'name', direction: 'asc' }];
  private _stateSubscription$: Subscription;
  private _paginatorAndSortSubscription$: Subscription;
  private _data$: BehaviorSubject<Feature[]> = new BehaviorSubject([]);
  private _loading$: BehaviorSubject<boolean> = new BehaviorSubject(false);

  private _paginator: MatPaginator;
  private _sort: MatSort;

  set paginator(value: MatPaginator) {
    this._paginator = value;
  }

  set sort(value: MatSort) {
    this._sort = value;
  }

  get loading$() {
    return this._loading$.asObservable();
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
  }

  connect(): BehaviorSubject<Feature[]> {
    this._stateSubscription$ = this.store.select('features').subscribe((state: Features) => {
      this._data$.next(state.content);
      if (this._paginator) {
        this._paginator.length = state.totalElements;
      }
      // TODO not working spinner because it is called immediately with initial state
      this._loading$.next(false);
    });
    return this._data$;
  }

  disconnect(): void {
    this._stateSubscription$.unsubscribe();
    this._paginatorAndSortSubscription$.unsubscribe();
    this._data$.complete();
    this._loading$.complete();
  }

  private getFeatures(): void {
    this._loading$.next(true);
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
