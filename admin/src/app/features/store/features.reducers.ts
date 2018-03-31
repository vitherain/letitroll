import * as Actions from './features.actions';
import { FeaturesState } from './features.state';

export function featuresReducer(state: FeaturesState = { features: [] }, action: Actions.FeaturesActionsUnion) {
  switch (action.type) {
    case Actions.API_GET_FEATURES:
      return {
        ...state
      };
    case Actions.API_GET_FEATURES_SUCCESS:
      return {
        ...state,
        features: [...action.payload]
      };
    default:
      return state;
  }
}
