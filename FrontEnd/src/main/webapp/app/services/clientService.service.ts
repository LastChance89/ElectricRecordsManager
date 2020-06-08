import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Client} from '../models/Client.model'
import { Observable } from 'rxjs';
import { UserRecord } from'../models/userRecord.model';

@Injectable()
export class ClientService {
	 constructor(private http: HttpClient) { }

	initalLoadClient(fromData): Observable<boolean>{
		return this.http.post<boolean>('/power/data/initalize',fromData);
	}
	
	getClient(searchOption,searchCritera,inputValue) : Observable<Client>{
		let payload = {"searchOpt":searchOption,"searchCritera":searchCritera,"inputVal":inputValue}
		return this.http.post<Client>('/power/data/clientSearch',payload );
	}
	
	getClientRecords(accountNumber): Observable<any>{
		let payload = {"accountNumber":accountNumber}
		return this.http.post<UserRecord>('/power/data/getRecords',payload );
	}
	
	getAllClients(): Observable<any>{
		return this.http.post('/power/data/getAllClients','');
	}

	//Need to impliment me. 
	public handleError(){
	console.log("bleh");
	}

}