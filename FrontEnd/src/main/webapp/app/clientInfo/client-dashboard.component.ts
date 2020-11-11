import { Component, OnInit } from '@angular/core';
import { Client } from '../models/client.model';
import { UserRecord } from '../models/userRecord.model';
import { ClientService } from '../services/client-service.service';

@Component({
  selector: 'app-client-dashboard',
  templateUrl: './client-dashboard.component.html',
  styleUrls: ['./client-dashboard.component.css']
})
export class ClientDashboardComponent implements OnInit {

	constructor(private clientService: ClientService ) {}

	ngOnInit() {
	}
	
	records:Array<UserRecord>
	clients: Array<Client>;
  	
	setRetrievedClients(clients){
		this.clients = clients;
	}
	
	setRecords(records){
		this.records = records;
	}
}
