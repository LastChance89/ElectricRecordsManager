import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ClientService } from '../../services/clientService.service';

@Component({
  selector: 'app-file-modal',
  templateUrl: './file-modal.component.html',
  styleUrls: ['./file-modal.component.css']
})
export class FileModalComponent implements OnInit {

  constructor(public activeModal: NgbActiveModal, private clientService: ClientService) { }

  ngOnInit() {
  }

  
  selectedFiles : File[] = [];
  isLoading = true;

  //Currently we only can add 1, however we may add more later for bulk upload
  updateFile(event){
    let files = event.target.files;
    for(let i = 0; i< files.length; i++){
      this.selectedFiles.push(files[i]);
      
    }
  }

  loadUsers(){

    this.isLoading = false;

    const fromData = new FormData()
    this.selectedFiles.forEach(element => {
      fromData.append("files", element,element.name);
    });
    this.clientService.initalLoadClient(fromData).subscribe(result => {
      console.log(result);
      this.isLoading = result;
    } )
  }

  close(){
    this.activeModal.close();
  }
}
