import {Component} from '@angular/core';
import {AlertController, NavController} from 'ionic-angular';

@Component({
  selector: 'page-find-email-password',
  templateUrl: 'findEmailPassword.html'
})
export class FindEmailPasswordPage {

  constructor(public navCtrl: NavController,
              public alertCtrl: AlertController) {
  }

  complete() {
    // const email="rnjstjdqhd39@naver.com";
  }

  cancel() {
    this.navCtrl.pop();
  }

}
