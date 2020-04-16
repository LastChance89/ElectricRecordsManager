import { Component,OnInit,Input ,EventEmitter,Output, OnChanges, SimpleChanges } from '@angular/core';

import {ClientService} from '../../services/ClientService.service'
import { Observable } from 'rxjs'

import {ClientInfo} from '../../models/ClientInfo.model'
import {GridService} from '../../services/gridService.service'

@Component({
  selector: 'user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
  providers:[ClientService]
})



export class User  implements OnInit {
	constructor(private clientService: ClientService, private gridService: GridService) { }
	
	gridColumns :any;
	//_userList: any;
	recordList: any[];


	@Input() clientInfo: ClientInfo; 

	@Input()_userList
	
	
	@Output() records = new EventEmitter();

	/*
	ngOnChanges(changes: SimpleChanges){
		for(const name in changes){
			if(changes.hasOwnProperty(name)){
				switch(name){
					case 'clientInfo':{
						if(changes['clientInfo'].currentValue != changes['clientInfo'].previousValue){
							this.userList = []
							changes['clientInfo'].currentValue.forEach(element => {
								this.userList.push(element);
							});
						}
						
					}
				}
			}
		}
	}
*/
	ngOnInit(){
		this.gridService.getGridMetaData(2).subscribe(gridMeta => {
			this.gridColumns = gridMeta;
			});
		this.clientService.getAllUsers().subscribe(userList => {
			this._userList = userList
		});
	}

	getUserRecords(e){
		e.preventDefault();
		console.log("Pants");
		this.clientService.getClientRecords(this.clientInfo.accountNumber).subscribe(recordList=>{
		this.records.emit(recordList);
		})

	}

}