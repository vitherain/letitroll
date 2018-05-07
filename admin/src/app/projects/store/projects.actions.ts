import { HttpErrorInfo } from '../../shared/http/http-error-info';
import { ListPayload } from '../../shared/store/list-payload';
import { Project } from '../models/project.model';
import { Action } from '@ngrx/store';

export const LOAD_PROJECTS = '[PROJECTS] LOAD_PROJECTS';
export const LOAD_PROJECTS_SUCCESS = '[PROJECTS] LOAD_PROJECTS_SUCCESS';
export const LOAD_PROJECTS_FAILURE = '[PROJECTS] LOAD_PROJECTS_FAILURE';

export class LoadProjects implements Action {
  readonly type = LOAD_PROJECTS;

  constructor() {}
}

export class LoadProjectsSuccess implements Action {
  readonly type = LOAD_PROJECTS_SUCCESS;

  constructor(public payload: ListPayload<Project>) {}
}

export class LoadProjectsFailure implements Action {
  readonly type = LOAD_PROJECTS_FAILURE;

  constructor(public payload: HttpErrorInfo) {}
}
