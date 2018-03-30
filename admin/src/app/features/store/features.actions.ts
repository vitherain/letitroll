import { Action } from '@ngrx/store';
import { RequestPayload } from '../../shared/tables/request.payload';

export const API_GET_FEATURES = 'API_GET_FEATURES';

export class ApiGetFeatures implements Action {
  readonly type = API_GET_FEATURES;

  constructor(public payload: RequestPayload) {}
}

export type FeaturesActions = ApiGetFeatures;
