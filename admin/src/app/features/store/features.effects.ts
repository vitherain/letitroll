import { Injectable } from '@angular/core';
import { Actions, Effect } from '@ngrx/effects';

import * as FeatureActions from './features.actions';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Store } from '@ngrx/store';
import { FeaturesState, State } from './features.state';
import { Observable } from 'rxjs/Observable';
import { toHttpParams } from '../../shared/tables/table-request.payload';

@Injectable()
export class FeaturesEffects {

  constructor(private actions$: Actions,
              private httpClient: HttpClient,
              private store: Store<FeaturesState>) {}

  @Effect()
  features$ = this.actions$
    .ofType(FeatureActions.API_GET_FEATURES)
    .switchMap((action: FeatureActions.ApiGetFeatures) => {
      const params = toHttpParams(action.payload);
      return this.httpClient.get<State>('/api/v1/projects/5acfba0a85c2500f4007b6eb/features', { params });
    })
    .map((features: State) => {
      return {
        type: FeatureActions.API_GET_FEATURES_SUCCESS,
        payload: { content: features.content, totalElements: features.totalElements }
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
