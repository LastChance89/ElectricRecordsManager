import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {User} from '../user/userDisplay/user.component'
import {userSearch} from '../user/user-search/user-search.component'
import{powerMenu} from '../powerMenu/powerMenu.component';
import{LoginComponent} from '../login/login.component';
import {LoggedInAuthenticatorService} from '../services/logged-in-authenticator.service'
import {MainPageComponent}  from '../main-page/main-page.component'
import {UserDashboardComponent} from  '../user/user-dashboard.component'
import {AccountComponent} from '../account/account.component'

const routes: Routes = [

 {
	path:'', redirectTo: 'login', pathMatch: 'full' 
	
 },
 	{
	 path: 'login',
	 component: LoginComponent,

 	},
 	{
	    path: 'user',
	    component: UserDashboardComponent,
	    canActivate:[LoggedInAuthenticatorService]
 	},
	{
	    path:'application',
	    component:MainPageComponent,
	    canActivate:[LoggedInAuthenticatorService]
	},
	{
		path:'account',
		component:AccountComponent

	}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
