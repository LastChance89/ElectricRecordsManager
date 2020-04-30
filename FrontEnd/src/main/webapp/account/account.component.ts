import { Component, OnInit } from '@angular/core';
import {AuthorizationService} from '../services/authorizationService.service'
import { User } from '../models/User.model';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  
  user: User  = new User();

  constructor(private authorizationService :AuthorizationService ) { }

  ngOnInit() {
  }

  createUser(){

    this.authorizationService.createAccount(this.user).subscribe(result =>{
      console.log(result);
    })
    

  }

}
