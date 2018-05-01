import { RouterReducerState } from '@ngrx/router-store';
import { RouterStateUrl } from './router-custom-serializer';

export interface AppState {
  router: RouterReducerState<RouterStateUrl>;
}
