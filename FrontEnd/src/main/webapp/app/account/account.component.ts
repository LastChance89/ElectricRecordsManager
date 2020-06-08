import { Component, OnInit } from '@angular/core';
import {AuthorizationService} from '../services/authorizationService.service'
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

  constructor(private authorizationService :AuthorizationService,private modalService : ModalService) { }

  
  ngOnInit() {
  }

  createUser(){
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

}
