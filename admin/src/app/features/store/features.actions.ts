import { Action } from '@ngrx/store';
import { TableRequestPayload } from '../../shared/tables/table-request.payload';
import { Feature } from '../models/feature.model';
import { HttpErrorInfo } from '../../shared/http/http-error-info';
import { State } from './features.state';

export const API_GET_FEATURES = 'API_GET_FEATURES';
export const API_GET_FEATURES_SUCCESS = 'API_GET_FEATURES_SUCCESS';
export const API_GET_FEATURES_FAILURE = 'API_GET_FEATURES_FAILURE';

export class ApiGetFeatures implements Action {
  readonly type = API_GET_FEATURES;

  constructor(public payload: TableRequestPayload) {}
}

export class ApiGetFeaturesSuccess implements Action {
  readonly type = API_GET_FEATURES_SUCCESS;

  constructor(public payload: State) {}
}

export class ApiGetFeaturesFailure implements Action {
  readonly type = API_GET_FEATURES_FAILURE;

  constructor(public payload: HttpErrorInfo) {}
}

export type FeaturesActionsUnion = ApiGetFeatures
  | ApiGetFeaturesSuccess
  | ApiGetFeaturesFailure;
