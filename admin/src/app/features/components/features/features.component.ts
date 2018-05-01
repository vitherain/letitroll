import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { LoadProjects } from '../../../projects/store/projects.actions';
import { Projects, ProjectsState } from '../../../projects/store/projects.state';
import { Observable } from 'rxjs/Observable';
import { FeaturesState } from '../../store/features.state';
import { ToggleFeaturesSideNav } from '../../store/features.actions';

@Component({
  selector: 'app-features',
  templateUrl: './features.component.html',
  styleUrls: ['./features.component.scss']
})
export class FeaturesComponent implements OnInit {
  projects$: Observable<Projects>;
  sideNavOpened$: Observable<boolean>;

  constructor(private featuresStore: Store<FeaturesState>, private projectsStore: Store<ProjectsState>) {}

  ngOnInit(): void {
    this.projectsStore.dispatch(new LoadProjects());
    this.projects$ = this.projectsStore.select(state => state.projects);
    this.sideNavOpened$ = this.featuresStore.select(state => state.sideNavOpened);
  }

  toggleSideNav(): void {
    this.featuresStore.dispatch(new ToggleFeaturesSideNav());
  }
}
