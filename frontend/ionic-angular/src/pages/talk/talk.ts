import {Component, ViewChild} from '@angular/core';
import {NavController, NavParams} from 'ionic-angular';
import { Content } from 'ionic-angular';

@Component({
  selector: 'page-talk',
  templateUrl: 'talk.html'
})
export class TalkPage {
  @ViewChild(Content) content: Content;
  createdUser: any;
  talk: any;
  message: string = "";
  items: any[] = [];

  constructor(public navCtrl: NavController,
              public navParams: NavParams) {

    setTimeout(() => {
      this.content.scrollToBottom(0)
    }, 10);
  }

  ngAfterViewInit() {
    this.content.ionScrollEnd.subscribe((data)=>{
      if(data.scrollTop == 0) {
        // console.log("TOP")
      }
    });
  }

  ionViewWillEnter(): void {
    setTimeout(() => {
      this.content.scrollToBottom(0)
    }, 10);
  }

  ionViewWillLeave() {
  }

  sendMessage() {
    const data = {
      createdUser: this.createdUser,
      message: this.message,
      createdDate: Date.now()
    };

    this.items.push(data);

    setTimeout(() => {
      this.message = "";
      this.content.scrollToBottom(0);
    }, 10);
  }

}
