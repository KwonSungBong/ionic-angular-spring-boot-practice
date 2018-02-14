import {Component} from '@angular/core';
import { NavController, AlertController } from 'ionic-angular';
import { App } from "ionic-angular";

import { LoginPage } from '../login/login';
import { TalkPage } from '../talk/talk';
import {AuthService} from "../../services/auth.service";
import {TalkService} from "../../services/talk.service";

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {
  items: any[] = [];

  constructor(public navCtrl: NavController,
              public alertCtrl: AlertController,
              protected app: App,
              private authService: AuthService,
              private talkService: TalkService
  ) {
    this.talkService.init(this.authService.getUser());
  }

  logout() {
    this.authService.logout(data =>
    this.authService.setToken(() =>
      this.app.getRootNav().setRoot(LoginPage)));
  }

  enterTalk(talk) {
    // this.navCtrl.push(TalkPage, talk);
  }

  selectTalk(talk) {
    this.talkService.enter(talk);
    this.navCtrl.push(TalkPage);
  }

  selectRandomTalk() {
    // const talk = this.items[0];
    // this.enterTalk(talk);
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
            this.talkService.insert({subject: subject});
            this.navCtrl.push(TalkPage);
            // this.enterTalk({subject: subject});
            // const createdDate = Date.now();
            // const createdUser = '';
            // let item = {
            //   subject: subject,
            //   createdDate: createdDate,
            //   createdUser: createdUser,
            //   numberOfConnections: 0
            // };
            // this.enterTalk(item);
          }
        }
      ]
    });
    prompt.present();
  }

}
