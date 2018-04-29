import * as Actions from './projects.actions';
import { ApiGetProjectsSuccess } from './projects.actions';
import { ProjectsState } from './projects.state';

const initialState: ProjectsState = {
  projects: []
};

export function projectsReducer(state = initialState, action: Actions.ProjectsActionsUnion) {
  switch (action.type) {
    case Actions.API_GET_PROJECTS:
      return {
        ...state
      };
    case Actions.API_GET_PROJECTS_SUCCESS:
      return {
        ...state,
        projects: (action as ApiGetProjectsSuccess).payload
      };
    default:
      return state;
  }
}
