import {Component} from '@angular/core';
import {AlertController, App, NavController} from 'ionic-angular';

import {LoginPage} from '../login/login';
import {TalkPage} from '../talk/talk';
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

  selectTalk(talk) {
    this.talkService.enter(talk);
    this.navCtrl.push(TalkPage);
  }

  selectRandomTalk() {
    // const talk = this.items[0];
    // this.selectTalk(talk);
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
          }
        }
      ]
    });
    prompt.present();
  }

}
