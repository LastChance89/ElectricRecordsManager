import { Component, OnInit } from '@angular/core';
import {AuthorizationService} from '../services/authorizationService.service';

@Component({
  selector: 'main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  constructor(private authorizationService: AuthorizationService) { }

  ngOnInit() {
   	
  	//Now we do a quick check that login was really sucsesfull, and this SHOULD set the context. 
  	//This is what transfers the session across multiple tabs, removing makes the spring context null
  	this.authorizationService.setupContext().subscribe(
  	response =>{
  		console.log("ContextSetup");
  	});
  }

}
