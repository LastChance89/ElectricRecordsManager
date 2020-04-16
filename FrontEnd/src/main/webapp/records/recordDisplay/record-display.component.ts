import { Component, OnInit, Input } from '@angular/core';
import { Observable } from 'rxjs'
import {AgGridModule } from 'ag-grid-angular';
import {GridService} from '../../services/gridService.service'

@Component({
  selector: 'record-display',
  templateUrl: './record-display.component.html',
  styleUrls: ['./record-display.component.css']
})

export class RecordDisplay{
	constructor(private gridService:GridService){}
	
	@Input() records: any;
	
	gridColumns:any; 
	
	ngOnInit() {
		this.gridService.getGridMetaData(1).subscribe(gridMeta => {
		 this.gridColumns = gridMeta;
		 });
	}
	
	
}