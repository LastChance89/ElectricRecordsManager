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
    
    this.showMainMenu.next([null,false]);
    //ONLY navigate after the sesion storage is cleared. 
    let promise = new Promise((resolve,reject) => {
      sessionStorage.clear();
      resolve();
    })
    return promise;
  }

  updateToken(token){
    sessionStorage.setItem('token',token);
  }

  //Needed so the menu keeps staying during refresh. 
  keepMenuOn(){
    this.showMainMenu.next([sessionStorage.getItem('username'),true]);
  }
 
}
