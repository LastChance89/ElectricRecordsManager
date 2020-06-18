import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountComponent } from './account.component';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ModalService } from '../services/modal-service.service';
import { AuthorizationService } from '../services/authorization-service.service';
import { RouterTestingModule } from '@angular/router/testing';
import { User } from '../models/User.model';
import { userInfo } from 'os';
import { DebugElement } from '@angular/core';
import { By } from '@angular/platform-browser';

describe('AccountComponent', () => {
  let component: AccountComponent;
  let fixture: ComponentFixture<AccountComponent>;


  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [FormsModule,HttpClientTestingModule,RouterTestingModule ],
      declarations: [ AccountComponent ],
      providers: [ ModalService, Location,AuthorizationService]
    })
    .compileComponents();
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
  

});
