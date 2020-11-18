import { Component, OnInit } from '@angular/core';
import { AuthorizationService } from '../../services/authorization-service.service';
import { ModalService } from '../../services/modal-service.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-account-help',
  templateUrl: './account-help.component.html',
  styleUrls: ['./account-help.component.css']
})


export class AccountHelpComponent implements OnInit {


  
  private message: String = "Enter UserName."; 
  private userHintFound: boolean = false;
  private loading: boolean = false;
  fieldsCompelted : boolean = false;
  userName: String;
  constructor(public activeModal: NgbActiveModal, private authorizationService: AuthorizationService, private modalService : ModalService) { }

  ngOnInit() {
  }

  getHint(){
    this.loading = true; 
    return this.authorizationService.getUserHint(this.userName).subscribe(result =>{
      if(result["error"] != undefined){
        this.message = result["error"];
        this.userHintFound = false; 
        this.loading = false;
      }
      else{
        this.message = "Password Hint: "+ result["message"];
        this.userHintFound = true; 
        this.loading = false;
      }
    
    },
    error =>{
      this.close();
      this.modalService.openMessageModal(true, error.error.message);
    })
  }
  close(){
    this.activeModal.close();
  }
  enableButton(){
    this.fieldsCompelted = this.userName !== undefined && this.userName !== "" ? true : false;
  }

  getMessage(){
    return this.message;
  }

  isHintFound(){
    return this.userHintFound;
  }

  isLoading(){
    return this.loading;
  }

}
