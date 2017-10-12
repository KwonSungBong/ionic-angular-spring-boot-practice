import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
//import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { NavController } from 'ionic-angular';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {
  token: string;

  constructor(public navCtrl: NavController,
              private http: HttpClient) {

  }

  test() {
    this.http.get('/api/auth/test').subscribe(data => {
      console.log("TEST", data);
    });
  }

  /*getToken() {
    this.http.get('/auth/token').subscribe(data => {
      this.token = data;
      console.log(this.token);
    });
  }

  login() {
    const body = {};

    this.http.post('/login', body, {
      headers: new HttpHeaders().set('X-XSRF-TOKEN', this.token.token),
      params: new HttpParams().set('username', 'user').set('password', 'password'),
    }).subscribe(data => {
      console.log(data);
    });
  }

  logout() {
    const body = {};

    this.http.post('/logout', body, {
      headers: new HttpHeaders().set('X-XSRF-TOKEN', this.token.token),
    }).subscribe(data => {
      console.log(data);
    });
  }

  me() {
    this.http.get('/auth/me').subscribe(data => {
      console.log(data);
    });
  }*/

}
