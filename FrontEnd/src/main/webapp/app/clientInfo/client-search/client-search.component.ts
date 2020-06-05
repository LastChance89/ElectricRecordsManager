import { Component,OnInit,EventEmitter,Output} from '@angular/core';
import {ClientService} from '../../services/ClientService.service'

import {Client} from '../../models/client.model'


import {Observable } from 'rxjs'

@Component({
  selector: 'client-search',
  templateUrl: './client-search.component.html',
  styleUrls: ['./client-search.component.css',],
  providers:[ClientService]
})

export class ClientSearch  {
  constructor(private clientService: ClientService) { }
  
	client: any; //change to a list
	searchField: string = "ACC_NUM";
	inputValue: string = "";
	searchCritera:string="EQUAL";
	
	setRetrievedUsers(a){

	}

	@Output() retrievedUsers = new EventEmitter<Client>();

	getUser() {
		this.clientService.getClient(this.searchField,this.searchCritera,this.inputValue).subscribe(Client => {
			this.retrievedUsers.emit(Client);
		})
  	}
}