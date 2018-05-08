import { AppState } from '../../store/app.state';
import { Project } from '../models/project.model';
import { createFeatureSelector, createSelector } from '@ngrx/store';

export interface ProjectsState extends AppState {
  projects: Projects;
}

export interface Projects {
  entities: Array<Project>;
  totalElements: number;
  loading: boolean;
}

// I don't understand why this returns Projects and not ProjectsState effectively :-(
export const getProjectsState = createFeatureSelector<Projects>('projects');

export const getProjectsEntities = createSelector(getProjectsState, (state: Projects) => state.entities);
