import { Component } from '@angular/core';
import { Observable } from 'rxjs'
import { NgbModal, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';
import { SharedPopupModalComponent } from '../shared-popup-modal/shared-popup-modal.component'

@Component({
  selector: 'app-navigation-menu',
  templateUrl: './navigation-menu.component.html',
  styleUrls: ['./navigation-menu.component.css']
})
export class NavigationMenuComponent {

  constructor(private modalService: NgbModal) { }

  title = 'powerMenu';
  show: boolean = false;



  //might not need this based on input?
  showShowHideModel() {
    let options: NgbModalOptions = {
      backdrop: 'static',
      centered: true,

    };
    const modalRef = this.modalService.open(SharedPopupModalComponent, options);
  }
}
