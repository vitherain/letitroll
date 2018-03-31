import { ActionReducerMap } from '@ngrx/store';

import { AppState } from './app.state';
import { featuresReducer } from '../features/store/features.reducers';

export const appReducers: ActionReducerMap<AppState> = {
  features: featuresReducer
};
