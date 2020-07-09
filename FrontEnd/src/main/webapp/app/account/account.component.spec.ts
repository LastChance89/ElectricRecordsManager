import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountComponent } from './account.component';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ModalService } from '../services/modal-service.service';
import { AuthorizationService } from '../services/authorization-service.service';
import { RouterTestingModule } from '@angular/router/testing';
import { User } from '../models/User.model';
import { of, Observable,throwError  } from 'rxjs';
import {MessageModalComponent} from '../modals/message-modal/message-modal.component'

describe('AccountComponent', () => {
  let component: AccountComponent;
  let fixture: ComponentFixture<AccountComponent>;
  let mockAuthorizationService : any;
   
  beforeEach(async(() => {
    mockAuthorizationService = jasmine.createSpyObj(['createAccount'])
    TestBed.configureTestingModule({
      imports: [FormsModule,HttpClientTestingModule,RouterTestingModule ],
      declarations: [ AccountComponent,MessageModalComponent ],
      providers: [ ModalService, Location,AuthorizationService]
    });
    TestBed.overrideProvider(AuthorizationService, {useValue: mockAuthorizationService});
    TestBed.compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should be able to enable and disable create user account button', async(() => {
    //fixture.debugElement.query(By.id;
    expect(fixture.nativeElement.querySelector('#accButton').disabled).toBeTruthy();
    component.user = new User()
    component.user.password = "p";
    component.user.hint="h";
    component.user.userName="u";
    component.enableButton();
    fixture.detectChanges();
    expect(fixture.nativeElement.querySelector('#accButton').disabled).toBeFalsy();
  }))

  it('should display success message modal on account created', () => {
      let mockResponse = { message: 'success message' }
      let mockModalService = TestBed.get(ModalService);
      spyOn(mockModalService,'openMessageModal').and.returnValue(null);
      mockAuthorizationService.createAccount.and.returnValue(of(mockResponse));
      component.createUser();
      expect(mockModalService.openMessageModal).toHaveBeenCalledWith(false,'success message');
  })
  
  it('should display error message modal on account creation error',()=>{
    let mockModalService = TestBed.get(ModalService);
    spyOn(mockModalService,'openMessageModal').and.returnValue(null);
    mockAuthorizationService.createAccount.and.callFake((()=> throwError({error: {message:'error'}})) );
    component.createUser();
    expect(mockModalService.openMessageModal).toHaveBeenCalledWith(true,'error');
  })
  
});
