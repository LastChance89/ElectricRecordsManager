import { Component,OnInit,EventEmitter,Output} from '@angular/core';
import {ClientService} from '../../services/ClientService.service'

import {ClientInfo} from '../../models/clientInfo.model'


import {Observable } from 'rxjs'




@Component({
  selector: 'user-search',
  templateUrl: './user-search.component.html',
  styleUrls: ['./user-search.component.css',],
  providers:[ClientService]
})

export class userSearch  {
  constructor(private clientService: ClientService) { }
  
	clientInfo: any; //change to a list
	searchField: string = "ACC_NUM";
	inputValue: string = "";
	searchCritera:string="EQUAL";
	
	setRetrievedUsers(a){
		console.log("FIRE!");

	}

	@Output() retrievedUsers = new EventEmitter<ClientInfo>();

	getUser() {
		this.clientService.getClient(this.searchField,this.searchCritera,this.inputValue).subscribe(clientInfo => {
		this.retrievedUsers.emit(clientInfo);
	})
	
  }

}