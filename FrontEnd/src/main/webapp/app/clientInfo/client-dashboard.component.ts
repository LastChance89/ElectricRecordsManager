import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-client-dashboard',
  templateUrl: './client-dashboard.component.html',
  styleUrls: ['./client-dashboard.component.css']
})
export class ClientDashboardComponent implements OnInit {

	constructor() { }

	ngOnInit() {
	}
	
	records:any

	//make me a list at some point
	client: any;
  	
	setRetrievedClients(client){
		this.client = client;
	}
	
	setRecords(records){
		this.records = records;
	}


}
