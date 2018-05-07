import { TableRequestPayload } from '../../shared/tables/table-request.payload';
import { HttpErrorInfo } from '../../shared/http/http-error-info';
import { ListPayload } from '../../shared/store/list-payload';
import { Feature } from '../models/feature.model';
import { Action } from '@ngrx/store';

export const LOAD_FEATURES = '[FEATURES] LOAD_FEATURES';
export const LOAD_FEATURES_SUCCESS = '[FEATURES] LOAD_FEATURES_SUCCESS';
export const LOAD_FEATURES_FAILURE = '[FEATURES] LOAD_FEATURES_FAILURE';
export const TOGGLE_SIDE_NAV = '[FEATURES] TOGGLE_SIDE_NAV';

export class LoadFeatures implements Action {
  readonly type = LOAD_FEATURES;

  constructor(public payload: TableRequestPayload) {}
}

export class LoadFeaturesSuccess implements Action {
  readonly type = LOAD_FEATURES_SUCCESS;

  constructor(public payload: ListPayload<Feature>) {}
}

export class LoadFeaturesFailure implements Action {
  readonly type = LOAD_FEATURES_FAILURE;

  constructor(public payload: HttpErrorInfo) {}
}

export class ToggleFeaturesSideNav implements Action {
  readonly type = TOGGLE_SIDE_NAV;

  constructor() {}
}
