import { Component, OnInit } from '@angular/core';
import {AuthorizationService} from '../services/authorizationService.service'

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  userName: string; 
  password: string; 
  hint: string; 

  constructor(private authorizationService :AuthorizationService ) { }

  ngOnInit() {
  }

  createUser(){

    this.authorizationService.createAccount(this.userName,this.password,this.hint).subscribe(result =>{
      console.log(result);
    })
    

  }

}
