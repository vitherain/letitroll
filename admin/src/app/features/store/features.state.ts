import { Feature } from '../models/feature.model';
import { AppState } from '../../store/app.state';
import { createFeatureSelector, createSelector } from '@ngrx/store';
import { Project } from '../../projects/models/project.model';

export interface FeaturesState extends AppState {
  features: Features;
}

export interface Features {
  entities: Array<Feature>;
  totalElements: number;
  loading: boolean;
  sideNavOpened: boolean;
  selectedProject: Project;
}

// I don't understand why this returns Features and not FeaturesState effectively :-(
export const getFeaturesState = createFeatureSelector<Features>('features');

export const getFeaturesEntities = createSelector(getFeaturesState, (state: Features) => state.entities);

export const getFeaturesTotalElements = createSelector(getFeaturesState, (state: Features) => state.totalElements);

export const getFeaturesLoading = createSelector(getFeaturesState, (state: Features) => state.loading);

export const getSideNavOpened = createSelector(getFeaturesState, (state: Features) => state.sideNavOpened);

export const getSelectedProject = createSelector(getFeaturesState, (state: Features) => state.selectedProject);
