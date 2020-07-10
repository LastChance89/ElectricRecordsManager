import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MainPageComponent } from './main-page.component';
import { AuthorizationService } from '../services/authorization-service.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of, throwError } from 'rxjs';
import { Context } from 'ag-grid-community';
import { ModalService } from '../services/modal-service.service';
import { DashBoardService } from '../services/dash-board-service.service';

describe('MainPageComponent', () => {
  let component: MainPageComponent;
  let fixture: ComponentFixture<MainPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MainPageComponent ],
      providers: [AuthorizationService],
      imports: [HttpClientTestingModule]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should keep / set context and load dashboard data on initalization', ()=>{
    let mockAuthorizationService = TestBed.get(AuthorizationService);
    let mockDashboardService = TestBed.get(DashBoardService);
    spyOn(mockAuthorizationService,'setupContext').and.returnValue(of('test'));
     //dont care whats returned, only that it is called. 
    spyOn(mockDashboardService, 'getDashBoardData').and.returnValue(of(null));
    component.ngOnInit();
    expect(mockAuthorizationService.setupContext).toHaveBeenCalled();
    expect(mockDashboardService.getDashBoardData).toHaveBeenCalled();
  });

  it('should display error message on context initalization failed', ()=>{
    let mockAuthorizationService = TestBed.get(AuthorizationService);
    let mockModalService = TestBed.get(ModalService);
    //dont care whats returned, only that it is called.
    spyOn(mockModalService, 'openMessageModal').and.returnValue(null);
    spyOn(mockAuthorizationService,'setupContext').and.callFake((()=> throwError({error: {message:'error'}})));
    component.ngOnInit();
    expect(mockModalService.openMessageModal).toHaveBeenCalledWith(true,'error');
  });

  it('should dispaly error message on failure to load dasbhoard data', ()=>{
    let mockAuthorizationService = TestBed.get(AuthorizationService);
    let mockModalService = TestBed.get(ModalService);
    let mockDashboardService = TestBed.get(DashBoardService);
    //dont care whats returned, only that it is called.
    spyOn(mockModalService, 'openMessageModal').and.returnValue(null);
    spyOn(mockAuthorizationService,'setupContext').and.returnValue(of(null));
    spyOn(mockDashboardService, 'getDashBoardData').and.callFake((()=> throwError({error: {message:'error'}})));
    component.ngOnInit();
    expect(mockModalService.openMessageModal).toHaveBeenCalledWith(true,'error');
  });

  it('should display error message on failed user logout', ()=>{
    let mockAuthorizationService = TestBed.get(AuthorizationService);
    let mockModalService = TestBed.get(ModalService);
    spyOn(mockAuthorizationService,'logUserOut').and.callFake((()=> throwError({error: {message:'error'}})));
    spyOn(mockModalService,'openMessageModal').and.returnValue(null);
    component.logout();
    expect(mockModalService.openMessageModal).toHaveBeenCalledWith(true,'error');
  })

  

});
