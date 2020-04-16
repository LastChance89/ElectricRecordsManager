import { Component, OnInit } from '@angular/core';
import {ClientInfo} from '../models/ClientInfo.model'


@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit {

	constructor() { }

	ngOnInit() {
	}
	
	records:any

	//make me a list at some point
	clientInfo: any;
  	
	setRetrievedUsers(user){
		this.clientInfo = user;
	}
	
	setRecords(records){
		this.records = records;
	}


}
