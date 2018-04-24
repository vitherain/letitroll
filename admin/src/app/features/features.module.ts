import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';

import { FeaturesEffects } from './store/features.effects';
import { featuresReducer } from './store/features.reducers';
import { FeaturesComponent } from './components/features/features.component';
import { FeaturesRoutingModule } from './features-routing.module';
import { SharedModule } from '../shared/shared.module';
import { FeaturesListComponent } from './components/features-list/features-list.component';
import { DeleteFeatureDialogComponent } from './components/delete-feature-dialog/delete-feature-dialog.component';
import { FormsModule } from '@angular/forms';
import { projectsReducer } from '../projects/store/projects.reducers';

@NgModule({
  declarations: [FeaturesComponent, FeaturesListComponent, DeleteFeatureDialogComponent],
  entryComponents: [DeleteFeatureDialogComponent],
  imports: [
    CommonModule,
    FeaturesRoutingModule,
    SharedModule,
    StoreModule.forFeature('features', featuresReducer),
    StoreModule.forFeature('projects', projectsReducer),
    EffectsModule.forFeature([FeaturesEffects]),
    FormsModule
  ]
})
export class FeaturesModule {}
