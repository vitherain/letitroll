import { HttpErrorInfo } from '../../shared/http/http-error-info';
import { Projects } from './projects.state';
import { CustomAction } from '../../shared/store/custom-action';

export const LOAD_PROJECTS = '[PROJECTS] LOAD_PROJECTS';
export const LOAD_PROJECTS_SUCCESS = '[PROJECTS] LOAD_PROJECTS_SUCCESS';
export const LOAD_PROJECTS_FAILURE = '[PROJECTS] LOAD_PROJECTS_FAILURE';

export class LoadProjects implements CustomAction {
  readonly type = LOAD_PROJECTS;

  constructor() {}
}

export class LoadProjectsSuccess implements CustomAction {
  readonly type = LOAD_PROJECTS_SUCCESS;

  constructor(public payload: Projects) {}
}

export class LoadProjectsFailure implements CustomAction {
  readonly type = LOAD_PROJECTS_FAILURE;

  constructor(public payload: HttpErrorInfo) {}
}

export type ProjectsActionsUnion = LoadProjects | LoadProjectsSuccess | LoadProjectsFailure;
