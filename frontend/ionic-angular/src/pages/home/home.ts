import { Component } from '@angular/core';
// import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { NavController, AlertController } from 'ionic-angular';
import { App } from "ionic-angular";

import { LoginPage } from '../login/login';
import { TalkPage } from '../talk/talk';

// import Socket from 'sockjs-client'
// import Stomp from 'stompjs'
// let socket;
// let stompClient;

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {
  token: any;
  items: any[] = [];

  constructor(public navCtrl: NavController,
              public alertCtrl: AlertController,
              protected app: App
              // ,private http: HttpClient
  ) {

  }

  logout() {
    this.app.getRootNav().setRoot(LoginPage);
  }

  enterTalk(talk) {
    this.navCtrl.push(TalkPage, talk);
  }

  selectTalk(talk) {
    this.enterTalk(talk);
  }

  selectRandomTalk() {
    const talk = this.items[0];
    this.enterTalk(talk);
  }

  createTalk() {
    let prompt = this.alertCtrl.create({
      title: '대화',
      message: "제목을 입력하세요",
      inputs: [
        {
          name: 'subject',
          placeholder: 'subject'
        },
      ],
      buttons: [
        {
          text: '취소',
          handler: data => {}
        },
        {
          text: '완료',
          handler: data => {
            if(data.subject == "") return false;
            const subject: string = data.subject;
            const createdDate = Date.now();
            const createdUser = '';
            let item = {
              subject: subject,
              createdDate: createdDate,
              createdUser: createdUser,
              numberOfConnections: 0
            };
            this.enterTalk(item);
          }
        }
      ]
    });
    prompt.present();
  }


  // test() {
  //   this.http.get('/api/auth/test').subscribe(data => {
  //     console.log("TEST", data);
  //   }, err => {
  //     console.log(err);
  //   });
  // }
  //
  // getToken() {
  //   this.http.get('/api/auth/token').subscribe(data => {
  //     this.token = data;
  //     console.log(this.token);
  //   }, err => {
  //     console.log(err);
  //   });
  // }
  //
  // login() {
  //   const body = {};
  //
  //   this.http.post('/api/login', body, {
  //     headers: new HttpHeaders().set('X-XSRF-TOKEN', this.token.token).set('Content-Type', 'application/json; charset=utf-8'),
  //     params: new HttpParams().set('username', 'user').set('password', 'password'),
  //   }).subscribe(
  //     data => {
  //     console.log(data);
  //   }, err => {
  //     console.log(err);
  //   });
  // }
  //
  // logout() {
  //   const body = {};
  //
  //   this.http.post('/api/logout', body, {
  //     headers: new HttpHeaders().set('X-XSRF-TOKEN', this.token.token),
  //   }).subscribe(data => {
  //     console.log(data);
  //   }, err => {
  //     console.log(err);
  //   });
  // }
  //
  // me() {
  //   this.http.get('/api/auth/me').subscribe(data => {
  //     console.log(data);
  //   }, err => {
  //     console.log(err);
  //   });
  // }
  //
  // social() {
  //   var secUrl = 'http://211.238.242.169:8899/auth/facebook';
  //   window.open(secUrl);
  // }
  //
  // socket() {
  //   const socketUrl = "http://localhost:8899/websocket";
  //
  //   const stompConnect = () => {
  //     socket = new Socket(socketUrl);
  //     stompClient = Stomp.over(socket);
  //     stompClient.debug = null;
  //     stompClient.connect({}, function (frame) {
  //       console.log('Connected: ' + frame);
  //       const url = '/talk/room.list';
  //       stompClient.subscribe(url, function (response) {
  //         // const url = 'http://' + config.apiHost + ':' + config.apiPort + '/image/download';
  //         const responseBody = JSON.parse(response.body);
  //         console.log(responseBody);
  //       });
  //     }, stompReconnect);
  //   }
  //
  //   const stompReconnect = () => {
  //     setTimeout(stompConnect, 1000);
  //   }
  //
  //   stompConnect();
  // }
  //
  // test1() {
  //   stompClient.send("/app/talk/room.find", {}, JSON.stringify({}));
  // }
  //
  // test2() {
  //   stompClient.send("/app/talk/room.insert", {}, JSON.stringify({}));
  // }
  //
  // test3() {
  //   stompClient.send("/app/talk/room.update", {}, JSON.stringify({}));
  // }
  //
  // test4() {
  //   stompClient.send("/app/talk/room.delete", {}, JSON.stringify({}));
  // }

}
