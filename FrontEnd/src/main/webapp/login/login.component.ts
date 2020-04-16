import { Component, OnInit } from '@angular/core';
import { AuthorizationService } from '../services/authorizationService.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authorizationService: AuthorizationService,

    private router: Router) { }


  userName: string;
  password: string;
  sucsesfulLogin: boolean;

  errorMsg: string;

  ngOnInit() {
  }

  authorizeLogin(e) {
    e.preventDefault();
    this.authorizationService.submitAuthorization(this.userName, this.password).subscribe(
      responseData => {
          //Cross Tab capabilities. 
          sessionStorage.setItem('username', this.userName);
          sessionStorage.setItem('token', responseData['token']);
          console.log(sessionStorage);
          this.router.navigate(['application']);
      },
      error => {
          if(error.status === 401){
            this.errorMsg = error.error;
          }
          else{
            this.errorMsg = error.error;
          }
        }
      )
  }

  isUserLoggedIn() {
    return sessionStorage.getItem('username') !== null;
  }


}
