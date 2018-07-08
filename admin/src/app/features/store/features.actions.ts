import { TableRequestPayload } from '../../shared/tables/table-request.payload';
import { HttpErrorInfo } from '../../shared/http/http-error-info';
import { ListPayload } from '../../shared/store/list-payload';
import { Action } from '@ngrx/store';
import { Project } from '../../projects/models/project.model';
import { Environment } from '../../environments/models/environment.model';
import { FeatureTargeting } from '../models/feature-targeting.model';
import { Feature } from '../models/feature.model';

export interface LoadFeaturesPayload {
  environmentId: string;
  tableRequest: TableRequestPayload;
}

export class LoadFeatureTargetings implements Action {
  readonly type = '[FEATURES] LOAD_FEATURE_TARGETINGS';

  constructor(public payload: LoadFeaturesPayload) {}
}

export class LoadFeatureTargetingsSuccess implements Action {
  readonly type = '[FEATURES] LOAD_FEATURE_TARGETINGS_SUCCESS';

  constructor(public payload: ListPayload<FeatureTargeting>) {}
}

export class LoadFeatureTargetingsFailure implements Action {
  readonly type = '[FEATURES] LOAD_FEATURE_TARGETINGS_FAILURE';

  constructor(public payload: HttpErrorInfo) {}
}

export class LoadFeatureTargeting implements Action {
  readonly type = '[FEATURES] LOAD_FEATURE_TARGETING';

  constructor(public payload: { targetingId: string }) {}
}

export class LoadFeatureTargetingSuccess implements Action {
  readonly type = '[FEATURES] LOAD_FEATURE_TARGETING_SUCCESS';

  constructor(public payload: FeatureTargeting) {}
}

export class LoadFeatureTargetingFailure implements Action {
  readonly type = '[FEATURES] LOAD_FEATURE_TARGETING_FAILURE';

  constructor(public payload: HttpErrorInfo) {}
}

export class ToggleFeaturesSideNav implements Action {
  readonly type = '[FEATURES] TOGGLE_SIDE_NAV';

  constructor() {}
}

export class SelectProject implements Action {
  readonly type = '[FEATURES] SELECT_PROJECT';

  constructor(public payload: Project) {}
}

export class SelectEnvironment implements Action {
  readonly type = '[FEATURES] SELECT_ENVIRONMENT';

  constructor(public payload: Environment) {}
}

export class OpenDeleteConfirmDialog implements Action {
  readonly type = '[FEATURES] OPEN_DELETE_CONFIRM_DIALOG';

  constructor(public payload: FeatureTargeting) {}
}

export class CloseDeleteConfirmDialog implements Action {
  readonly type = '[FEATURES] CLOSE_DELETE_CONFIRM_DIALOG';

  constructor(public payload: { deleteIt: boolean; feature: Feature }) {}
}

export class OpenToggleFeatureTargetingConfirmDialog implements Action {
  readonly type = '[FEATURES] OPEN_TOGGLE_FEATURE_TARGETING_CONFIRM_DIALOG';

  constructor(public payload: FeatureTargeting) {}
}

export class CloseToggleFeatureTargetingConfirmDialog implements Action {
  readonly type = '[FEATURES] CLOSE_TOGGLE_FEATURE_TARGETING_CONFIRM_DIALOG';

  constructor(public payload: { newValue: boolean; featureTargeting: FeatureTargeting }) {}
}

export class DeleteFeature implements Action {
  readonly type = '[FEATURES] DELETE_FEATURE';

  constructor(public payload: Feature) {}
}

export class DeleteFeatureSuccess implements Action {
  readonly type = '[FEATURES] DELETE_FEATURE_SUCCESS';

  constructor() {}
}

export class DeleteFeatureFailure implements Action {
  readonly type = '[FEATURES] DELETE_FEATURE_FAILURE';

  constructor(public payload: HttpErrorInfo) {}
}
