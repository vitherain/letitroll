import { MatTableDataSource } from '@angular/material';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { FeaturesState } from '../store/features.state';
import { Store } from '@ngrx/store';
import { Feature } from '../models/feature.model';
import { OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { ApiGetFeatures } from '../store/features.actions';

export class FeaturesTableDataSource extends MatTableDataSource<Feature> implements OnInit, OnDestroy {

  stateSubscription$: Subscription;
  data$: BehaviorSubject<Feature[]> = new BehaviorSubject([]);

  constructor(private store: Store<FeaturesState>) {
    super();
  }

  ngOnInit(): void {
    this.stateSubscription$ = this.store.select('features').subscribe((data: Feature[]) => {
      this.data$.next(data);
    });
    this.store.dispatch(new ApiGetFeatures({}));
  }

  _updateChangeSubscription(): void {
    console.log('called _updateChangeSubscription');
    super._updateChangeSubscription();
  }

  _filterData(data: Feature[]): Feature[] {
    console.log('called _filterData');
    return super._filterData(data);
  }

  _orderData(data: Feature[]): Feature[] {
    console.log('called _orderData');
    return super._orderData(data);
  }

  _pageData(data: Feature[]): Feature[] {
    console.log('called _pageData');
    return super._pageData(data);
  }

  _updatePaginator(filteredDataLength: number): void {
    console.log('called _updatePaginator');
    super._updatePaginator(filteredDataLength);
  }

  connect(): BehaviorSubject<Feature[]> {
    return this.data$;
  }

  disconnect(): void {
    console.log('called disconnect');
    super.disconnect();
  }

  ngOnDestroy(): void {
    this.stateSubscription$.unsubscribe();
  }
}
