import { TestBed } from '@angular/core/testing';

import { LoggedInAuthenticatorService } from './logged-in-authenticator.service';
import { Router } from '@angular/router';
import { AuthorizationService } from './authorization-service.service';
import { SystemSettingServiceService } from './system-setting-service.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { HttpClient } from '@angular/common/http';

describe('LoggedInAuthenticatorService', () => {

  let mockRouter = {
    navigate: jasmine.createSpy('navigate')
  } 

  beforeEach(() => TestBed.configureTestingModule({
    imports:  [HttpClientTestingModule],
    providers: [LoggedInAuthenticatorService, {provide: Router, useValue: mockRouter},AuthorizationService,SystemSettingServiceService], 
  }));

  it('should be created', () => {
    const service: LoggedInAuthenticatorService = TestBed.get(LoggedInAuthenticatorService);
    expect(service).toBeTruthy();
  });
});
