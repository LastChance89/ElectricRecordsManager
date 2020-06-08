import { Component,OnInit,Input ,EventEmitter,Output, OnChanges, SimpleChanges } from '@angular/core';

import {ClientService} from '../../services/ClientService.service'
import { Observable } from 'rxjs'

import {Client} from '../../models/client.model'
import {GridService} from '../../services/gridService.service'
import { ColDef } from 'ag-grid-community';
import {GridRenderer} from '../../grid/custom-grid-renderer.component'


@Component({
  selector: 'client-display',
  templateUrl: './client-display.component.html',
  styleUrls: ['./client-display.component.css'],
  providers:[ClientService]
})



export class ClientDisplay  implements OnInit {
	constructor(private clientService: ClientService, private gridService: GridService) { }
	
	gridColumns : ColDef[];
	recordList: any[];

	@Input() client: Client; 

	@Input()_clientList

	@Output() records = new EventEmitter();


	ngOnInit(){
		this.gridService.getGridMetaData(2).subscribe(gridMeta => {
			this.gridColumns = gridMeta;
		});
		
		this.clientService.getAllClients().subscribe(clientList => {
			this._clientList = clientList
		});
	}

	getUserRecords(e){
		e.preventDefault();
		this.clientService.getClientRecords(this.client.accountNumber).subscribe(recordList=>{
			this.records.emit(recordList);
		})
	}

}