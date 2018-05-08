import { NavigationExtras } from '@angular/router';

export interface GoPayload {
  path: any[];
  query?: object;
  extras?: NavigationExtras;
}
