import * as Actions from './features.actions';
import { State } from './features.state';

export function featuresReducer(state: State = { features: [] }, action: Actions.FeaturesActionsUnion) {
  switch (action.type) {
    case Actions.API_GET_FEATURES:
      return state;
    default:
      return state;
  }
}
