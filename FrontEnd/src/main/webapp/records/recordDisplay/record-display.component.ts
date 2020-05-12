import { Component, OnInit, Input,  } from '@angular/core';
import { Observable } from 'rxjs'
import {AgGridModule } from 'ag-grid-angular';
import {GridService} from '../../services/gridService.service'
import { ActivatedRoute } from "@angular/router";
import { map } from 'rxjs/operators';
import {ClientInfo} from '../../models/ClientInfo.model'
import {RecordService} from '../../services/recordServices.service';

@Component({
  selector: 'record-display',
  templateUrl: './record-display.component.html',
  styleUrls: ['./record-display.component.css']
})

export class RecordDisplay{
	constructor(private gridService:GridService, private route: ActivatedRoute, private recordService: RecordService){}
	
	records: any;
	accNum : number;
	gridColumns:any;
	x :any;
	ngOnInit() {
		this.accNum =parseInt(this.route.snapshot.paramMap.get('accNum'),10);
	
		this.gridService.getGridMetaData(1).subscribe(gridMeta => {
		 this.gridColumns = gridMeta;
		 });
		
		if(this.accNum != undefined){
			this.recordService.getUserRecords(this.accNum).subscribe(records => {
				this.records = records;
			})
		}
		
	}
}