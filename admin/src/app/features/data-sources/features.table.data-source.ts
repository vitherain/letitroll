import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { FeaturesState } from '../store/features.state';
import { Store } from '@ngrx/store';
import { Feature } from '../models/feature.model';
import { Subscription } from 'rxjs/Subscription';
import { ApiGetFeatures } from '../store/features.actions';

export class FeaturesTableDataSource extends MatTableDataSource<Feature> {

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
    this.stateSubscription$ = this.store.select('features').subscribe(data => {
      this.data$.next(data.features);
    });

    this.getFeatures();
    return this.data$;
  }

  disconnect(): void {
    super.disconnect();
    this.stateSubscription$.unsubscribe();
  }

  getFeatures(): void {
    const sort: MatSort = this.sort;
    const paginator: MatPaginator = this.paginator;
    this.store.dispatch(new ApiGetFeatures({}));
  }
}
