import * as Actions from './features.actions';
import { Features } from './features.state';

const initialState: Features = {
  content: [],
  totalElements: 0
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
        ...action.payload
      };
    default:
      return state;
  }
}
