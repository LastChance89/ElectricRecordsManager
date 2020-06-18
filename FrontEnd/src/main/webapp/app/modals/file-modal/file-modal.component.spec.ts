import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FileModalComponent } from './file-modal.component';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ClientService } from '../../services/clientService.service';
import { ModalService } from '../../services/modal-service.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('FileModalComponent', () => {
  let component: FileModalComponent;
  let fixture: ComponentFixture<FileModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FileModalComponent ],
      providers: [NgbActiveModal, ClientService, ModalService],
      imports: [HttpClientTestingModule]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FileModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
