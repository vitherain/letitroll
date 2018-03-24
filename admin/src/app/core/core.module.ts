import { NgModule } from '@angular/core';
import { HomeComponent } from './home/home.component';
import { AppRoutingModule } from './app-routing.module';
import { NavigationComponent } from './navigation/navigation.component';
import { MaterialModule } from '../shared/material.module';

@NgModule({
  declarations: [
    HomeComponent,
    NavigationComponent
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
