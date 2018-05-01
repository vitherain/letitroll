import { TableRequestPayload } from '../../shared/tables/table-request.payload';
import { HttpErrorInfo } from '../../shared/http/http-error-info';
import { FeaturesState } from './features.state';
import { CustomAction } from '../../shared/store/custom-action';

export const LOAD_FEATURES = '[FEATURES] LOAD_FEATURES';
export const LOAD_FEATURES_SUCCESS = '[FEATURES] LOAD_FEATURES_SUCCESS';
export const LOAD_FEATURES_FAILURE = '[FEATURES] LOAD_FEATURES_FAILURE';
export const TOGGLE_SIDE_NAV = '[FEATURES] TOGGLE_SIDE_NAV';

export class LoadFeatures implements CustomAction {
  readonly type = LOAD_FEATURES;

  constructor(public payload: TableRequestPayload) {}
}

export class LoadFeaturesSuccess implements CustomAction {
  readonly type = LOAD_FEATURES_SUCCESS;

  constructor(public payload: FeaturesState) {}
}

export class LoadFeaturesFailure implements CustomAction {
  readonly type = LOAD_FEATURES_FAILURE;

  constructor(public payload: HttpErrorInfo) {}
}

export class ToggleFeaturesSideNav implements CustomAction {
  readonly type = TOGGLE_SIDE_NAV;

  constructor() {}
}

export type FeaturesActionsUnion = LoadFeatures | LoadFeaturesSuccess | LoadFeaturesFailure | ToggleFeaturesSideNav;
