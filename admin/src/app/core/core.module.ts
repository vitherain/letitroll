import { NgModule } from '@angular/core';
import { AppRoutingModule } from '../app-routing.module';
import { NavigationComponent } from './navigation/navigation.component';
import { MaterialModule } from '../shared/material.module';
import { LoginComponent } from './auth/login/login.component';

@NgModule({
  declarations: [
    NavigationComponent,
    LoginComponent
  ],
  imports: [
    AppRoutingModule,
    MaterialModule
  ],
  exports: [
    AppRoutingModule,
    NavigationComponent
  ]
})
export class CoreModule { }
