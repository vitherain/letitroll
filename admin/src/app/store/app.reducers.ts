import { ActionReducer, ActionReducerMap, MetaReducer } from '@ngrx/store';

import { AppState } from './app.state';
import { routerReducer } from '@ngrx/router-store';
import { environment } from '../../environments/environment';
import { storeFreeze } from 'ngrx-store-freeze';

export const appReducers: ActionReducerMap<AppState> = {
  router: routerReducer
};

function logger(reducer: ActionReducer<AppState>): ActionReducer<AppState> {
  return function(state: AppState, action: any): AppState {
    console.log('state', state);
    console.log('action', action);

    return reducer(state, action);
  };
}

export const metaReducers: MetaReducer<AppState>[] = !environment.production ? [logger, storeFreeze] : [];
