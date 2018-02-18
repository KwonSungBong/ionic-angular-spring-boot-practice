import {Component} from '@angular/core';
import {AlertController, NavController} from 'ionic-angular';

@Component({
  selector: 'page-join-with-email',
  templateUrl: 'joinWithEmail.html'
})
export class JoinWithEmailPage {

  constructor(public navCtrl: NavController,
              public alertCtrl: AlertController) {
  }

  complete() {
    // const email="rnjstjdqhd39@naver.com";
    // const password="rnjstjdqhd39@";
  }

  cancel() {
    this.navCtrl.pop();
  }

}
