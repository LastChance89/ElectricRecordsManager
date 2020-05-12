import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class RecordService{

	constructor(private http: HttpClient) { }
	
	getUserRecords(accNum: number){
		let payload = {"accNum":accNum}
		return this.http.post('http://localhost:8080/power/data/getRecords',payload);
	}




}