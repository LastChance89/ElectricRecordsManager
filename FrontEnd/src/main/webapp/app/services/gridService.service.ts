import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ColDef } from 'ag-grid-community';


@Injectable()
export class GridService{
	constructor(private http: HttpClient){}
	getGridMetaData(gridId){
		let payload ={"id": gridId};
		return this.http.post<ColDef[]>('/grid/gridMeta',payload);
	}
}