import { Action, createReducer, Store } from 'ngrx-actions';
import { LoadFeatures, LoadFeaturesFailure, LoadFeaturesSuccess, ToggleFeaturesSideNav } from './features.actions';
import { Feature } from '../models/feature.model';
import { Features } from './features.state';
import { ActionReducerMap } from '@ngrx/store';

@Store([])
export class FeaturesContentStore {
  @Action(LoadFeaturesSuccess)
  loadSuccess(state: Array<Feature>, action: LoadFeaturesSuccess) {
    return [...action.payload.content];
  }
}

@Store(0)
export class FeaturesTotalElementsStore {
  @Action(LoadFeaturesSuccess)
  loadSuccess(state: number, action: LoadFeaturesSuccess) {
    return action.payload.totalElements;
  }
}

@Store(false)
export class FeaturesLoadingStore {
  @Action(LoadFeatures)
  load(state: boolean, action: LoadFeatures) {
    return true;
  }

  @Action(LoadFeaturesSuccess, LoadFeaturesFailure)
  loadSuccessOrFailure(state: boolean, action: LoadFeaturesSuccess | LoadFeaturesFailure) {
    return false;
  }
}

@Store(true)
export class FeaturesSideNavOpenedStore {
  @Action(ToggleFeaturesSideNav)
  load(state: boolean, action: ToggleFeaturesSideNav) {
    return !state;
  }
}

function contentReducer(state, action) {
  return createReducer(FeaturesContentStore)(state, action);
}

function totalElementsReducer(state, action) {
  return createReducer(FeaturesTotalElementsStore)(state, action);
}

function loadingReducer(state, action) {
  return createReducer(FeaturesLoadingStore)(state, action);
}

function sideNavReducer(state, action) {
  return createReducer(FeaturesSideNavOpenedStore)(state, action);
}

export const featuresReducers: ActionReducerMap<Features> = {
  content: contentReducer,
  totalElements: totalElementsReducer,
  loading: loadingReducer,
  sideNavOpened: sideNavReducer
};
