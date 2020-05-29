import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpResponse } from '@angular/common/http';
import { AuthorizationService } from './authorizationService.service';
import { JwtHelperService } from "@auth0/angular-jwt";
import { Router } from '@angular/router';
import { SystemSettingServiceService } from './system-setting-service.service'

@Injectable()
export class AuthenticationInterceptorService implements HttpInterceptor {

  constructor(private authorizationService: AuthorizationService, private router: Router, private systemSetter: SystemSettingServiceService) {

  }



  intercept(req: HttpRequest<any>, next: HttpHandler) {

    //We dont want to check some routes, logout is one of them otherwise will log us back in. DOH. 
    const excludeRoutes: String[] = ['/power/authorization/logOut']
    const specalRoute: String[] = ['/power/checkLogin/setContext' ]

    //Special case taht requires specific settings. 
    if(req.url == '/power/checkLogin/setContext'){
        this.systemSetter.keepMenuOn();
        req = this.setupNewRequest(req);
    }
    else{
      if (excludeRoutes.indexOf(req.url) == -1) {
        //Check username and token just to ensure a user was properly authenticated. 
        if (sessionStorage.getItem('username') && sessionStorage.getItem('token')) {
          // Every intecept we update the experation time if the toekn is not expired.  
          const jwtHelper = new JwtHelperService();
  
          //Ensure the token is not expired. 
          if (!jwtHelper.isTokenExpired(sessionStorage.getItem('token'))) {
            this.authorizationService.validateAndRefresh(req).subscribe(result => {
              //Verify we have correct return type. 
              if (result instanceof HttpResponse) {
              
                  /*
                  I Update the session storage as well as the request header so that the token 
                  can exist across pages as well as new tabs / windows. 
                  */
                  sessionStorage.setItem('token', result.body["token"]);
                  req = this.setupNewRequest(req);
                
              }
              //This is needed because getting some strange result I need to trouble shoot. For now works. 
              else {
                this.systemSetter.keepMenuOn();
                req = this.setupNewRequest(req);
              }
            },
              error => {
                this.router.navigate(['login']);
              }
  
            )
          }
          else {
            this.systemSetter.clearSession();
            this.router.navigate(['login']);
          }
        }
      }
    }


    
    return next.handle(req);
  }

  //Required to keep authorization between tabs. 
  setupNewRequest(req: HttpRequest<any>) {
    return req.clone({
      setHeaders: {
        Authorization: sessionStorage.getItem('token')
      }
    })
  }

}