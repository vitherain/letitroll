import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { ApiGetProjects } from '../../../projects/store/projects.actions';
import { Projects, ProjectsState } from '../../../projects/store/projects.state';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-features',
  templateUrl: './features.component.html',
  styleUrls: ['./features.component.scss']
})
export class FeaturesComponent implements OnInit {
  projects$: Observable<Projects>;

  constructor(private store: Store<ProjectsState>) {}

  ngOnInit(): void {
    this.store.dispatch(new ApiGetProjects());
    this.projects$ = this.store.select('projects');
  }
}
