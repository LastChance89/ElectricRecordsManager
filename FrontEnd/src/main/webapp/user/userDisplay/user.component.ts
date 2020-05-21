import { Component,OnInit,Input ,EventEmitter,Output, OnChanges, SimpleChanges } from '@angular/core';

import {ClientService} from '../../services/ClientService.service'
import { Observable } from 'rxjs'

import {ClientInfo} from '../../models/ClientInfo.model'
import {GridService} from '../../services/gridService.service'
import { ColDef } from 'ag-grid-community';
import {GridRenderer} from '../../grid/custom-grid-renderer.component'


@Component({
  selector: 'user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
  providers:[ClientService]
})



export class User  implements OnInit {
	constructor(private clientService: ClientService, private gridService: GridService) { }
	
	gridColumns : ColDef[];
	recordList: any[];


	@Input() clientInfo: ClientInfo; 

	@Input()_userList
	
	
	@Output() records = new EventEmitter();


	ngOnInit(){
		this.gridService.getGridMetaData(2).subscribe(gridMeta => {
			this.gridColumns = gridMeta;
			this.setupColumns();
			});
		this.clientService.getAllUsers().subscribe(userList => {
			this._userList = userList
		});
	}

	//Most likely move. 
	getUserRecords(e){
			e.preventDefault();
			this.clientService.getClientRecords(this.clientInfo.accountNumber).subscribe(recordList=>{
			this.records.emit(recordList);
			})
	}


	setupColumns(){
        this.gridColumns.forEach(column => {
          if(column.field === 'accountNumber'){
            column.cellRendererFramework = GridRenderer;
            column.cellRendererParams = {
            inRouterLink: column
            }
          }
        });
	}
}