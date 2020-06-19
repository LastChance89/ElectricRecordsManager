import { TestBed, async } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AppComponent } from './app.component';
import { SystemSettingServiceService } from './services/system-setting-service.service';
import { AuthorizationService } from './services/authorization-service.service';
import { Router } from '@angular/router';
import { ModalService } from './services/modal-service.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { NavigationMenuComponent } from './navigation-menu/navigation-menu.component';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ 
        RouterTestingModule,
        HttpClientTestingModule
      ],
      declarations: [
        AppComponent, NavigationMenuComponent
      ],
      providers: [SystemSettingServiceService,AuthorizationService,ModalService]
    }).compileComponents();
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'app'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('app');
  });

});
