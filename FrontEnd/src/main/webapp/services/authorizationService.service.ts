import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User }from '../models/User.model'
import { map } from 'rxjs/operators';
import { catchError } from 'rxjs/operators';

@Injectable()
export class AuthorizationService {
	constructor(private http: HttpClient) { }

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

	//@TODO: Make me correct. 
	handleEerror() {
		console.log("B");
	}

}