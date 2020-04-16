import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';



@Injectable()
export class GridService{
	constructor(private http: HttpClient){}
	
	getGridMetaData(gridId){
		let payload ={"id": gridId};
		//Map this?
		return this.http.post('http://localhost:8080/grid/gridMeta',payload);
	}

}