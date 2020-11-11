import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';


import { ClientService } from '../../services/client-service.service';
import { ModalService } from '../../services/modal-service.service';
import { ClientSearch } from './client-search.component';
import { Client } from '../../models/client.model';
import { of, throwError } from 'rxjs';

describe('ClientSearch', () => {
  let component: ClientSearch;
  let fixture: ComponentFixture<ClientSearch>;
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [FormsModule, HttpClientTestingModule],
      declarations: [ClientSearch],
      providers: [ModalService, ClientService],

    });
    TestBed.compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientSearch);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call getClients Method', ()=>{
    let mockClientService = TestBed.get(ClientService);
    spyOn(mockClientService,'getClients');
    spyOn(component.retrievedClients,'emit');

    mockClientService.getClients.and.returnValue(of(new Array<Client>()));
    component.getClients();
    expect(component.retrievedClients.emit).toHaveBeenCalled();
  });
  
  it('should throw error on error', ()=>{
    let mockClientService = TestBed.get(ClientService);
    let mockModalService = TestBed.get(ModalService);
    spyOn(mockClientService,'getClients').and.callFake((()=> throwError({error: {message:'error'}})));
    spyOn(mockModalService,'openMessageModal').and.returnValue(null);

    component.getClients();
    expect(mockModalService.openMessageModal).toHaveBeenCalledWith(true,'error');
  });

});
