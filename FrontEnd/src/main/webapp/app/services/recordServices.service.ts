import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ClientReport } from '../models/client-report.model';

@Injectable()
export class RecordService{
	constructor(private http: HttpClient) { }
	getUserRecords(accNum: number){
		let payload = {"accNum":accNum}
		return this.http.post<ClientReport>('/power/data/getRecords',payload);
	}
}