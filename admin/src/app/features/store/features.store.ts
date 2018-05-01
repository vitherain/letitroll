import { Action, Store } from 'ngrx-actions';

import { LoadFeaturesSuccess, ToggleFeaturesSideNav } from './features.actions';
import { Features } from './features.state';

@Store({
  content: [],
  totalElements: 0
})
export class FeaturesStore {
  @Action(LoadFeaturesSuccess)
  loadFeaturesSuccess(state: Features, action: LoadFeaturesSuccess) {
    return {
      ...state,
      ...action.payload
    };
  }
}

@Store(false)
export class SideNavStore {
  @Action(ToggleFeaturesSideNav)
  toggleFeaturesSideNav(state: boolean, action: ToggleFeaturesSideNav) {
    return !state;
  }
}
