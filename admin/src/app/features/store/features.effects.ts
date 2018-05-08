import { Injectable } from '@angular/core';
import { Actions, Effect } from '@ngrx/effects';

import { LoadFeatures, LoadFeaturesFailure, LoadFeaturesSuccess } from './features.actions';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Features } from './features.state';
import { Observable } from 'rxjs/Observable';
import { toHttpParams } from '../../shared/tables/table-request.payload';
import { ofAction } from 'ngrx-actions';

@Injectable()
export class FeaturesEffects {
  constructor(private actions$: Actions, private httpClient: HttpClient) {}

  @Effect()
  features$ = this.actions$
    .pipe(ofAction(LoadFeatures))
    .switchMap((action: LoadFeatures) => {
      const params = toHttpParams(action.payload);
      return this.httpClient.get<Features>('/api/v1/projects/5acfba0a85c2500f4007b6eb/features', { params });
    })
    .map((features: Features) => {
      return new LoadFeaturesSuccess({ entities: features.entities, totalElements: features.totalElements });
    })
    .catch((err: HttpErrorResponse) => {
      return Observable.of(new LoadFeaturesFailure({ statusCode: err.status }));
    });
}
