import { Feature } from '../models/feature.model';
import { AppState } from '../../store/app.state';
import { createFeatureSelector, createSelector } from '@ngrx/store';

export interface FeaturesState extends AppState {
  features: Features;
}

export interface Features {
  content: Array<Feature>;
  totalElements: number;
  loading: boolean;
  sideNavOpened: boolean;
}

// I don't understand why this returns Features and not FeaturesState effectively :-(
export const getFeaturesState = createFeatureSelector<Features>('features');

export const getFeaturesContent = createSelector(getFeaturesState, (state: Features) => state.content);

export const getFeaturesTotalElements = createSelector(getFeaturesState, (state: Features) => state.totalElements);

export const getFeaturesLoading = createSelector(getFeaturesState, (state: Features) => state.loading);

export const getSideNavOpened = createSelector(getFeaturesState, (state: Features) => state.sideNavOpened);
