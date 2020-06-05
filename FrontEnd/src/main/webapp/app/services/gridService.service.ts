import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ColDef } from 'ag-grid-community';
import { map } from "rxjs/operators";
import { GridRenderer } from '../grid/custom-grid-renderer.component';

@Injectable()
export class GridService{
	constructor(private http: HttpClient){}

	values; 
	
	getGridMetaData(gridId){
		let payload ={"id": gridId};
		return this.http.post<ColDef[]>('/grid/gridMeta',payload).pipe(map(	this.formatGridData));
	}

	formatGridData(gridMeta){
		gridMeta.forEach(element => {
			//setup cellRenderers to format data on the grid. 
			switch(element.cellRenderer){
				case 'UNITS':{
					element.cellRenderer =unitsFormatter;
					break;
				}
				case 'DOLLAR':{
					element.cellRenderer =currencyFormatter;
					break;
				}
				//we use null because it breaks the hyperlink fields otherwise. 
				default: {
					element.cellRenderer = null;
					break;
				}
			}

			//Setup hyperlink for accountNumber. 
			switch(element.field){
				case('accountNumber'):{
					element.cellRendererFramework = GridRenderer;
					element.cellRendererParams = {
					inRouterLink: element
					}
					break;
				}
				default:{
					break;
				}
			}
		});

		//We have to set the functions here because when we call this function in the map 
		//we cant access fields outside. 
		function currencyFormatter(params: any){
			return '$'+ params.value; 
		}

		function unitsFormatter(params: any){
			return params.value +" kWh";
		}

		return gridMeta;
	}

}