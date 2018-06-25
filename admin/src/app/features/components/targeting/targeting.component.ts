import { Component, OnDestroy, OnInit } from '@angular/core';
import { FeaturesState, getSelectedFeatureTargeting } from '../../store/features.state';
import { Store } from '@ngrx/store';
import { ActivatedRoute, Params } from '@angular/router';
import { FeatureTargeting } from '../../models/feature-targeting.model';
import { Observable } from 'rxjs/Observable';
import { Subscription } from 'rxjs/Subscription';
import { LoadFeatureTargeting } from '../../store/features.actions';
import { Select } from 'ngrx-actions';

@Component({
  selector: 'app-targeting',
  templateUrl: './targeting.component.html',
  styleUrls: ['./targeting.component.scss']
})
export class TargetingComponent implements OnInit, OnDestroy {
  @Select(getSelectedFeatureTargeting) model: Observable<FeatureTargeting>;
  paramsSub: Subscription;
  userTargetingVisible = false;

  constructor(private store: Store<FeaturesState>, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.paramsSub = this.route.params.subscribe((params: Params) => {
      const targetingId = params['targetingId'];
      this.store.dispatch(new LoadFeatureTargeting({ targetingId }));
    });
  }

  expandUserTargeting(): void {
    this.userTargetingVisible = true;
  }

  ngOnDestroy(): void {
    this.paramsSub.unsubscribe();
  }
}
