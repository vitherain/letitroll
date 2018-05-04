import * as Actions from './features.actions';
import { Features } from './features.state';

const featuresInitialState: Features = {
  content: [],
  totalElements: 0
};

export function featuresReducer(state = featuresInitialState, action: Actions.FeaturesActionsUnion) {
  switch (action.type) {
    case Actions.LOAD_FEATURES:
      return {
        ...state
      };
    case Actions.LOAD_FEATURES_SUCCESS:
      return {
        ...state,
        ...action.payload
      };
    default:
      return state;
  }
}

export function sideNavReducer(state: boolean = false, action: Actions.FeaturesActionsUnion) {
  switch (action.type) {
    case Actions.TOGGLE_SIDE_NAV:
      return !state;
    default:
      return state;
  }
}
