import {Component, ViewChild} from '@angular/core';
import {NavController, NavParams} from 'ionic-angular';
import { Content } from 'ionic-angular';
import {TalkService} from "../../services/talk.service";

@Component({
  selector: 'page-talk',
  templateUrl: 'talk.html'
})
export class TalkPage {
  @ViewChild(Content) content: Content;
  talk: any;
  message: string = "";

  constructor(public navCtrl: NavController,
              public navParams: NavParams,
              private talkService: TalkService) {
    setTimeout(() => {
      this.content.scrollToBottom(0)
    }, 100);
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
    }, 100);
  }

  ionViewWillLeave() {
    this.talkService.exit();
  }

  sendMessage() {
    const data = {
      content: this.message,
    };

    this.talkService.insertMessage(data);

    setTimeout(() => {
      this.message = "";
      this.content.scrollToBottom(0);
    }, 50);
  }

}
