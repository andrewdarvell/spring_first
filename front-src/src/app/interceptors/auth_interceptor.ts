import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import { Injectable } from '@angular/core';
import {TOKEN_KEY} from '../service/store.service';

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem(TOKEN_KEY);
    if (token === null) {
      return next.handle(req);
    }
    const newReq = req.clone({
      headers: req.headers
        .set('X-Auth-Token', token)
    });
    return next.handle(newReq);
  }

}
