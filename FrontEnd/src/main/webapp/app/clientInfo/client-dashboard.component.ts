import { Component, OnInit } from '@angular/core';
import {Client} from '../models/client.model'


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
	client: Client;
  	
	setRetrievedUsers(user){
		this.client = user;
	}
	
	setRecords(records){
		this.records = records;
	}


}
