import { Component, OnInit } from '@angular/core';
import {AuthorizationService} from '../services/authorization-service.service';
import { DashBoardService } from '../services/dash-board-service.service';
import { DashBoard } from '../models/dash-board.model';
import { ModalService } from '../services/modal-service.service';

@Component({
  selector: 'main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  constructor(private authorizationService: AuthorizationService, private daashBoardSerivce: DashBoardService,private modalService : ModalService) { }


  private dashBoard:DashBoard;

  ngOnInit() {
    //Sets context. Required for moving to the new page and keeping sessions setup correctly. 
    //Add error handling here
  	this.authorizationService.setupContext().subscribe(
      response =>{
        console.log("context initalized")
      },
      error =>{
        this.modalService.openMessageModal(true, error.error.message);
      }
    );

    //Implement me. 
    this.daashBoardSerivce.getDashBoardData().subscribe(result =>{
      this.dashBoard = result;
    },
		error =>{
		  this.modalService.openMessageModal(true, error.error.message);
		});

  }

  
  logout(){
    this.authorizationService.logUserOut().subscribe(result =>{
    }
    ,error => {
      this.modalService.openMessageModal(true, error.error.message);
    })
  }
}
