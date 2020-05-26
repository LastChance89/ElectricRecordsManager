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
    //Sets context. Required for moving to the new page. 
    //Add error handling here
  	this.authorizationService.setupContext().subscribe(
      response =>{
      },
      error =>{
        console.log("Unable to setup UserContext")
      }
    );
  }

}
