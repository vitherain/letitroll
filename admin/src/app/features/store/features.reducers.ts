import * as Actions from './features.actions';
import { State } from './features.state';

const initialState: State = {
  features: []
};

export function featuresReducer(state = initialState, action: Actions.FeaturesActionsUnion) {
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
