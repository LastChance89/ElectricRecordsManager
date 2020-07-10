import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { ClientDisplay } from './client-display.component';
import { ModalService } from '../../services/modal-service.service';
import { GridService } from '../../services/gridService.service';
import { ClientService } from '../../services/client-service.service';
import { AgGridModule } from 'ag-grid-angular';
import { GridComponent } from '../../grid/grid.component';
import { Client } from '../../models/Client.model';
import { of, throwError } from 'rxjs';
import { UserRecord } from '../../models/userRecord.model';

describe('ClientDisplay', () => {
  let component: ClientDisplay;
  let fixture: ComponentFixture<ClientDisplay>;
 

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [FormsModule,HttpClientTestingModule,AgGridModule ],
      declarations: [ ClientDisplay,GridComponent ],
      providers: [ ModalService,GridService,ClientService]
    });
  
    TestBed.compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientDisplay);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should get GridMetadata on initalization', ()=>{
    let mockGridServce =TestBed.get(GridService);
    let colDef = [
      {headerName: "Service", field: "service", cellRenderer: null}
    ]
    spyOn(mockGridServce,'getGridMetaData').and.returnValue(of(colDef));
    component.ngOnInit();
    expect(component.gridColumns == colDef).toBeTruthy();
  });


  it('should display message model on getGridMetaData error', ()=>{
    let mockGridServce =TestBed.get(GridService);
    let mockModalService = TestBed.get(ModalService);
    spyOn(mockModalService,'openMessageModal').and.returnValue(null);
    spyOn(mockGridServce,'getGridMetaData').and.callFake(()=>throwError({error:{message: "error"}}));
    component.ngOnInit();
    expect(mockModalService.openMessageModal).toHaveBeenCalledWith(true,'error');

  });

  it('should emit records on getUserRecords', ()=>{
    let mockClientServce =TestBed.get(ClientService);
    let  e = jasmine.createSpyObj('e', [ 'preventDefault' ]);
    let mockClient = new Client();
    mockClient.accountnumber = '1';
    component.client = mockClient;

    spyOn(component.records,'emit');
    spyOn(mockClientServce,'getClientRecords').and.returnValue(of(new Array<UserRecord>()));
    component.getClientRecords(e);
    expect(component.records.emit).toHaveBeenCalled();

  });

  
  it('should open message modal on getClientRecords error', ()=>{
    let mockClientServce =TestBed.get(ClientService);
    let mockModalService = TestBed.get(ModalService);
    let  e = jasmine.createSpyObj('e', [ 'preventDefault' ]); 
    let mockClient = new Client();
    mockClient.accountnumber = '1';
    component.client = mockClient;

    spyOn(mockModalService,'openMessageModal').and.returnValue(null);
    spyOn(mockClientServce, 'getClientRecords').and.callFake(()=> throwError({error:{error:'error'}}))
    component.getClientRecords(e);
    expect(mockModalService.openMessageModal).toHaveBeenCalled();
  })


});
