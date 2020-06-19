import { Component,OnInit,EventEmitter,Output} from '@angular/core';
import {ClientService} from '../../services/client-service.service'

import {Client} from '../../models/client.model'

import { ModalService } from '../../services/modal-service.service';

@Component({
  selector: 'client-search',
  templateUrl: './client-search.component.html',
  styleUrls: ['./client-search.component.css',],
  providers:[ClientService]
})

export class ClientSearch  {
  constructor(private clientService: ClientService, private modalService : ModalService) { }
  

	searchField: string = "ACC_NUM";
	inputValue: string = "";
	searchCritera:string="EQUAL";
	
	@Output() retrievedClients = new EventEmitter<Array<Client>>();

	getClients() {
		this.clientService.getClients(this.searchField,this.searchCritera,this.inputValue).subscribe(clients => {
			this.retrievedClients.emit(clients);
		},
		error =>{
		  this.modalService.openMessageModal(true, error.error.message);
		})
  	}
}