import { Component, OnInit } from '@angular/core';
import {AuthorizationService} from '../../services/authorizationService';

@Component({
  selector: 'app-authorization',
  templateUrl: './authorization.component.html',
  styleUrls: ['./authorization.component.css']
})
export class AuthorizationComponent implements OnInit {

  constructor(private authorizationService: AuthorizationService) { }
  
  userName: String; 
  password: String; 
  sucsesfulLogin: boolean;
  ngOnInit() {
  }
  
  authorizeLogin(){
  	authorizationService.submitAuthorization().subscribe(sucsesfulLogin => {
      this.sucsesfulLogin = sucsesfulLogin;
  	})
  }
  
  

}
