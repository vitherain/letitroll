import { Injectable } from '@angular/core';
import { Actions, Effect } from '@ngrx/effects';

import * as ProjectActions from './projects.actions';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs/Observable';
import { ProjectsState } from './projects.state';

@Injectable()
export class ProjectsEffects {
  constructor(private actions$: Actions, private httpClient: HttpClient, private store: Store<ProjectsState>) {}

  @Effect()
  projects$ = this.actions$
    .ofType(ProjectActions.API_GET_PROJECTS)
    .switchMap((action: ProjectActions.ApiGetProjects) => {
      return this.httpClient.get<ProjectsState>('/api/v1/projects');
    })
    .map((state: ProjectsState) => {
      return {
        type: ProjectActions.API_GET_PROJECTS_SUCCESS,
        payload: state.projects
      };
    })
    .catch((err: HttpErrorResponse) => {
      return Observable.of({
        type: ProjectActions.API_GET_PROJECTS_FAILURE,
        payload: {
          statusCode: err.status
        }
      });
    });
}
