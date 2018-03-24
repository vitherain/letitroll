import { Injectable } from '@angular/core';

@Injectable()
export class DateTimeProviderService {

  currentYear(): number {
    return new Date().getFullYear();
  }
}
