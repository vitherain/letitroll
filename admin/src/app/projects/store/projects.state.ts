import { AppState } from '../../store/app.state';
import { Project } from '../models/project.model';
import { createFeatureSelector, createSelector } from '@ngrx/store';

export interface ProjectsState extends AppState {
  projects: Projects;
}

export interface Projects {
  content: Array<Project>;
  totalElements: number;
  loading: boolean;
}

export const getProjectsState = createFeatureSelector<ProjectsState>('projects');

export const getProjects = createSelector(getProjectsState, (state: ProjectsState) => state.projects);
