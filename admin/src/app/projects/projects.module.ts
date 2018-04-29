import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';

import { SharedModule } from '../shared/shared.module';
import { projectsReducer } from './store/projects.reducers';
import { ProjectsEffects } from './store/projects.effects';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    StoreModule.forFeature('projects', projectsReducer),
    EffectsModule.forFeature([ProjectsEffects])
  ]
})
export class ProjectsModule {}
