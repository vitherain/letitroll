import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { Actions, Effect } from '@ngrx/effects';
import { map, tap } from 'rxjs/operators';
import { ofAction } from 'ngrx-actions';
import { Back, Forward, Go } from './app.actions';

@Injectable()
export class AppEffects {
  constructor(private actions$: Actions, private router: Router, private location: Location) {}

  @Effect({ dispatch: false })
  navigate$ = this.actions$.pipe(
    ofAction(Go),
    map((action: Go) => action.payload),
    tap(({ path, query: queryParams, extras }) => this.router.navigate(path, { queryParams, ...extras }))
  );

  @Effect({ dispatch: false })
  navigateBack$ = this.actions$.pipe(ofAction(Back), tap(() => this.location.back()));

  @Effect({ dispatch: false })
  navigateForward$ = this.actions$.pipe(ofAction(Forward), tap(() => this.location.forward()));
}
