import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule,HTTP_INTERCEPTORS} from "@angular/common/http";
import {ClientService} from './services/clientService.service'
import {AuthorizationService} from './services/authorizationService.service';
import {ClientDisplay} from './clientInfo/client-display/client-display.component';
import {ClientSearch} from './clientInfo/client-search/client-search.component'
import {Client} from './models/Client.model'
import { FormsModule } from '@angular/forms';
import {LoginComponent} from './login/login.component';
import {LoggedInAuthenticatorService} from './services/logged-in-authenticator.service'
import {AuthenticationInterceptorService} from './services/AuthenticationInterceptorService.service'
import {MainPageComponent}  from './main-page/main-page.component'
import {AgGridModule} from 'ag-grid-angular';
import {GridService} from './services/gridService.service'
import {RecordDisplay} from './records/recordDisplay/record-display.component'
import { HashLocationStrategy, LocationStrategy } from '@angular/common'
import {ClientDashboardComponent} from './clientInfo/client-dashboard.component'
import {SharedPopupModalComponent} from './shared-popup-modal/shared-popup-modal.component'
import {MessageModalComponent} from './modals/message-modal/message-modal.component'
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {AccountComponent} from './account/account.component'
import {NavigationMenuComponent} from'./navigation-menu/navigation-menu.component'
import {GridRenderer} from './grid/custom-grid-renderer.component'
import {GridComponent} from './grid/grid.component'
import {RecordService} from './services/recordServices.service'
import {SystemSettingServiceService} from './services/system-setting-service.service'
import{DashBoardServiceService} from './services/dash-board-service.service'
@NgModule({
  declarations: [
    AppComponent, 
    NavigationMenuComponent,
    ClientDisplay,
    ClientSearch,
    LoginComponent,
    MainPageComponent,
    RecordDisplay,
    ClientDashboardComponent,
    SharedPopupModalComponent,
    MessageModalComponent,
    AccountComponent,
    GridRenderer,
    GridComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot([]),
    FormsModule,
    AgGridModule,
    NgbModule,
  ],
  entryComponents: [SharedPopupModalComponent,GridRenderer,MessageModalComponent],
  
  providers: [ClientService,LoggedInAuthenticatorService, DashBoardServiceService,
  AuthorizationService,Client,GridService, RecordService,SystemSettingServiceService,
  {provide: HTTP_INTERCEPTORS, useClass:AuthenticationInterceptorService ,multi: true},
  {provide: LocationStrategy, useClass: HashLocationStrategy}],
  bootstrap: [AppComponent]
})
export class AppModule { }
