import { Action } from '@ngrx/store';
import { HttpErrorInfo } from '../../shared/http/http-error-info';
import { Projects } from './projects.state';

export const API_GET_PROJECTS = 'API_GET_PROJECTS';
export const API_GET_PROJECTS_SUCCESS = 'API_GET_PROJECTS_SUCCESS';
export const API_GET_PROJECTS_FAILURE = 'API_GET_PROJECTS_FAILURE';

export class ApiGetProjects implements Action {
  readonly type = API_GET_PROJECTS;

  constructor() {}
}

export class ApiGetProjectsSuccess implements Action {
  readonly type = API_GET_PROJECTS_SUCCESS;

  constructor(public payload: Projects) {}
}

export class ApiGetProjectsFailure implements Action {
  readonly type = API_GET_PROJECTS_FAILURE;

  constructor(public payload: HttpErrorInfo) {}
}

export type ProjectsActionsUnion = ApiGetProjects | ApiGetProjectsSuccess | ApiGetProjectsFailure;
