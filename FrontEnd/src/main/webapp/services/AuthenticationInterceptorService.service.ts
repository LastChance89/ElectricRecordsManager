import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpBackend, HttpClient, HttpResponse } from '@angular/common/http';
import { AuthorizationService } from './authorizationService.service';
import { JwtHelperService } from "@auth0/angular-jwt";

@Injectable()
export class AuthenticationInterceptorService implements HttpInterceptor {

  private httpClient: HttpClient;
  private refreshedToken;

  constructor(private handler: HttpBackend, private authorizationService: AuthorizationService) {
    this.httpClient = new HttpClient(handler);
  }

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    //This is checking if the session storage has a username and a token. Cna make this only token. 
    if (sessionStorage.getItem('username') && sessionStorage.getItem('token')) {
      // Every intecept we update the experation time if the toekn is not expired.  
      const helper = new JwtHelperService();
      //console.log("token EXP example: "  + helper.getTokenExpirationDate(sessionStorage.getItem('token')));

      console.log("Original Request Here boys:   " + req.url)

      console.log("Expired? " + helper.getTokenExpirationDate(sessionStorage.getItem('token')))

      //Ensure the token is not expired. 
      if (!helper.isTokenExpired(sessionStorage.getItem('token'))) {
        //This is the endpoint we want to hit. 
        const httpRequest = new HttpRequest(<any>req.method, 'http://localhost:8080/power/checkLogin/keepAcitve', sessionStorage.getItem('token'));
        //Here is where we will call the backend service to refresht the token. 
        this.handler.handle(httpRequest).subscribe(result => {
          //Verify we have correct return type. 
          if (result instanceof HttpResponse) {
            //TODO: Send me back to login page. 
            if (result.status === 401 || result.status == 403) {

            }
            else {
              //console.log(result);
              //result.status && result.body.token
              console.log("Original Request : " + req);

              //we udpate the session storage as well as the request, that way hte request has the new
              // token to send to the backend as well as the updated session storage keeping it. 
              sessionStorage.setItem('token', result.body["token"]);

              req = req.clone({
                setHeaders: {
                  Authorization: result.body["token"]
                }
              })
              
            }
          }
          else {
            req = req.clone({
              setHeaders: {
                Authorization: sessionStorage.getItem('token')
              }
            })
          }
        })
      }
    }
 
    //Move along sir. 
    return next.handle(req);

  }
  
}