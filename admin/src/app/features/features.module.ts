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

@NgModule({
  declarations: [
    FeaturesComponent,
    FeaturesListComponent
  ],
  imports: [
    CommonModule,
    FeaturesRoutingModule,
    SharedModule,
    StoreModule.forFeature('features', featuresReducer),
    EffectsModule.forFeature([FeaturesEffects]),
  ]
})
export class FeaturesModule {}
