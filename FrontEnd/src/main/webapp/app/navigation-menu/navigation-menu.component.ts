import { Component, OnInit } from '@angular/core';
import { Observable, Subject } from 'rxjs'
import { NgbModal, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';
import { SharedPopupModalComponent } from '../shared-popup-modal/shared-popup-modal.component'
import { JwtHelperService } from "@auth0/angular-jwt";


@Component({
  selector: 'app-navigation-menu',
  templateUrl: './navigation-menu.component.html',
  styleUrls: ['./navigation-menu.component.css']
})
export class NavigationMenuComponent implements OnInit  {

  show: boolean = false;
  userRoles  = new Array(2);
  subject = new Subject<boolean>();
  showAdminRoutes: boolean;

  ngOnInit() {
    let roles = JSON.parse(atob(sessionStorage.getItem('token').split('.')[1]))['roles'];
    //@TODO: change this so it only happens once.
    for(var key in Object.keys(roles)){   
      this.userRoles.push(roles[key]['authority']);
    }
   
   
  }

  constructor(private modalService: NgbModal) {

  }

  

  //might not need this based on input?
  showShowHideModel() {
    let options: NgbModalOptions = {
      backdrop: 'static',
      centered: true,
    };
    const modalRef = this.modalService.open(SharedPopupModalComponent, options);
  }
}
