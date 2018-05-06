import * as Actions from './projects.actions';
import { Projects } from './projects.state';
import { ActionReducerMap } from '@ngrx/store';

function contentReducer(state = [], action: Actions.ProjectsActionsUnion) {
  switch (action.type) {
    case Actions.LOAD_PROJECTS_SUCCESS:
      return [...action.payload.content];
    default:
      return state;
  }
}

function totalElementsReducer(state = 0, action: Actions.ProjectsActionsUnion) {
  switch (action.type) {
    case Actions.LOAD_PROJECTS_SUCCESS:
      return action.payload.totalElements;
    default:
      return state;
  }
}

function loadingReducer(state: boolean = false, action: Actions.ProjectsActionsUnion) {
  switch (action.type) {
    case Actions.LOAD_PROJECTS:
      return true;
    case Actions.LOAD_PROJECTS_SUCCESS:
      return false;
    case Actions.LOAD_PROJECTS_FAILURE:
      return false;
    default:
      return state;
  }
}

export const projectsReducers: ActionReducerMap<Projects> = {
  content: contentReducer,
  totalElements: totalElementsReducer,
  loading: loadingReducer
};
