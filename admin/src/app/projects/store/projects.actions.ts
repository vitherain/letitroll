import { HttpErrorInfo } from '../../shared/http/http-error-info';
import { ListPayload } from '../../shared/store/list-payload';
import { Project } from '../models/project.model';
import { Action } from '@ngrx/store';

export class LoadProjects implements Action {
  readonly type = '[PROJECTS] LOAD_PROJECTS';

  constructor() {}
}

export class LoadProjectsSuccess implements Action {
  readonly type = '[PROJECTS] LOAD_PROJECTS_SUCCESS';

  constructor(public payload: ListPayload<Project>) {}
}

export class LoadProjectsFailure implements Action {
  readonly type = '[PROJECTS] LOAD_PROJECTS_FAILURE';

  constructor(public payload: HttpErrorInfo) {}
}
