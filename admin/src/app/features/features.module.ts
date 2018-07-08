import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';

import { FeaturesEffects } from './store/features.effects';
import { featuresReducers } from './store/features.stores';
import { FeaturesComponent } from './components/features/features.component';
import { FeaturesRoutingModule } from './features-routing.module';
import { SharedModule } from '../shared/shared.module';
import { FeaturesListComponent } from './components/features-list/features-list.component';
import { DeleteFeatureDialogComponent } from './components/delete-feature-dialog/delete-feature-dialog.component';
import { FormsModule } from '@angular/forms';
import { NgrxActionsModule } from 'ngrx-actions';
import { UnselectedProjectComponent } from './components/unselected-project/unselected-project.component';
import { TargetingComponent } from './components/targeting/targeting.component';
import { ToggleFeatureTargetingDialogComponent } from './components/toggle-feature-targeting-dialog/toggle-feature-targeting-dialog.component';

@NgModule({
  declarations: [
    FeaturesComponent,
    FeaturesListComponent,
    DeleteFeatureDialogComponent,
    UnselectedProjectComponent,
    TargetingComponent,
    ToggleFeatureTargetingDialogComponent
  ],
  entryComponents: [DeleteFeatureDialogComponent, ToggleFeatureTargetingDialogComponent],
  imports: [
    CommonModule,
    FeaturesRoutingModule,
    SharedModule,
    NgrxActionsModule,
    StoreModule.forFeature('features', featuresReducers),
    EffectsModule.forFeature([FeaturesEffects]),
    FormsModule
  ]
})
export class FeaturesModule {}
