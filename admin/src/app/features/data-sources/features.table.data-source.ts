import { MatTableDataSource } from '@angular/material';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { FeaturesState, State } from '../store/features.state';
import { Store } from '@ngrx/store';
import { Feature } from '../models/feature.model';
import { Subscription } from 'rxjs/Subscription';
import { ApiGetFeatures } from '../store/features.actions';
import { SortDefinition } from '../../shared/tables/table-request.payload';

export class FeaturesTableDataSource extends MatTableDataSource<Feature> {

  defaultSort: Array<SortDefinition> = [{ property: 'name', direction: 'asc' }];
  stateSubscription$: Subscription;
  data$: BehaviorSubject<Feature[]> = new BehaviorSubject([]);

  constructor(private store: Store<FeaturesState>) {
    super([]);
  }

  _orderData(data: Feature[]): Feature[] {
    this.getFeatures();
    return super._orderData(data);
  }

  _pageData(data: Feature[]): Feature[] {
    this.getFeatures();
    return super._pageData(data);
  }

  connect(): BehaviorSubject<Feature[]> {
    this.stateSubscription$ = this.store.select('features').subscribe((state: State) => {
      this.data$.next(state.content);
      if (this.paginator) {
        this.paginator.length = state.totalElements;
      }
    });

    this.getFeatures();
    return this.data$;
  }

  disconnect(): void {
    super.disconnect();
    this.stateSubscription$.unsubscribe();
  }

  getFeatures(): void {
    if (this.store) {
      const sortDefined: boolean = this.sort && this.sort.active && this.sort.direction;
      const tableRequest = {
        page: this.paginator ? this.paginator.pageIndex : 0,
        size: this.paginator ? this.paginator.pageSize : 2000,
        sort: sortDefined ? [{ property: this.sort.active, direction: this.sort.direction }] : this.defaultSort
      };
      this.store.dispatch(new ApiGetFeatures(tableRequest));
    }
  }
}
