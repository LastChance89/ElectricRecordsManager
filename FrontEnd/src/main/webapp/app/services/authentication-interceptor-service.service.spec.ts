import { TestBed } from '@angular/core/testing';

import { DashBoardService } from './dash-board-service.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AuthenticationInterceptorService } from './authentication-interceptor-service.service';
import { AuthorizationService } from './authorization-service.service';
import { RouterTestingModule } from '@angular/router/testing';
import { SystemSettingServiceService } from './system-setting-service.service';

describe('AuthenticationInterceptorService', () => {
  
  let service = AuthenticationInterceptorService;
  let httpMock: HttpTestingController;
  beforeEach(() =>{
  TestBed.configureTestingModule({
    providers: [AuthenticationInterceptorService,AuthorizationService,],
    imports: [HttpClientTestingModule,RouterTestingModule,SystemSettingServiceService]
  });
  service = TestBed.get(AuthenticationInterceptorService);
  httpMock = TestBed.get(HttpClientTestingModule);
  
  });
/*
  it('should be created', () => {
    expect(service).toBeTruthy();
  });
*/
});
