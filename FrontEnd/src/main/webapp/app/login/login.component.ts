import { Component, OnInit } from '@angular/core';
import { AuthorizationService } from '../services/authorizationService.service';
import { Router, ActivatedRoute } from '@angular/router';
import {SystemSettingServiceService} from '../services/system-setting-service.service'
import { NgbModal, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';
import {MessageModalComponent} from '../modals/message-modal//message-modal.component'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  
  private userName: string;
  private password: string;
  private sucsesfulLogin: boolean;
  private errorMsg: string;

  options: NgbModalOptions = {
    backdrop: 'static',
    centered: true,
  };

  constructor(private authorizationService: AuthorizationService, private systemSetter: SystemSettingServiceService,private modalService: NgbModal,
    private router: Router) {
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


  ngOnInit() {}

  authorizeLogin(e) {
    e.preventDefault();
    this.authorizationService.submitAuthorization(this.userName, this.password).subscribe(
      responseData => {
          this.systemSetter.setupSession(this.userName, responseData['token'])
          this.router.navigate(['application']);
      },
      error => {
            const modelRef = this.modalService.open(MessageModalComponent,this.options);
            modelRef.componentInstance.message = error.error;
            modelRef.componentInstance.isError = true;
        }
      )
  }

  isUserLoggedIn() {
    return sessionStorage.getItem('username') !== null;
  }
}
