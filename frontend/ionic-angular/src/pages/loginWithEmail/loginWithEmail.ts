import {Component} from '@angular/core';
import {AlertController, App, NavController} from 'ionic-angular';

import {HomePage} from '../home/home';
import {JoinWithEmailPage} from '../joinWithEmail/joinWithEmail';
import {FindEmailPasswordPage} from '../findEmailPassword/findEmailPassword';
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'page-login-with-email',
  templateUrl: 'loginWithEmail.html'
})
export class LoginWithEmailPage {
  username: string = "";
  password: string = "";

  constructor(public navCtrl: NavController,
              protected app: App,
              public alertCtrl: AlertController,
              private authService: AuthService) {
  }

  complete() {
    const username = this.username;
    // const password = this.password;
    const password = 'password';

    this.authService.login(username, password,
      data => this.authService.me(() =>
        this.app.getRootNav().setRoot(HomePage)));
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
