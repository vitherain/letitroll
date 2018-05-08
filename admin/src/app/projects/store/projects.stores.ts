import { Action, createReducer, Store } from 'ngrx-actions';
import { ActionReducerMap } from '@ngrx/store';
import { Projects } from './projects.state';
import { LoadProjects, LoadProjectsFailure, LoadProjectsSuccess } from './projects.actions';
import { Project } from '../models/project.model';

@Store([])
export class ProjectsEntitiesStore {
  @Action(LoadProjectsSuccess)
  loadSuccess(state: Array<Project>, action: LoadProjectsSuccess) {
    return [...action.payload.entities];
  }
}

@Store(0)
export class ProjectsTotalElementsStore {
  @Action(LoadProjectsSuccess)
  loadSuccess(state: number, action: LoadProjectsSuccess) {
    return action.payload.totalElements;
  }
}

@Store(false)
export class ProjectsLoadingStore {
  @Action(LoadProjects)
  load(state: boolean, action: LoadProjects) {
    return true;
  }

  @Action(LoadProjectsSuccess, LoadProjectsFailure)
  loadSuccessOrFailure(state: boolean, action: LoadProjectsSuccess | LoadProjectsFailure) {
    return false;
  }
}

export function entitiesReducer(state, action) {
  return createReducer(ProjectsEntitiesStore)(state, action);
}

export function totalElementsReducer(state, action) {
  return createReducer(ProjectsTotalElementsStore)(state, action);
}

export function loadingReducer(state, action) {
  return createReducer(ProjectsLoadingStore)(state, action);
}

export const projectsReducers: ActionReducerMap<Projects> = {
  entities: entitiesReducer,
  totalElements: totalElementsReducer,
  loading: loadingReducer
};
