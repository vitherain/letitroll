import { Injectable } from '@angular/core';
import { Actions, Effect } from '@ngrx/effects';

import * as FeatureActions from './features.actions';
import { Feature } from '../models/feature.model';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Store } from '@ngrx/store';
import { State } from './features.state';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class FeaturesEffects {

  constructor(private actions$: Actions,
              private httpClient: HttpClient,
              private store: Store<State>) {}

  @Effect()
  features$ = this.actions$
    .ofType(FeatureActions.API_GET_FEATURES)
    .switchMap((action: FeatureActions.ApiGetFeatures) => {
      return this.httpClient.get<Feature[]>('/api/v1/features');
    })
    .map((features: Feature[]) => {
      return {
        type: FeatureActions.API_GET_FEATURES_SUCCESS,
        payload: features
      };
    })
    .catch((err: HttpErrorResponse) => {
      return Observable.of({
        type: FeatureActions.API_GET_FEATURES_FAILURE,
        payload: {
          statusCode: err.status
        }
      });
    });
}
