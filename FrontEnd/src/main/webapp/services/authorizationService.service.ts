import { Injectable } from '@angular/core';
import { HttpClient,HttpBackend,HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User }from '../models/User.model'
import { map } from 'rxjs/operators';
import { catchError } from 'rxjs/operators';
import { ValueCache } from 'ag-grid-community';

@Injectable()
export class AuthorizationService {

	constructor(private http: HttpClient, private httpBackend: HttpBackend) { 
	}

	submitAuthorization(userName, password): Observable<any> {
		let payload = { "userName": userName, "password": password };
		return this.http.post<Response>('/power/authorization/userLogin', payload)
	}
	checkLogin() {
		return this.http.post('/power/checkLogin/checkLoggedIn', '');
	}

	setupContext() {
		return this.http.post('/power/checkLogin/setContext', '');
	}

	createAccount(user:User){
		return this.http.post<User>('/power/authorization/createAccount',user);
	}

	validateAndRefresh(req){
		
		//return this.http.post<Response>('/power/checkLogin/keepAcitve', sessionStorage.getItem('token'));
		//We use a HttpBackend handler in order for the HTTPInterceptor to not intercept and cause an infinate loop
		return this.httpBackend.handle(new HttpRequest(<any>req.method, '/power/checkLogin/keepAcitve', sessionStorage.getItem('token')));
	}
	logUserOut(){
		return this.http.post<boolean>('/power/authorization/logOut','')
	}

}