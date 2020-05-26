import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {ClientInfo} from '../models/ClientInfo.model'
import { Observable } from 'rxjs';
import { UserRecord } from'../models/userRecord.model';

@Injectable()
export class ClientService {
	 constructor(private http: HttpClient,  private clientInfo: ClientInfo) { }

	initalLoadClient(fromData): Observable<boolean>{
		return this.http.post<boolean>('/power/data/initalize',fromData);
	}
	
	getClient(searchOption,searchCritera,inputValue) : Observable<ClientInfo>{
		let payload = {"searchOpt":searchOption,"searchCritera":searchCritera,"inputVal":inputValue}
		return this.http.post<ClientInfo>('/power/data/userSearch',payload );
	}
	
	getClientRecords(accountNumber): Observable<any>{
		let payload = {"accountNumber":accountNumber}
		return this.http.post<UserRecord>('/power/data/getRecords',payload );
	}
	
	getAllUsers(): Observable<any>{
		return this.http.post('/power/data/getAllUsers','');
	}

	//Need to impliment me. 
	public handleError(){
	console.log("bleh");
	}

}