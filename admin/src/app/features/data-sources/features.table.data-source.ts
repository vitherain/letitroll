import { MatPaginator, MatSort } from '@angular/material';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { FeaturesState, State } from '../store/features.state';
import { Store } from '@ngrx/store';
import { Feature } from '../models/feature.model';
import { Subscription } from 'rxjs/Subscription';
import { ApiGetFeatures } from '../store/features.actions';
import { SortDefinition } from '../../shared/tables/table-request.payload';
import { DataSource } from '@angular/cdk/table';

export class FeaturesTableDataSource extends DataSource<Feature> {
  private defaultSort: Array<SortDefinition> = [{ property: 'name', direction: 'asc' }];
  private stateSubscription$: Subscription;
  private data$: BehaviorSubject<Feature[]> = new BehaviorSubject([]);

  private _paginator: MatPaginator;
  private _sort: MatSort;

  set paginator(value: MatPaginator) {
    this._paginator = value;
  }

  set sort(value: MatSort) {
    this._sort = value;
  }

  constructor(private store: Store<FeaturesState>) {
    super();
  }

  initialize(): void {}

  connect(): BehaviorSubject<Feature[]> {
    this.stateSubscription$ = this.store.select('features').subscribe((state: State) => {
      this.data$.next(state.content);
      if (this._paginator) {
        this._paginator.length = state.totalElements;
      }
    });

    this.getFeatures();
    return this.data$;
  }

  disconnect(): void {
    this.stateSubscription$.unsubscribe();
  }

  private getFeatures(): void {
    if (this.store) {
      const sortDefined: boolean = !!this._sort && !!this._sort.active && !!this._sort.direction;
      const tableRequest = {
        page: this._paginator ? this._paginator.pageIndex : 0,
        size: this._paginator ? this._paginator.pageSize : 2000,
        sort: sortDefined ? [{ property: this._sort.active, direction: this._sort.direction }] : this.defaultSort
      };
      this.store.dispatch(new ApiGetFeatures(tableRequest));
    }
  }
}
