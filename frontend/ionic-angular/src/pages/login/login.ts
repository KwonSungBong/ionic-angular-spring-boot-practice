import {Component} from '@angular/core';
import {AlertController, App, NavController, Platform} from 'ionic-angular';
import {InAppBrowser, InAppBrowserObject} from '@ionic-native/in-app-browser';

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
              private iab: InAppBrowser,
              private plt: Platform,
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
    if(this.plt.is("android") || this.plt.is("ios")) {
      const url = this.authService.getProviderUrl(provider);
      let browser: InAppBrowserObject = this.iab.create(url);
      browser.on('exit').subscribe(event => {
        console.log("exit -->", event);
        this.load();
      }, err => {
        console.log("exit Error: " + err);
      });
    } else {
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
  }

  private load(): void {
    this.authService.setToken(() => this.authService.me(() => {
      this.app.getRootNav().setRoot(HomePage);
    }));
  }

  public test() {
    this.load();
  }
}
