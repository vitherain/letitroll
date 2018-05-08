import { Component, OnDestroy, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { LoadProjects } from '../../../projects/store/projects.actions';
import { getProjectsState, Projects, ProjectsState } from '../../../projects/store/projects.state';
import { Observable } from 'rxjs/Observable';
import {
  FeaturesState,
  getSelectedEnvironment,
  getSelectedProject,
  getSideNavOpened
} from '../../store/features.state';
import { SelectEnvironment, SelectProject, ToggleFeaturesSideNav } from '../../store/features.actions';
import { Select } from 'ngrx-actions';
import { Project } from '../../../projects/models/project.model';
import { Environment } from '../../../environments/models/environment.model';
import { Go } from '../../../store/app.actions';
import { AppState } from '../../../store/app.state';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-features',
  templateUrl: './features.component.html',
  styleUrls: ['./features.component.scss']
})
export class FeaturesComponent implements OnInit, OnDestroy {
  @Select(getProjectsState) projects$: Observable<Projects>;
  @Select(getSideNavOpened) sideNavOpened$: Observable<boolean>;
  @Select(getSelectedProject) selectedProject$: Observable<Project>;
  @Select(getSelectedEnvironment) selectedEnvironment$: Observable<Environment>;
  selectedProjectSubscription: Subscription;
  selectedProject: Project;
  selectedEnvironmentSubscription: Subscription;
  selectedEnvironment: Environment;

  constructor(
    private featuresStore: Store<FeaturesState>,
    private projectsStore: Store<ProjectsState>,
    private appStore: Store<AppState>
  ) {}

  ngOnInit(): void {
    this.projectsStore.dispatch(new LoadProjects());
    this.selectedProjectSubscription = this.selectedProject$.subscribe(
      (next: Project) => (this.selectedProject = next)
    );
    this.selectedEnvironmentSubscription = this.selectedEnvironment$.subscribe((next: Environment) => {
      this.selectedEnvironment = next;
      if (this.selectedEnvironment) {
        this.appStore.dispatch(
          new Go({
            path: ['features', this.selectedProject.name.toLowerCase(), this.selectedEnvironment.name.toLowerCase()]
          })
        );
      }
    });
  }

  toggleSideNav(): void {
    this.featuresStore.dispatch(new ToggleFeaturesSideNav());
  }

  selectProject(project: Project) {
    this.featuresStore.dispatch(new SelectProject(project));
  }

  selectEnvironment(environment: Environment) {
    this.featuresStore.dispatch(new SelectEnvironment(environment));
  }

  ngOnDestroy(): void {
    this.selectedProjectSubscription.unsubscribe();
    this.selectedEnvironmentSubscription.unsubscribe();
  }
}
