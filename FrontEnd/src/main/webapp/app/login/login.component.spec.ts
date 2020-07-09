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
import { Router } from '@angular/router';


describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  //et mockAuthorizationService : any;


  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      providers: [AuthorizationService,SystemSettingServiceService,ModalService,NgbActiveModal],
      imports: [RouterTestingModule, FormsModule,HttpClientTestingModule]
    });
    //TestBed.overrideProvider(AuthorizationService, {useValue: mockAuthorizationService});
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

  it('should set session user on succsesfull login', ()=>{

    let mockResponse = {token: btoa('"{"sub":"a","roles":[],"exp":1594082191,"iat":1594081291}"'), user: 'mockUser' }
    let  e = jasmine.createSpyObj('e', [ 'preventDefault' ]);
    let mockAuthorizationService = TestBed.get(AuthorizationService);
    let mockSystemSettingerService = TestBed.get(SystemSettingServiceService);
    let mockRouter  = TestBed.get(Router);
  
    spyOn(mockSystemSettingerService,'setupSession').and.returnValue(mockResponse);
    spyOn(mockAuthorizationService,'submitAuthorization');
    spyOn(mockRouter, 'navigate');

    mockAuthorizationService.submitAuthorization.and.returnValue(of(mockResponse));
    component.authorizeLogin(e);
    expect(mockRouter.navigate).toHaveBeenCalledWith(['application']);
  })

  
});
