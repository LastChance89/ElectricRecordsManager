import { Component, OnInit } from '@angular/core';
import { AuthorizationService } from '../services/authorizationService.service';
import { Router, ActivatedRoute } from '@angular/router';
import {SystemSettingServiceService} from '../services/system-setting-service.service'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authorizationService: AuthorizationService, private systemSetter: SystemSettingServiceService,
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


  userName: string;
  password: string;
  sucsesfulLogin: boolean;

  errorMsg: string;

  ngOnInit() {}

  authorizeLogin(e) {
    e.preventDefault();
    this.authorizationService.submitAuthorization(this.userName, this.password).subscribe(
      responseData => {
          this.systemSetter.setupSession(this.userName, responseData['token'])
          this.router.navigate(['application']);
      },
      error => {
            this.errorMsg = error.error;
        }
      )
  }

  isUserLoggedIn() {
    return sessionStorage.getItem('username') !== null;
  }
}
