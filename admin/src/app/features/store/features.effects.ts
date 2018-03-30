import { Injectable } from '@angular/core';
import { Actions, Effect } from '@ngrx/effects';

import * as FeatureActions from './features.actions';
import { Feature } from '../models/feature.model';
import { HttpClient } from '@angular/common/http';
import { Store } from '@ngrx/store';
import { State } from './features.state';

@Injectable()
export class FeatureEffects {

  @Effect()
  features$ = this.actions$
    .ofType(FeatureActions.API_GET_FEATURES)
    .switchMap((action: FeatureActions.ApiGetFeatures) => {
      return this.httpClient.get<Feature[]>('https://ng-recipe-book-3adbb.firebaseio.com/recipes.json', {
        observe: 'body',
        responseType: 'json'
      })
    })
    .map(
      (features) => {
        console.log(features);
        for (let recipe of features) {
          if (!recipe['ingredients']) {
            recipe['ingredients'] = [];
          }
        }
        return {
          type: FeatureActions.API_GET_FEATURES_SUCCESS,
          payload: features
        };
      }
    );

  constructor(private actions$: Actions,
              private httpClient: HttpClient,
              private store: Store<State>) {}
}
