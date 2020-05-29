import { Component, OnInit, Input } from '@angular/core';
import {NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';

@Component({
  selector: 'app-message-modal',
  templateUrl: './message-modal.component.html',
  styleUrls: ['./message-modal.component.css']
})
export class MessageModalComponent implements OnInit {

  constructor(public activeModal: NgbActiveModal,private router: Router) { }

  @Input() message: String;
  @Input() isError: boolean = false;


  
  isLoading = true;

  ngOnInit() {
  }
  navigateHome(){
    this.activeModal.close();
    this.router.navigate(['login']);
  }
  
  closeModel(){
    this.activeModal.close();
  }
  
}
