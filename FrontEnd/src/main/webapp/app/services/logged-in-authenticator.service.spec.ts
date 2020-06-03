import { TestBed } from '@angular/core/testing';

import { LoggedInAuthenticatorService } from './logged-in-authenticator.service';

describe('LoggedInAuthenticatorService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LoggedInAuthenticatorService = TestBed.get(LoggedInAuthenticatorService);
    expect(service).toBeTruthy();
  });
});
