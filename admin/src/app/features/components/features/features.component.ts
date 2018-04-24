import { Component, OnDestroy, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { ApiGetProjects } from '../../../projects/store/projects.actions';
import { Subscription } from 'rxjs/Subscription';
import { Subject } from 'rxjs/Subject';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { Project } from '../../../projects/models/project.model';
import { ProjectsState } from '../../../projects/store/projects.state';

@Component({
  selector: 'app-features',
  templateUrl: './features.component.html',
  styleUrls: ['./features.component.scss']
})
export class FeaturesComponent implements OnInit, OnDestroy {
  private projectsSubscription$: Subscription;
  private projects$: Subject<Project[]> = new BehaviorSubject([]);

  constructor(private store: Store<ProjectsState>) {}

  ngOnInit(): void {
    this.store.dispatch(new ApiGetProjects());
    this.projectsSubscription$ = this.store.select('projects').subscribe((state: Project[]) => {
      this.projects$.next(state);
    });
  }

  ngOnDestroy(): void {
    this.projectsSubscription$.unsubscribe();
  }
}
