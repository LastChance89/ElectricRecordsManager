import { Component } from '@angular/core';
import { Observable } from 'rxjs'
import { NgbModal, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';
import { SharedPopupModalComponent } from '../shared-popup-modal/shared-popup-modal.component'

@Component({
  selector: 'powerMenu',
  templateUrl: './powerMenu.component.html',
  styleUrls: ['./powerMenu.component.css'],
})

export class powerMenu {


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
