import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { LoadProjects } from '../../../projects/store/projects.actions';
import { getProjectsState, Projects, ProjectsState } from '../../../projects/store/projects.state';
import { Observable } from 'rxjs/Observable';
import { FeaturesState, getSideNavOpened } from '../../store/features.state';
import { ToggleFeaturesSideNav } from '../../store/features.actions';
import { Select } from 'ngrx-actions';

@Component({
  selector: 'app-features',
  templateUrl: './features.component.html',
  styleUrls: ['./features.component.scss']
})
export class FeaturesComponent implements OnInit {
  @Select(getProjectsState) projects$: Observable<Projects>;
  @Select(getSideNavOpened) sideNavOpened$: Observable<boolean>;

  constructor(private featuresStore: Store<FeaturesState>, private projectsStore: Store<ProjectsState>) {}

  ngOnInit(): void {
    this.projectsStore.dispatch(new LoadProjects());
  }

  toggleSideNav(): void {
    this.featuresStore.dispatch(new ToggleFeaturesSideNav());
  }
}
