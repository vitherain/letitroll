import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';

import { SharedModule } from '../shared/shared.module';
import { projectsReducers } from './store/projects.stores';
import { ProjectsEffects } from './store/projects.effects';
import { NgrxActionsModule } from 'ngrx-actions';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    NgrxActionsModule,
    StoreModule.forFeature('projects', projectsReducers),
    EffectsModule.forFeature([ProjectsEffects])
  ]
})
export class ProjectsModule {}
