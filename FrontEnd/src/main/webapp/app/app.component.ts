import { HostListener, Component,ChangeDetectorRef  } from '@angular/core';
import {SystemSettingServiceService} from '../services/system-setting-service.service'
import { AuthorizationService } from '../services/authorizationService.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  private  title = 'app';
  private showMenu = false;
  private loggedInUser = null;
  constructor(private systemSetter: SystemSettingServiceService, private authorizationService: AuthorizationService, private router: Router){
    //Observable that once sessionStorage is set which means user is logged in,
    //displays the menu and the logged in user. . 
    systemSetter.changeEmitted$.subscribe(result =>{
      this.loggedInUser = result[0];
      this.showMenu = result[1];
    })
  }

  
  logout(){
    this.authorizationService.logUserOut().subscribe(result =>{
      this.systemSetter.clearSession();
      this.router.navigate(['login']);
    }
    ,error => {
      //Make me a popup. 
      console.log(error.status);
      console.log("An error has occured. Please contact your administrator.")
    })
  }

}
