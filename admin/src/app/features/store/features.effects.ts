import { Injectable } from '@angular/core';
import { Actions, Effect } from '@ngrx/effects';

import {
  CloseDeleteConfirmDialog,
  DeleteFeature,
  DeleteFeatureFailure,
  DeleteFeatureSuccess,
  LoadFeatures,
  LoadFeaturesFailure,
  LoadFeaturesSuccess,
  OpenDeleteConfirmDialog
} from './features.actions';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { toHttpParams } from '../../shared/tables/table-request.payload';
import { ofAction } from 'ngrx-actions';
import { FeatureTargeting } from '../models/feature-targeting.model';
import { map, switchMap, tap } from 'rxjs/operators';
import { DeleteFeatureDialogComponent } from '../components/delete-feature-dialog/delete-feature-dialog.component';
import { MatDialog } from '@angular/material';
import { Feature } from '../models/feature.model';

export interface FeatureTargetingsResponse {
  content: Array<FeatureTargeting>;
  totalElements: number;
}

@Injectable()
export class FeaturesEffects {
  constructor(private actions$: Actions, private dialog: MatDialog, private httpClient: HttpClient) {}

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

  @Effect()
  deleteFeature$ = this.actions$
    .pipe(ofAction(DeleteFeature))
    .switchMap((action: DeleteFeature) => {
      return this.httpClient.delete(`/api/v1/projects/${action.payload.projectId}/features/${action.payload.id}`);
    })
    .map(() => new DeleteFeatureSuccess())
    .catch((err: HttpErrorResponse) => {
      return Observable.of(new DeleteFeatureFailure({ statusCode: err.status }));
    });

  @Effect()
  openDeleteConfirmDialog$ = this.actions$
    .pipe(ofAction(OpenDeleteConfirmDialog))
    .pipe(
      switchMap((action: OpenDeleteConfirmDialog) => {
        const dialogRef = this.dialog.open(DeleteFeatureDialogComponent, {
          data: { featureTargeting: action.payload }
        });
        return dialogRef.afterClosed();
      })
    )
    .pipe(
      map((data: { deleteIt: boolean; feature: Feature }) => {
        return new CloseDeleteConfirmDialog({ deleteIt: data.deleteIt, feature: data.feature });
      })
    );

  @Effect()
  closeDeleteConfirmDialog$ = this.actions$.pipe(ofAction(CloseDeleteConfirmDialog)).pipe(
    tap((action: CloseDeleteConfirmDialog) => {
      if (action.payload.deleteIt) {
        return new DeleteFeature(action.payload.feature);
      }
    })
  );
}
