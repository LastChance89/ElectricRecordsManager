import { ModuleWithProviders, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { User } from './user.component';
import { LoginComponent } from './src/main/webapp/login/login/login.component';
import { AuthorizationComponent } from './src/main/webapp/login/authorization/authorization.component';
import { MainPageComponent } from './src/main/webapp/main-page/main-page.component';
import { UserDashboardComponent } from './src/main/webapp/user/user-dashboard/user-dashboard.component';
import { SharedPopupComponent } from './src/main/webapp/shared-popup/shared-popup.component';
import { SharedPopupModalComponent } from './src/main/webapp/shared-popup-modal/shared-popup-modal.component';
import { AccountComponent } from './src/main/webapp/account/account.component'

@NgModule({
  imports: [
  ],
  declarations: [
    User,
    LoginComponent,
    AuthorizationComponent,
    MainPageComponent,
    UserDashboardComponent,
    SharedPopupComponent,
    SharedPopupModalComponent,
    AccountComponent
  ]
})
export class UserModule {}
