import { Injectable } from '@angular/core';
import {HttpClient } from '@angular/common/http';
import { DashBoard } from '../models/dash-board.model';

@Injectable({
  providedIn: 'root'
})
export class DashBoardService {

  constructor(private http: HttpClient) { }


  getDashBoardData(){
    return this.http.post<DashBoard>('/power/data/dashboardData','')
  }


}
