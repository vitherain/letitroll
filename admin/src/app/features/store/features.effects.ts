import { Injectable } from '@angular/core';
import { Actions, Effect } from '@ngrx/effects';

import * as FeatureActions from './features.actions';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Store } from '@ngrx/store';
import { Features } from './features.state';
import { Observable } from 'rxjs/Observable';
import { toHttpParams } from '../../shared/tables/table-request.payload';

@Injectable()
export class FeaturesEffects {
  constructor(private actions$: Actions, private httpClient: HttpClient, private store: Store<Features>) {}

  @Effect()
  features$ = this.actions$
    .ofType(FeatureActions.LOAD_FEATURES)
    .switchMap((action: FeatureActions.LoadFeatures) => {
      const params = toHttpParams(action.payload);
      return this.httpClient.get<Features>('/api/v1/projects/5acfba0a85c2500f4007b6eb/features', { params });
    })
    .map((features: Features) => {
      return {
        type: FeatureActions.LOAD_FEATURES_SUCCESS,
        payload: { content: features.content, totalElements: features.totalElements }
      };
    })
    .catch((err: HttpErrorResponse) => {
      return Observable.of({
        type: FeatureActions.LOAD_FEATURES_FAILURE,
        payload: {
          statusCode: err.status
        }
      });
    });
}
