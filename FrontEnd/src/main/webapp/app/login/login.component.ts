import { Component, OnInit } from '@angular/core';
import { AuthorizationService } from '../services/authorization-service.service';
import { Router, ActivatedRoute } from '@angular/router';
import {SystemSettingServiceService} from '../services/system-setting-service.service'
import { ModalService } from '../services/modal-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  
  private userName: string;
  private password: string;

  private position: number; 

  constructor(private authorizationService: AuthorizationService, private systemSetter: SystemSettingServiceService,
    private modalService : ModalService, private router: Router,) {
      this.authorizationService.checkLogin().subscribe(response => {
        if(response['token']){
          this.systemSetter.setupSession(response['user'],response['token']);
          this.router.navigate(['application']);
        }	
        else{
          this.router.navigate(['login']);
        }
      },
      error =>{
        this.router.navigate(['login']);
      });
     }


  ngOnInit() {
  }

  authorizeLogin(e) {
    e.preventDefault();
    this.authorizationService.submitAuthorization(this.userName, this.password).subscribe(
      responseData => {
          this.systemSetter.setupSession(this.userName, responseData['token'])
          this.router.navigate(['application']);
      },
      error => {
            this.modalService.openMessageModal(true, error.error.message);
        }
      )
  }

  isUserLoggedIn() {
    return sessionStorage.getItem('username') !== null;
  }

  showHintDialog() {
    const modalRef = this.modalService.openHintModal();
  }


}
