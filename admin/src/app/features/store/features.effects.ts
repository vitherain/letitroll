import { Injectable } from '@angular/core';
import { Actions, Effect } from '@ngrx/effects';

import { LoadFeatures, LoadFeaturesFailure, LoadFeaturesSuccess } from './features.actions';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { toHttpParams } from '../../shared/tables/table-request.payload';
import { ofAction } from 'ngrx-actions';
import { Feature } from '../models/feature.model';

export interface FeaturesResponse {
  content: Array<Feature>;
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
      return this.httpClient.get<FeaturesResponse>(
        `/api/v1/projects/${action.payload.projectId}/environments/${action.payload.environmentId}/targeted-features`,
        { params }
      );
    })
    .map((features: FeaturesResponse) => {
      return new LoadFeaturesSuccess({ entities: features.content, totalElements: features.totalElements });
    })
    .catch((err: HttpErrorResponse) => {
      return Observable.of(new LoadFeaturesFailure({ statusCode: err.status }));
    });
}
