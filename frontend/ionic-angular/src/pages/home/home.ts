import { Component } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { NavController } from 'ionic-angular';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {
  token: any;

  constructor(public navCtrl: NavController,
              private http: HttpClient) {

  }

  test() {
    this.http.get('/api/auth/test').subscribe(data => {
      console.log("TEST", data);
    }, err => {
      console.log(err);
    });
  }

  getToken() {
    this.http.get('/api/auth/token').subscribe(data => {
      this.token = data;
      console.log(this.token);
    }, err => {
      console.log(err);
    });
  }

  login() {
    const body = {};

    this.http.post('/api/login', body, {
      headers: new HttpHeaders().set('X-XSRF-TOKEN', this.token.token).set('Content-Type', 'application/json; charset=utf-8'),
      params: new HttpParams().set('username', 'user').set('password', 'password'),
    }).subscribe(
      data => {
      console.log(data);
    }, err => {
      console.log(err);
    });
  }

  logout() {
    const body = {};

    this.http.post('/api/logout', body, {
      headers: new HttpHeaders().set('X-XSRF-TOKEN', this.token.token),
    }).subscribe(data => {
      console.log(data);
    }, err => {
      console.log(err);
    });
  }

  me() {
    this.http.get('/api/auth/me').subscribe(data => {
      console.log(data);
    }, err => {
      console.log(err);
    });
  }

  social() {
    var secUrl = 'http://211.238.242.169:8899/auth/facebook';
    window.open(secUrl);
  }

}
