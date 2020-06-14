import { Component, OnInit } from '@angular/core';
import { AuthorizationService } from '../../services/authorizationService.service';
import { ModalService } from '../../services/modal-service.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-account-help',
  templateUrl: './account-help.component.html',
  styleUrls: ['./account-help.component.css']
})


export class AccountHelpComponent implements OnInit {


  private userName: String;
  private message: String = "Enter UserName."; 
  private userHintFound: boolean = false;
  private isLoading: boolean = false;
  fieldsCompelted : boolean = false;

  constructor(public activeModal: NgbActiveModal, private authorizationService: AuthorizationService, private modalService : ModalService) { }

  ngOnInit() {
  }

  getHint(){
    this.isLoading = true; 
    return this.authorizationService.getUserHint(this.userName).subscribe(result =>{
      if(result["error"] != undefined){
        this.message = "User Not found";
        this.userHintFound = false; 
        this.isLoading = false;
      }
      else{
        this.message = "Password Hint: "+ result["message"];
        this.userHintFound = true; 
        this.isLoading = false;
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
}
