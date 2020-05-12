import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';
import {AuthorizationService} from './authorizationService.service';

@Injectable()
export class AuthenticationInterceptorService implements HttpInterceptor {
	constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler) {
	//This is checking if the session storage has a username and a token. Cna make this only token. 
    if (sessionStorage.getItem('username') && sessionStorage.getItem('token')) {
     //Creates a new request, adds a header so the backend knows its authorized.
     //Need to fire off a update the time in the spring framework. 
      req = req.clone({
        setHeaders: {
          Authorization: sessionStorage.getItem('token')
        }
      })
    }
    return next.handle(req);

  }


}