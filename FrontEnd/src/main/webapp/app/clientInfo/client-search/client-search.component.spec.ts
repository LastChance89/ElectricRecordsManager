import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';


import { ClientService } from '../../services/client-service.service';
import { ModalService } from '../../services/modal-service.service';
import { ClientSearch } from './client-search.component';
import { Client } from '../../models/Client.model';
import { Observable, of, throwError } from 'rxjs';

describe('ClientSearch', () => {
  let component: ClientSearch;
  let fixture: ComponentFixture<ClientSearch>;
  let mockClientService : any;
  beforeEach(async(() => {
    mockClientService = jasmine.createSpyObj(['getClients']);

    TestBed.configureTestingModule({
      imports: [FormsModule, HttpClientTestingModule],
      declarations: [ClientSearch],
      providers: [ModalService, ClientService],

    });
    TestBed.overrideProvider(ClientService, {useValue: mockClientService});
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
    mockClientService.getClients.and.returnValue(of(new Array<Client>()));
    spyOn(component.retrievedClients,'emit');
    fixture.nativeElement.querySelector('#searchButton').click();
    expect( component.retrievedClients.emit).toHaveBeenCalled();
  });

  it('should throw error on error', ()=>{
    let mockModalService = TestBed.get(ModalService);
    spyOn(mockModalService,'openMessageModal').and.returnValue(null);
    mockClientService.getClients.and.callFake((()=> throwError({error: {message:'error'}})))
    fixture.nativeElement.querySelector('#searchButton').click();
    expect(mockModalService.openMessageModal).toHaveBeenCalledWith(true,'error');
  });

});
