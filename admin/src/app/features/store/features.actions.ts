import { TableRequestPayload } from '../../shared/tables/table-request.payload';
import { HttpErrorInfo } from '../../shared/http/http-error-info';
import { ListPayload } from '../../shared/store/list-payload';
import { Action } from '@ngrx/store';
import { Project } from '../../projects/models/project.model';
import { Environment } from '../../environments/models/environment.model';
import { FeatureTargeting } from '../models/feature-targeting.model';

export const LOAD_FEATURES = '[FEATURES] LOAD_FEATURES';
export const LOAD_FEATURES_SUCCESS = '[FEATURES] LOAD_FEATURES_SUCCESS';
export const LOAD_FEATURES_FAILURE = '[FEATURES] LOAD_FEATURES_FAILURE';
export const TOGGLE_SIDE_NAV = '[FEATURES] TOGGLE_SIDE_NAV';
export const SELECT_PROJECT = '[FEATURES] SELECT_PROJECT';
export const SELECT_ENVIRONMENT = '[FEATURES] SELECT_ENVIRONMENT';
export const OPEN_DELETE_CONFIRM_DIALOG = '[FEATURES] OPEN_DELETE_CONFIRM_DIALOG';

export interface LoadFeaturesPayload {
  environmentId: string;
  tableRequest: TableRequestPayload;
}

export class LoadFeatures implements Action {
  readonly type = LOAD_FEATURES;

  constructor(public payload: LoadFeaturesPayload) {}
}

export class LoadFeaturesSuccess implements Action {
  readonly type = LOAD_FEATURES_SUCCESS;

  constructor(public payload: ListPayload<FeatureTargeting>) {}
}

export class LoadFeaturesFailure implements Action {
  readonly type = LOAD_FEATURES_FAILURE;

  constructor(public payload: HttpErrorInfo) {}
}

export class ToggleFeaturesSideNav implements Action {
  readonly type = TOGGLE_SIDE_NAV;

  constructor() {}
}

export class SelectProject implements Action {
  readonly type = SELECT_PROJECT;

  constructor(public payload: Project) {}
}

export class SelectEnvironment implements Action {
  readonly type = SELECT_ENVIRONMENT;

  constructor(public payload: Environment) {}
}

export class OpenDeleteConfirmDialog implements Action {
  readonly type = OPEN_DELETE_CONFIRM_DIALOG;

  constructor(public payload: FeatureTargeting) {}
}
