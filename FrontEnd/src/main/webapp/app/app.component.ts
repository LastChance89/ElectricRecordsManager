import { HostListener, Component,ChangeDetectorRef  } from '@angular/core';
import {SystemSettingServiceService} from './services/system-setting-service.service'
import { AuthorizationService } from './services/authorization-service.service';
import { Router } from '@angular/router';
import {MessageModalComponent} from './modals/message-modal//message-modal.component'
import { ModalService } from './services/modal-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent {

  private  title = 'app';
  private showMenu = false;
  private loggedInUser = null;
  constructor(private systemSetter: SystemSettingServiceService, private authorizationService: AuthorizationService, private router: Router,private modalService : ModalService){
    //Observable that once sessionStorage is set which means user is logged in,
    //displays the menu and the logged in user. . 
    systemSetter.changeEmitted$.subscribe(result =>{
      this.loggedInUser = result[0];
      this.showMenu = result[1];
    },
    error =>{
      this.modalService.openMessageModal(true, error.error.message);
    }
)
  }
  
  logout(){
    this.authorizationService.logUserOut().subscribe(result =>{
      //Ensure we dont navigate befre the session is cleared. 
      this.systemSetter.clearSession().then(value =>{
        this.router.navigate(['login'])
      })
    }
    ,error => {
      this.modalService.openMessageModal(true, error.error.message);
    })
  }

}
