import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import {AuthorizationService} from '../services/authorizationService.service';
import {SystemSettingServiceService} from './system-setting-service.service'

/*
This service is used for when we open a new tab so we can verify the user is logged in. 
*/
@Injectable()
export class LoggedInAuthenticatorService implements CanActivate{

  constructor(private router: Router,private authorizationService: AuthorizationService, private systemSetter: SystemSettingServiceService) { }
  

  canActivate() :boolean{
		//no sessionStorage initaliaized. 
		if(sessionStorage.length == 0){
			this.authorizationService.checkLogin().subscribe(response => {
					if(response['token']){
						this.systemSetter.setupSession(response['user'],response['token']);
						return true; 
					}	
					else{
						this.router.navigate(['login']);
					}
				},
				error =>{
					this.router.navigate(['login']);
				});
		}
		//If we have sessionStorage, we check the token to see if it exists. 
  		else if(sessionStorage.getItem('token')){  		
  			return true;
  		}
  		// Go to login screne, have teh user login. 
  		else{
  			this.router.navigate(['login']);
  		}
	}

}
  
