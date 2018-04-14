import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { MaterialModule } from './material.module';
import { DateTimeProviderService } from './services/date-time-provider.service';
import { SharedHttpInterceptor } from './interceptors/shared-http.interceptor';
import { ClipboardModule } from 'ngx-clipboard';

@NgModule({
  imports: [MaterialModule, HttpClientModule, ClipboardModule],
  exports: [CommonModule, MaterialModule, HttpClientModule, ClipboardModule],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: SharedHttpInterceptor,
      multi: true
    },
    DateTimeProviderService
  ]
})
export class SharedModule {}
