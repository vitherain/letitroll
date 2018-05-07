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

export const getFeaturesState = createFeatureSelector<FeaturesState>('features');

export const getFeatures = createSelector(getFeaturesState, (state: FeaturesState) => state.features);

export const getFeaturesContent = createSelector(getFeatures, (state: Features) => state.content);

export const getFeaturesTotalElements = createSelector(getFeatures, (state: Features) => state.totalElements);

export const getFeaturesLoading = createSelector(getFeatures, (state: Features) => state.loading);

export const getSideNavOpened = createSelector(getFeatures, (state: Features) => state.sideNavOpened);
