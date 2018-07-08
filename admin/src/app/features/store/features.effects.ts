import { Injectable } from '@angular/core';
import { Actions, Effect } from '@ngrx/effects';

import {
  CloseDeleteConfirmDialog,
  CloseToggleFeatureTargetingConfirmDialog,
  DeleteFeature,
  DeleteFeatureFailure,
  DeleteFeatureSuccess,
  LoadFeatureTargeting,
  LoadFeatureTargetingFailure,
  LoadFeatureTargetings,
  LoadFeatureTargetingsFailure,
  LoadFeatureTargetingsSuccess,
  LoadFeatureTargetingSuccess,
  OpenDeleteConfirmDialog,
  OpenToggleFeatureTargetingConfirmDialog
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
import { ToggleFeatureTargetingDialogComponent } from '../components/toggle-feature-targeting-dialog/toggle-feature-targeting-dialog.component';

export interface FeatureTargetingsResponse {
  content: Array<FeatureTargeting>;
  totalElements: number;
}

@Injectable()
export class FeaturesEffects {
  constructor(private actions$: Actions, private dialog: MatDialog, private httpClient: HttpClient) {}

  @Effect()
  featuresTargetings$ = this.actions$
    .pipe(ofAction(LoadFeatureTargetings))
    .switchMap((action: LoadFeatureTargetings) => {
      const params = toHttpParams(action.payload.tableRequest);
      return this.httpClient.get<FeatureTargetingsResponse>(
        `/api/v1/environments/${action.payload.environmentId}/targeted-features`,
        { params }
      );
    })
    .map((targetings: FeatureTargetingsResponse) => {
      return new LoadFeatureTargetingsSuccess({
        entities: targetings.content,
        totalElements: targetings.totalElements
      });
    })
    .catch((err: HttpErrorResponse) => {
      return Observable.of(new LoadFeatureTargetingsFailure({ statusCode: err.status }));
    });

  @Effect()
  featureTargeting$ = this.actions$
    .pipe(ofAction(LoadFeatureTargeting))
    .switchMap((action: LoadFeatureTargeting) => {
      return this.httpClient.get<FeatureTargeting>(`/api/v1/targeted-features/${action.payload.targetingId}`);
    })
    .map((targeting: FeatureTargeting) => {
      return new LoadFeatureTargetingSuccess(targeting);
    })
    .catch((err: HttpErrorResponse) => {
      return Observable.of(new LoadFeatureTargetingFailure({ statusCode: err.status }));
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

  @Effect()
  openToggleFeatureTargetingConfirmDialog$ = this.actions$
    .pipe(ofAction(OpenToggleFeatureTargetingConfirmDialog))
    .pipe(
      switchMap((action: OpenToggleFeatureTargetingConfirmDialog) => {
        const dialogRef = this.dialog.open(ToggleFeatureTargetingDialogComponent, {
          data: { featureTargeting: action.payload }
        });
        return dialogRef.afterClosed();
      })
    )
    .pipe(
      map((data: { newValue: boolean; featureTargeting: FeatureTargeting }) => {
        return new CloseToggleFeatureTargetingConfirmDialog({
          newValue: data.newValue,
          featureTargeting: data.featureTargeting
        });
      })
    );
}
