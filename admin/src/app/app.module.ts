import { NgModule } from '@angular/core';

import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SharedModule } from './shared/shared.module';
import { CoreModule } from './core/core.module';

import { AppComponent } from './app.component';
import { environment } from '../environments/environment';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { FeaturesModule } from './features/features.module';
import { StoreModule } from '@ngrx/store';
import { appReducers, metaReducers } from './store/app.reducers';
import { EffectsModule } from '@ngrx/effects';
import { ProjectsModule } from './projects/projects.module';
import { RouterStateSerializer, StoreRouterConnectingModule } from '@ngrx/router-store';
import { CustomSerializer } from './store/router-custom-serializer';
import { NgrxActionsModule } from 'ngrx-actions';
import { AppEffects } from './store/app.effects';

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    SharedModule,
    CoreModule,
    NgrxActionsModule,
    StoreModule.forRoot(appReducers, { metaReducers }),
    StoreRouterConnectingModule.forRoot({
      stateKey: 'router'
    }),
    EffectsModule.forRoot([AppEffects]),
    FeaturesModule,
    ProjectsModule,
    StoreDevtoolsModule.instrument({
      logOnly: environment.production
    })
  ],
  bootstrap: [AppComponent],
  providers: [{ provide: RouterStateSerializer, useClass: CustomSerializer }]
})
export class AppModule {}
