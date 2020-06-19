import { Component, OnInit, Input,  } from '@angular/core';
import { Observable } from 'rxjs'
import {AgGridModule } from 'ag-grid-angular';
import {GridService} from '../../services/gridService.service'
import { ActivatedRoute } from "@angular/router";
import { map } from 'rxjs/operators';
import {Client} from '../../models/Client.model'
import {RecordService} from '../../services/recordServices.service';
import { ModalService } from '../../services/modal-service.service';

@Component({
  selector: 'record-display',
  templateUrl: './record-display.component.html',
  styleUrls: ['./record-display.component.css']
})

export class RecordDisplay{
	constructor(private gridService:GridService, private route: ActivatedRoute, private recordService: RecordService,private modalService : ModalService){}
	
	records: any;
	accNum : number;
	gridColumns:any;
	client: Client;

	ngOnInit() {
		this.accNum =parseInt(this.route.snapshot.paramMap.get('accNum'),10);
		this.gridService.getGridMetaData(1).subscribe(gridMeta => {
			this.gridColumns = gridMeta;
		}, 
		error =>{
			this.modalService.openMessageModal(true, error.error.message);
		});
		
		if(this.accNum != undefined){
			this.recordService.getUserRecords(this.accNum).subscribe(clientReport => {
				this.records = clientReport.records;
				this.client = clientReport.client;
			})
		}
	}
}