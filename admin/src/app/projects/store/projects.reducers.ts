import * as Actions from './projects.actions';
import { Projects } from './projects.state';

const initialState: Projects = {
  content: [],
  totalElements: 0
};

export function projectsReducer(state = initialState, action: Actions.ProjectsActionsUnion) {
  switch (action.type) {
    case Actions.LOAD_PROJECTS:
      return {
        ...state
      };
    case Actions.API_GET_PROJECTS_SUCCESS:
      return {
        ...state,
        ...action.payload
      };
    default:
      return state;
  }
}
