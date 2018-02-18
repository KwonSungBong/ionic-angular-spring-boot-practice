import {Component} from '@angular/core';
import {AlertController, App, NavController} from 'ionic-angular';

import {HomePage} from '../home/home';
import {LoginWithEmailPage} from '../loginWithEmail/loginWithEmail';
import {AuthService} from "../../services/auth.service";
import {BehaviorSubject} from "rxjs/BehaviorSubject";

@Component({
  selector: 'page-login',
  templateUrl: 'login.html'
})
export class LoginPage {

  windowOpener: any;
  loading: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor(public navCtrl: NavController,
              protected app: App,
              public alertCtrl: AlertController,
              private authService: AuthService) {
  }

  loginWithEmail() {
    this.navCtrl.push(LoginWithEmailPage)
  }

  loginWithFacebook() {
    this.loginWithSocial('facebook');
  }

  loginWithGoogle() {
    this.loginWithSocial('google');
  }

  loginWithKakao() {
    this.loginWithSocial('kakao');
  }

  loginWithNaver() {
    this.loginWithSocial('naver');
  }

  private loginWithSocial(provider) {
    this.loading.next(true);
    var url = this.authService.getProviderUrl(provider);
    this.windowOpener = window.open(url);
    var timer = setInterval(() => {
      if(this.windowOpener.closed) {
        clearInterval(timer);
        this.load();
        this.loading.next(false);
      }
    }, 1000);
  }

  private load(): void {
    this.authService.setToken(() => this.authService.me(() => {
      this.app.getRootNav().setRoot(HomePage);
    }));
  }

}
