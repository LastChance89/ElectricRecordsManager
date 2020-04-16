import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
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


	createAccount(userName, password, hint){
		let payload ={"userName":userName,"password":password,"hint":hint}
		return this.http.post('http://localhost:8080/power/checkLogin/createAccount',payload);
	}

	handleEerror() {
		console.log("B");
	}

}