import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginComponent } from './login.component';
import { ModalService } from '../services/modal-service.service';
import { AuthorizationService } from '../services/authorization-service.service';
import { SystemSettingServiceService } from '../services/system-setting-service.service';
import { RouterTestingModule } from '@angular/router/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';


describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let mockAuthorizationService : any;


  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      providers: [AuthorizationService,SystemSettingServiceService,ModalService,NgbActiveModal],
      imports: [RouterTestingModule, FormsModule,HttpClientTestingModule]
    });
  //  TestBed.overrideProvider(AuthorizationService, {useValue: mockAuthorizationService});
    TestBed.compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
  fixture.detectChanges();

  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('Should set Session Object on login', ()=>{
        let mockResponse = {token: 'token', user: 'mockUser' }

        mockAuthorizationService =jasmine.createSpyObj(['checkLogin','submitAuthorization']);

    mockAuthorizationService.checkLogin.and.returnValue(of(mockResponse));
    mockAuthorizationService.submitAuthorization.and.returnValue(of(mockResponse));
  
    fixture.nativeElement.querySelector("#createAccountLink").click();

  })

});
