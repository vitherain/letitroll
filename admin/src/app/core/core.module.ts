import { NgModule } from '@angular/core';
import { HomeComponent } from './home/home.component';
import { AppRoutingModule } from './app-routing.module';
import { NavigationComponent } from './navigation/navigation.component';

@NgModule({
  declarations: [
    HomeComponent,
    NavigationComponent
  ],
  imports: [AppRoutingModule],
  exports: [
    AppRoutingModule,
    NavigationComponent
  ]
})
export class CoreModule { }
