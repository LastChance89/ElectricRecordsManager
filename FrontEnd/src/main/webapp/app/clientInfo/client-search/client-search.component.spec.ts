import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';


import { ClientService } from '../../services/client-service.service';
import { ModalService } from '../../services/modal-service.service';
import { ClientSearch } from './client-search.component';
import { Client } from '../../models/Client.model';
import { Observable, of } from 'rxjs';

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
    mockClientService.getClients.and.returnValue(of(new Array<Client>()));
    fixture.detectChanges();

  });


  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should  work', ()=>{
    component.getClients();
    expect( component.retrievedClients.emit).toHaveBeenCalled();
  })
});
