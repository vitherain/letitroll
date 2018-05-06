import * as Actions from './features.actions';
import { Features } from './features.state';
import { ActionReducerMap } from '@ngrx/store';

function contentReducer(state = [], action: Actions.FeaturesActionsUnion) {
  switch (action.type) {
    case Actions.LOAD_FEATURES_SUCCESS:
      return [...action.payload.content];
    default:
      return state;
  }
}

function totalElementsReducer(state = 0, action: Actions.FeaturesActionsUnion) {
  switch (action.type) {
    case Actions.LOAD_FEATURES_SUCCESS:
      return action.payload.totalElements;
    default:
      return state;
  }
}

function loadingReducer(state: boolean = false, action: Actions.FeaturesActionsUnion) {
  switch (action.type) {
    case Actions.LOAD_FEATURES:
      return true;
    case Actions.LOAD_FEATURES_SUCCESS:
      return false;
    case Actions.LOAD_FEATURES_FAILURE:
      return false;
    default:
      return state;
  }
}

function sideNavReducer(state: boolean = false, action: Actions.FeaturesActionsUnion) {
  switch (action.type) {
    case Actions.TOGGLE_SIDE_NAV:
      return !state;
    default:
      return state;
  }
}

export const featuresReducers: ActionReducerMap<Features> = {
  content: contentReducer,
  totalElements: totalElementsReducer,
  loading: loadingReducer,
  sideNavOpened: sideNavReducer
};
