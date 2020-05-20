import { Injectable } from '@angular/core';
import { HttpClient,HttpBackend,HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User }from '../models/User.model'
import { map } from 'rxjs/operators';
import { catchError } from 'rxjs/operators';
import { ValueCache } from 'ag-grid-community';

@Injectable()
export class AuthorizationService {

	private httpClient: HttpClient;
	constructor(private http: HttpClient, private handler: HttpBackend) { 
		this.httpClient = new HttpClient(handler);
	}

	submitAuthorization(userName, password): Observable<any> {
		let payload = { "userName": userName, "password": password };
		return this.http.post<Response>('http://localhost:8080/power/authorization/userLogin', payload)
		
	}
	checkLogin() {
		return this.http.post('http://localhost:8080/power/checkLogin/checkLoggedIn', '');
	}

	setupContext() {
		return this.http.post('http://localhost:8080/power/checkLogin/setContext', '');
	}


	createAccount(user:User){
		//let payload ={"userName":userName,"password":password,"hint":hint}
		return this.http.post<User>('http://localhost:8080/power/authorization/createAccount',user);
	}

	validateAndRefresh(req){
		//Use backend handler instead of post to ensure the method is not caught by intercept resulting in infinite loop. 
		const httpRequest = new HttpRequest(<any>req.method, 'http://localhost:8080/power/checkLogin/keepAcitve', sessionStorage.getItem('token'));
		return this.handler.handle(httpRequest);
	}

	//@TODO: Make me correct. 
	handleEerror() {
		console.log("B");
	}

}