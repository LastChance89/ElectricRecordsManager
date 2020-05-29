import { HostListener, Component,ChangeDetectorRef  } from '@angular/core';
import {SystemSettingServiceService} from '../services/system-setting-service.service'
import { AuthorizationService } from '../services/authorizationService.service';
import { Router } from '@angular/router';
import { NgbModal, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';
import {MessageModalComponent} from '../modals/message-modal//message-modal.component'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent {

  options: NgbModalOptions = {
    backdrop: 'static',
    centered: true,
  };

  private  title = 'app';
  private showMenu = false;
  private loggedInUser = null;
  constructor(private systemSetter: SystemSettingServiceService, private authorizationService: AuthorizationService, private router: Router,private modalService: NgbModal){
    //Observable that once sessionStorage is set which means user is logged in,
    //displays the menu and the logged in user. . 
    systemSetter.changeEmitted$.subscribe(result =>{
      this.loggedInUser = result[0];
      this.showMenu = result[1];
    })
  }
  
  logout(){
    this.authorizationService.logUserOut().subscribe(result =>{
      //Ensure we dont navigate befre the session is cleared. 
      this.systemSetter.clearSession().then(value =>{
        this.router.navigate(['login'])
      })
    }
    ,error => {
      const modelRef = this.modalService.open(MessageModalComponent,this.options);
            modelRef.componentInstance.message = error.error;
            modelRef.componentInstance.isError = true;
    })
  }

}
