import { Injectable } from '@angular/core';
import { Actions, Effect } from '@ngrx/effects';

import * as ProjectActions from './projects.actions';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs/Observable';
import { ProjectsState } from './projects.state';
import { Project } from '../models/project.model';

@Injectable()
export class ProjectsEffects {
  constructor(private actions$: Actions, private httpClient: HttpClient, private store: Store<ProjectsState>) {}

  @Effect()
  projects$ = this.actions$
    .ofType(ProjectActions.LOAD_PROJECTS)
    .switchMap((action: ProjectActions.LoadProjects) => {
      return this.httpClient.get<Project[]>('/api/v1/projects');
    })
    .map((state: Project[]) => {
      return {
        type: ProjectActions.LOAD_PROJECTS_SUCCESS,
        payload: { content: state, totalElements: state.length }
      };
    })
    .catch((err: HttpErrorResponse) => {
      return Observable.of({
        type: ProjectActions.LOAD_PROJECTS_FAILURE,
        payload: {
          statusCode: err.status
        }
      });
    });
}
