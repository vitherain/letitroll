import { NgModule } from '@angular/core';

import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';

import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SharedModule } from './shared/shared.module';
import { CoreModule } from './core/core.module';

import { AppComponent } from './app.component';
import { FeaturesComponent } from './features/components/features/features.component';
import { featuresReducer } from './features/store/features.reducers';
import { FeatureEffects } from './features/store/features.effects';

@NgModule({
  declarations: [
    AppComponent,
    FeaturesComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    SharedModule,
    CoreModule,
    StoreModule.forFeature('features', featuresReducer),
    EffectsModule.forFeature([FeatureEffects])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
