import { Injectable, Input } from '@angular/core';
import { NgbModalOptions, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FileModalComponent } from '../modals/file-modal/file-modal.component';
import { MessageModalComponent } from '../modals/message-modal/message-modal.component';

@Injectable({
  providedIn: 'root'
})
export class ModalService {

  constructor(private ngbModal: NgbModal) { }


   options: NgbModalOptions = {
    backdrop: 'static',
    centered: true,
  };

  openFileModal(){
    const modalRef = this.ngbModal.open(FileModalComponent, this.options);
  }
  

  openMessageModal(isError, message){
    const modelRef = this.ngbModal.open(MessageModalComponent,this.options);
    modelRef.componentInstance.message = message;
    modelRef.componentInstance.isError = isError;
  }


}
