import { ActionReducerMap } from '@ngrx/store';

import { AppState } from './app.state';
import { routerReducer } from '@ngrx/router-store';

export const appReducers: ActionReducerMap<AppState> = {
  router: routerReducer
};
