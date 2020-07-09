import { TestBed, ComponentFixture, async } from '@angular/core/testing';

import { SystemSettingServiceService } from './system-setting-service.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('LoginComponent', () => {
  let service: SystemSettingServiceService;
  let fixture: ComponentFixture<SystemSettingServiceService>;
  

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      providers: [ SystemSettingServiceService ],
      imports: [HttpClientTestingModule]
    });
    //TestBed.overrideProvider(AuthorizationService, {useValue: mockAuthorizationService});
    service  = TestBed.get(SystemSettingServiceService);
  }));


  it('should create', () => {
    expect(service).toBeTruthy();
  });

  it('should setup session storage and display the main menu ', ()=>{
    //Below is what the encoded token is. Not sure why but ATOB refused to work 
    //with the base 64 BTOA encoded stirng of it, so I just pulled it from the front end
    //{sub:'a',roles:[],exp:1594214656,iat:1594213756}"
    let token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhIiwicm9sZXMiOltdLCJleHAiOjE1OTQyMTQ4NjUsImlhdCI6MTU5NDIxMzk2NX0.e-tN-9YOBH-a0QZ1-_FVJBOxz4a6jrJGuuwndvhUTSgIAUgllkh50heL7c4EIkx4tToFVf7V5JOJs9HgdBmIzA"
 
    let user = 'a';
    service.setupSession(user,token);
    expect(sessionStorage.getItem('username') == 'a').toBeTruthy();

  });

  it('should clear session storage', ()=>{
    service.clearSession(); 
    expect(sessionStorage.getItem('username') == undefined).toBeTruthy();
    expect(sessionStorage.getItem('token') == undefined).toBeTruthy();
  });

  it ('should update token', ()=>{
    let updateToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiIiwicm9sZXMiOltdLCJleHAiOjE1OTQyNTEzMjIsImlhdCI6MTU5NDI1MDQyMn0.RYkB2OoIoFblf-TuNOXFi8coHxoPUP6OC1c4Ija8F0umqWCduxYyDjqnOpPcXGi0-4eQysu_COIMUF7H0b8YYw";
    service.updateToken(updateToken);
    expect(sessionStorage.getItem('token') == updateToken).toBeTruthy();
  })

  
});
