import { Action, createReducer, Store } from 'ngrx-actions';
import {
  LoadFeatureTargetings,
  LoadFeatureTargetingsFailure,
  LoadFeatureTargetingsSuccess,
  LoadFeatureTargetingSuccess,
  SelectEnvironment,
  SelectProject,
  ToggleFeaturesSideNav
} from './features.actions';
import { Feature } from '../models/feature.model';
import { Features } from './features.state';
import { ActionReducerMap } from '@ngrx/store';
import { Project } from '../../projects/models/project.model';
import { Environment } from '../../environments/models/environment.model';
import { FeatureTargeting } from '../models/feature-targeting.model';

@Store([])
export class FeaturesEntitiesStore {
  @Action(LoadFeatureTargetingsSuccess)
  loadSuccess(state: Array<Feature>, action: LoadFeatureTargetingsSuccess) {
    return [...action.payload.entities];
  }
}

@Store(0)
export class FeaturesTotalElementsStore {
  @Action(LoadFeatureTargetingsSuccess)
  loadSuccess(state: number, action: LoadFeatureTargetingsSuccess) {
    return action.payload.totalElements;
  }
}

@Store(false)
export class FeaturesLoadingStore {
  @Action(LoadFeatureTargetings)
  load(state: boolean, action: LoadFeatureTargetings) {
    return true;
  }

  @Action(LoadFeatureTargetingsSuccess, LoadFeatureTargetingsFailure)
  loadSuccessOrFailure(state: boolean, action: LoadFeatureTargetingsSuccess | LoadFeatureTargetingsFailure) {
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

@Store(null)
export class SelectedProjectStore {
  @Action(SelectProject)
  load(state: Project, action: SelectProject) {
    return action.payload;
  }
}

@Store(null)
export class SelectedEnvironmentStore {
  @Action(SelectEnvironment)
  load(state: Environment, action: SelectEnvironment) {
    return action.payload;
  }
}

@Store(null)
export class SelectedFeatureTargetingStore {
  @Action(LoadFeatureTargetingSuccess)
  load(state: FeatureTargeting, action: LoadFeatureTargetingSuccess) {
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
  return createReducer(SelectedProjectStore)(state, action);
}

export function selectedEnvironmentReducer(state, action) {
  return createReducer(SelectedEnvironmentStore)(state, action);
}

export function selectedFeatureTargetingReducer(state, action) {
  return createReducer(SelectedFeatureTargetingStore)(state, action);
}

export const featuresReducers: ActionReducerMap<Features> = {
  entities: entitiesReducer,
  totalElements: totalElementsReducer,
  loading: loadingReducer,
  sideNavOpened: sideNavReducer,
  selectedProject: selectedProjectReducer,
  selectedEnvironment: selectedEnvironmentReducer,
  selectedFeatureTargeting: selectedFeatureTargetingReducer
};
