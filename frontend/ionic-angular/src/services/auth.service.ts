/**
 * Created by ksb on 2017. 10. 21..
 */
import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Injectable()
export class AuthService {
  token: any;
  user: any;

  constructor(private http: HttpClient) {

  }

  test() {
    this.http.get('/api/auth/test').subscribe(data => {
      console.log("TEST", data);
    }, err => {
      console.log(err);
    });
  }

  getUser() {
    return this.user;
  }

  getToken() {
    return this.token;
  }

  setToken(callback) {
    this.http.get('/api/auth/token').subscribe(data => {
      this.token = data;
      console.log(this.token);
      callback && callback(data);
    }, err => {
      console.log(err);
    });
  }

  login(username, password, callback) {
    const body = {};

    this.http.post('/api/login', body, {
      headers: new HttpHeaders().set('X-XSRF-TOKEN', this.token.token)
        .set('Content-Type', 'application/json; charset=utf-8'),
      params: new HttpParams().set('username', username).set('password', password),
    }).subscribe(
      data => {
        callback && callback(data);
        console.log(data);
      }, err => {
        callback && callback(err);
        console.log(err);
      });
  }

  logout(callback) {
    const body = {};

    this.http.post('/api/logout', body, {
      headers: new HttpHeaders().set('X-XSRF-TOKEN', this.token.token),
    }).subscribe(data => {
      console.log(data);
      callback && callback(data);
    }, err => {
      console.log(err);
      callback && callback(err);
    });
  }

  me(callback) {
    this.http.get('/api/auth/me').subscribe(data => {
      this.user = data['principal']['user'];
      callback && callback(data);
      console.log(data);
    }, err => {
      console.log(err);
    });
  }

  // social() {
  //   var secUrl = 'http://211.238.242.169:8899/auth/facebook';
  //   window.open(secUrl);
  // }

}
