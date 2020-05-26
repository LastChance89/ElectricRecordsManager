import { Injectable,EventEmitter,Output } from '@angular/core';
import { Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class SystemSettingServiceService {

  constructor(private http: HttpClient) { }  
  private showMainMenu = new Subject<any[]>(); 

  changeEmitted$ = this.showMainMenu.asObservable();
  setupSession(username, token){
    sessionStorage.setItem('username',username);
    sessionStorage.setItem('token',token);

    this.showMainMenu.next([username,true]);
  }

  clearSession(){
    sessionStorage.clear();
    this.showMainMenu.next([null,false]);
  }

  updateToken(token){
    sessionStorage.setItem('token',token);
  }

  //Needed so the menu keeps staying during refresh. 
  keepMenuOn(){
    this.showMainMenu.next([sessionStorage.getItem('username'),true]);
  }
 
}
