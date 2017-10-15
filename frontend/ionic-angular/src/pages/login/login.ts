import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { App } from "ionic-angular";
import { AlertController } from 'ionic-angular';

import { HomePage } from '../home/home';
import { LoginWithEmailPage } from '../loginWithEmail/loginWithEmail';
@Component({
  selector: 'page-login',
  templateUrl: 'login.html'
})
export class LoginPage {

  constructor(public navCtrl: NavController,
              protected app: App,
              public alertCtrl: AlertController) {
  }

  loginWithEmail() {
    this.navCtrl.push(LoginWithEmailPage)
  }

  loginWithAnonymous() {
    this.app.getRootNav().setRoot(HomePage)
  }

  loginWithFacebook() {
    this.app.getRootNav().setRoot(HomePage)
  }

  loginWithGoogle() {
    this.app.getRootNav().setRoot(HomePage)
  }

}
