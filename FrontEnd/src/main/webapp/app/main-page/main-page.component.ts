import { Component, OnInit } from '@angular/core';
import {AuthorizationService} from '../services/authorizationService.service';
import { DashBoardServiceService } from '../services/dash-board-service.service';
import { DashBoard } from '../models/dash-board.model';

@Component({
  selector: 'main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  constructor(private authorizationService: AuthorizationService, private daashBoardSerivce: DashBoardServiceService) { }


  private dashBoard:DashBoard;

  ngOnInit() {
    //Sets context. Required for moving to the new page and keeping sessions setup correctly. 
    //Add error handling here
  	this.authorizationService.setupContext().subscribe(
      response =>{
        console.log("context initalized")
      },
      error =>{
        console.log("Unable to setup UserContext")
      }
    );

    //Implement me. 
    this.daashBoardSerivce.getDashBoardData().subscribe(result =>{
      this.dashBoard = result;
    });

  }

  
  logout(){
    this.authorizationService.logUserOut().subscribe(result =>{
    }
    ,error => {
      console.log("Unable to logout user.")
    })
  }
}
