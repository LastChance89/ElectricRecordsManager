import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import {AuthorizationService} from '../services/authorization-service.service'
import { User } from '../models/User.model';
import { ModalService } from '../services/modal-service.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  
  public user: User  = new User();
  public fieldsCompelted = true;

  constructor(private authorizationService :AuthorizationService,private modalService : ModalService, private location: Location) { }

  
  ngOnInit() {
  }

  createUser(e){
    e.preventDefault;
    
    this.authorizationService.createAccount(this.user).subscribe(result =>{
      this.modalService.openMessageModal(false, result['message']);
    },
    error =>{
      this.modalService.openMessageModal(true, error.error.message);
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

  backToLoginPage(){
    this.location.back();
  }

}
