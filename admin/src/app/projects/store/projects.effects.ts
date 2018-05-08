import { Injectable } from '@angular/core';
import { Actions, Effect } from '@ngrx/effects';

import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs/Observable';
import { ProjectsState } from './projects.state';
import { Project } from '../models/project.model';
import { LoadProjects, LoadProjectsFailure, LoadProjectsSuccess } from './projects.actions';
import { ofAction } from 'ngrx-actions';

@Injectable()
export class ProjectsEffects {
  constructor(private actions$: Actions, private httpClient: HttpClient, private store: Store<ProjectsState>) {}

  @Effect()
  projects$ = this.actions$
    .pipe(ofAction(LoadProjects))
    .switchMap((action: LoadProjects) => {
      return this.httpClient.get<Project[]>('/api/v1/projects');
    })
    .map((state: Project[]) => {
      return new LoadProjectsSuccess({ entities: state, totalElements: state.length });
    })
    .catch((err: HttpErrorResponse) => {
      return Observable.of(new LoadProjectsFailure({ statusCode: err.status }));
    });
}
