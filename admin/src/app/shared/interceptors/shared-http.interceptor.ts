import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../../environments/environment';

@Injectable()
export class SharedHttpInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (environment.production) {
      const newUrl = `${environment.apiUrl}${req.url}`;
      const newReq: HttpRequest<any> = req.clone({ url: newUrl });
      return next.handle(newReq);
    }
    return next.handle(req);
  }
}
