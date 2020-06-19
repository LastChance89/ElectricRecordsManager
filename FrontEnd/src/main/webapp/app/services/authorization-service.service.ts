import { Injectable } from '@angular/core';
import { HttpClient,HttpBackend,HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User }from '../models/User.model'
@Injectable()
export class AuthorizationService {

	constructor(private http: HttpClient, private httpBackend: HttpBackend) { 
	}

	submitAuthorization(userName, password): Observable<any> {
		let payload = { "userName": userName, "password": password };
		return this.http.post<Response>('/power/authorization/userLogin', payload)
	}
	checkLogin() {
		return this.http.post<Observable<boolean>>('/power/checkLogin/checkLoggedIn', '');
	}

	setupContext() {
		return this.http.post('/power/checkLogin/setContext', '');
	}

	createAccount(user:User){
		return this.http.post<User>('/power/authorization/createAccount',user);
	}

	validateAndRefresh(req){
		return this.http.post<Response>('/power/checkLogin/keepAcitve', sessionStorage.getItem('token'));
	}
	logUserOut(){
		return this.http.post<boolean>('/power/authorization/logOut','')
	}
	getUserHint(userName: String){
		let payload = userName
		return this.http.post<Response>('/power/authorization/getHint',userName);
	}
}