import { Action, createReducer, Store } from 'ngrx-actions';
import {
  LoadFeatures,
  LoadFeaturesFailure,
  LoadFeaturesSuccess,
  SelectProject,
  ToggleFeaturesSideNav
} from './features.actions';
import { Feature } from '../models/feature.model';
import { Features } from './features.state';
import { ActionReducerMap } from '@ngrx/store';
import { Project } from '../../projects/models/project.model';

@Store([])
export class FeaturesEntitiesStore {
  @Action(LoadFeaturesSuccess)
  loadSuccess(state: Array<Feature>, action: LoadFeaturesSuccess) {
    return [...action.payload.entities];
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

@Store({})
export class SelectedProjectStore {
  @Action(SelectProject)
  load(state: Project, action: SelectProject) {
    return action.payload;
  }
}

export function entitiesReducer(state, action) {
  return createReducer(FeaturesEntitiesStore)(state, action);
}

export function totalElementsReducer(state, action) {
  return createReducer(FeaturesTotalElementsStore)(state, action);
}

export function loadingReducer(state, action) {
  return createReducer(FeaturesLoadingStore)(state, action);
}

export function sideNavReducer(state, action) {
  return createReducer(FeaturesSideNavOpenedStore)(state, action);
}

export function selectedProjectReducer(state, action) {
  return createReducer(FeaturesSideNavOpenedStore)(state, action);
}

export const featuresReducers: ActionReducerMap<Features> = {
  entities: entitiesReducer,
  totalElements: totalElementsReducer,
  loading: loadingReducer,
  sideNavOpened: sideNavReducer,
  selectedProject: selectedProjectReducer
};
