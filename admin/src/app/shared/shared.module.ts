import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MaterialModule } from './material.module';
import { DateTimeProviderService } from './services/date-time-provider.service';

@NgModule({
  imports: [
    MaterialModule
  ],
  exports: [
    CommonModule,
    MaterialModule
  ],
  providers: [
    DateTimeProviderService
  ]
})
export class SharedModule { }
