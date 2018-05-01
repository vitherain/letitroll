import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EffectsModule } from '@ngrx/effects';

import { FeaturesEffects } from './store/features.effects';
import { FeaturesStore, SideNavStore } from './store/features.store';
import { FeaturesComponent } from './components/features/features.component';
import { FeaturesRoutingModule } from './features-routing.module';
import { SharedModule } from '../shared/shared.module';
import { FeaturesListComponent } from './components/features-list/features-list.component';
import { DeleteFeatureDialogComponent } from './components/delete-feature-dialog/delete-feature-dialog.component';
import { FormsModule } from '@angular/forms';
import { NgrxActionsModule } from 'ngrx-actions';

@NgModule({
  declarations: [FeaturesComponent, FeaturesListComponent, DeleteFeatureDialogComponent],
  entryComponents: [DeleteFeatureDialogComponent],
  imports: [
    CommonModule,
    FeaturesRoutingModule,
    SharedModule,
    NgrxActionsModule.forFeature('features', FeaturesStore),
    NgrxActionsModule.forFeature('sideNavOpened', SideNavStore),
    EffectsModule.forFeature([FeaturesEffects]),
    FormsModule
  ]
})
export class FeaturesModule {}
