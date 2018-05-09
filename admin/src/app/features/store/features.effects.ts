import { Injectable } from '@angular/core';
import { Actions, Effect } from '@ngrx/effects';

import { LoadFeatures, LoadFeaturesFailure, LoadFeaturesSuccess } from './features.actions';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { toHttpParams } from '../../shared/tables/table-request.payload';
import { ofAction } from 'ngrx-actions';
import { FeatureTargeting } from '../models/feature-targeting.model';

export interface FeatureTargetingsResponse {
  content: Array<FeatureTargeting>;
  totalElements: number;
}

@Injectable()
export class FeaturesEffects {
  constructor(private actions$: Actions, private httpClient: HttpClient) {}

  @Effect()
  features$ = this.actions$
    .pipe(ofAction(LoadFeatures))
    .switchMap((action: LoadFeatures) => {
      const params = toHttpParams(action.payload.tableRequest);
      return this.httpClient.get<FeatureTargetingsResponse>(
        `/api/v1/environments/${action.payload.environmentId}/targeted-features`,
        { params }
      );
    })
    .map((targetings: FeatureTargetingsResponse) => {
      return new LoadFeaturesSuccess({ entities: targetings.content, totalElements: targetings.totalElements });
    })
    .catch((err: HttpErrorResponse) => {
      return Observable.of(new LoadFeaturesFailure({ statusCode: err.status }));
    });
}
