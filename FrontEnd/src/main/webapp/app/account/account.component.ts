import { Component, OnInit } from '@angular/core';
import {AuthorizationService} from '../services/authorizationService.service'
import { User } from '../models/User.model';
import {MessageModalComponent} from '../modals/message-modal//message-modal.component'
import { NgbModal, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  
  public user: User  = new User();
  public fieldsCompelted = true;

  constructor(private authorizationService :AuthorizationService,private modalService: NgbModal) { }

  options: NgbModalOptions = {
    backdrop: 'static',
    centered: true,
  };

  
  ngOnInit() {
  }

  createUser(){
   
    this.authorizationService.createAccount(this.user).subscribe(result =>{
      const modelRef= this.modalService.open(MessageModalComponent,this.options);
     modelRef.componentInstance.message=result['message'];
     modelRef.componentInstance.isError = false;
     console.log(result);
    },
    error =>{
      const modelRef= this.modalService.open(MessageModalComponent,this.options);
      modelRef.componentInstance.message = error.error;
      modelRef.componentInstance.isError = true;
      }
    )
  }

  enableButton(){
    if( (this.user.userName!== "" && this.user.password !== "" && this.user.hint !== "") &&
        (this.user.userName !== undefined && this.user.password !== undefined && this.user.hint !== undefined)){
        this.fieldsCompelted = false;
    }
    else{
      this.fieldsCompelted =  true;
    }
    
  }

}
