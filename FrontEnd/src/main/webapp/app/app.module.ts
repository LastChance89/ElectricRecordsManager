import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule,HTTP_INTERCEPTORS} from "@angular/common/http";
import {ClientService} from '../services/clientService.service'
import {AuthorizationService} from '../services/authorizationService.service';
import {User} from '../user/userDisplay/user.component'
import {userSearch} from '../user/user-search/user-search.component'
import {ClientInfo} from '../models/ClientInfo.model'
import { FormsModule } from '@angular/forms';
import {LoginComponent} from '../login/login.component';
import {LoggedInAuthenticatorService} from '../services/logged-in-authenticator.service'
import {AuthenticationInterceptorService} from '../services/AuthenticationInterceptorService.service'
import {MainPageComponent}  from '../main-page/main-page.component'
import {AgGridModule} from 'ag-grid-angular';
import {GridService} from '../services/gridService.service'
import {RecordDisplay} from '../records/recordDisplay/record-display.component'
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import {UserDashboardComponent} from '../user/user-dashboard.component'
import {SharedPopupModalComponent} from '../shared-popup-modal/shared-popup-modal.component'
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {AccountComponent} from '../account/account.component'
import {NavigationMenuComponent} from'../navigation-menu/navigation-menu.component'

@NgModule({
  declarations: [
    AppComponent, 
    NavigationMenuComponent,
    User,
    userSearch,
    LoginComponent,
    MainPageComponent,
    RecordDisplay,
    UserDashboardComponent,
    SharedPopupModalComponent,
    AccountComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot([]),
    FormsModule,
    AgGridModule,
    NgbModule
  ],
  entryComponents: [SharedPopupModalComponent],
  
  providers: [ClientService,LoggedInAuthenticatorService, 
  AuthorizationService,ClientInfo,GridService,
  {provide: HTTP_INTERCEPTORS, useClass:AuthenticationInterceptorService ,multi: true},
  {provide: LocationStrategy, useClass: HashLocationStrategy}],
  bootstrap: [AppComponent]
})
export class AppModule { }
