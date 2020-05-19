import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {ClientInfo} from '../models/ClientInfo.model'
import { Observable } from 'rxjs';
import { UserRecord } from'../models/userRecord.model';
import {map} from 'rxjs/operators'; 


@Injectable()
export class ClientService {
	 constructor(private http: HttpClient,  private clientInfo: ClientInfo) { }

	initalLoadClient(fromData): Observable<boolean>{
	
		return this.http.post<boolean>('http://localhost:8080/power/data/initalize',fromData);
	}
	
	getClient(searchOption,searchCritera,inputValue) : Observable<ClientInfo>{

	let payload = {"searchOpt":searchOption,"searchCritera":searchCritera,"inputVal":inputValue}
	/*Make the URL dynamic. */
	return this.http.post<ClientInfo>('http://localhost:8080/power/data/userSearch',payload );
	}
	
	/*Passin the thing here.*/
	getClientRecords(accountNumber): Observable<any>{
	let payload = {"accountNumber":accountNumber}
	return this.http.post<UserRecord>('http://localhost:8080/power/data/getRecords',payload );
	}
	
	getAllUsers(): Observable<any>{
		return this.http.post('http://localhost:8080/power/data/getAllUsers','');
	}

	//Need to impliment me. 
	public handleError(){
	console.log("bleh");
	}

}