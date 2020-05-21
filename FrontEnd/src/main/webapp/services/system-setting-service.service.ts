import { Injectable,EventEmitter,Output } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class SystemSettingServiceService {

  constructor() { }

  serverUrl = null;

  @Output() event = new EventEmitter();


  private showMainMenu = new Subject<boolean>(); 

  changeEmitted$ = this.showMainMenu.asObservable();

  setupSession(username, token){
    console.log("IM in token setter");
    sessionStorage.setItem('username',username);
    sessionStorage.setItem('token',token);
    this.showMainMenu.next(true);
  }

  clearSession(){
    console.log("im clearing");
    sessionStorage.clear();
    this.showMainMenu.next(false);
  }

  updateToken(token){
    console.log("IM updating")
    sessionStorage.setItem('token',token);
  }


  //Will read properties on startup. 
  readProperitesFile(){

  }


}
