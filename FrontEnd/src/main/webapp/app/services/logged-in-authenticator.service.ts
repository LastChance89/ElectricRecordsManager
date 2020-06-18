import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { AuthorizationService } from '../services/authorization-service.service';
import { SystemSettingServiceService } from './system-setting-service.service'
import { Observable } from 'rxjs/internal/Observable';
import { map } from 'rxjs/internal/operators/map';

/*
This service is used for when we open a new tab so we can verify the user is logged in. 
*/
@Injectable()
export class LoggedInAuthenticatorService implements CanActivate {

	constructor(private router: Router, private authorizationService: AuthorizationService, private systemSetter: SystemSettingServiceService) { }


	canActivate(): Observable<boolean> {
		return this.authorizationService.checkLogin().pipe(map(response => {
			if (response['token']) {
				this.systemSetter.setupSession(response['user'], response['token']);
				return true;
			}
			else {
				this.router.navigate(['login']);
				return false;
			}
		}

		));
	}

}

