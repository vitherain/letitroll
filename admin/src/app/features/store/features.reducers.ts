import * as Actions from './features.actions';
import { FeaturesState } from './features.state';

export function featuresReducer(state: FeaturesState = { features: [] }, action: Actions.FeaturesActionsUnion) {
  switch (action.type) {
    case Actions.API_GET_FEATURES:
      return state;
    default:
      return state;
  }
}
