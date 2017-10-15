import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { App } from "ionic-angular";
import { AlertController } from 'ionic-angular';

import { HomePage } from '../home/home';
import { JoinWithEmailPage } from '../joinWithEmail/joinWithEmail';
import { FindEmailPasswordPage } from '../findEmailPassword/findEmailPassword';

@Component({
  selector: 'page-login-with-email',
  templateUrl: 'loginWithEmail.html'
})
export class LoginWithEmailPage {

  constructor(public navCtrl: NavController,
              protected app: App,
              public alertCtrl: AlertController) {
  }

  complete() {
    // const email="rnjstjdqhd39@naver.com";
    // const password="rnjstjdqhd39@";
  }

  cancel() {
    this.navCtrl.pop();
  }

  joinWithEmailPage() {
    this.navCtrl.push(JoinWithEmailPage)
  }

  findEmailPasswordPage() {
    this.navCtrl.push(FindEmailPasswordPage)
  }

}
