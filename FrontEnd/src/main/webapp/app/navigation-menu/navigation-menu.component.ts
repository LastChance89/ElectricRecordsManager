import { Component, OnInit } from '@angular/core';
import {Subject } from 'rxjs'
import { ModalService } from '../services/modal-service.service';


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
  constructor(private modalService : ModalService) {

  }

  ngOnInit() {
    let roles = JSON.parse(atob(sessionStorage.getItem('token').split('.')[1]))['roles'];
    //@TODO: change this so it only happens once.
    for(var key in Object.keys(roles)){   
      this.userRoles.push(roles[key]['authority']);
    }
   
   
  }



  showShowHideModel() {
    const modalRef = this.modalService.openFileModal();
  }
}
