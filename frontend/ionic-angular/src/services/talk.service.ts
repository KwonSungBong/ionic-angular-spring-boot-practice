/**
 * Created by ksb on 2017. 10. 21..
 */
import {Injectable} from '@angular/core';
import {StompClient} from "./stomp.client";
import {Observable} from "rxjs/Rx";

@Injectable()
export class TalkService {
  user: any = {};
  items: any[] = [];
  talk: any = {};
  messages: any[] = [];
  roomEnterSubscribe: any;
  roomMessageListSubscribe: any;

  constructor(private stompClient: StompClient) {
  }

  init(user) {
    this.user = user;
    console.log("TalkService user", this.stompClient);

    let roomList: Observable<any> = this.stompClient.subscribe('/talk/room.list', {});
    roomList.subscribe(response => {
        console.log("list", response);
        this.items = response;
    });

    this.list();
  }

  list() {
    this.stompClient.send("/app/talk/room.find", {}, JSON.stringify({}));
  }

  enter(talk) {
    let roomEnter: Observable<any> = this.stompClient.subscribe('/talk/room.enter/'+this.user.id, {});
    this.roomEnterSubscribe = roomEnter.subscribe(room => {
      this.talk = room;
      this.findMessage();
    });

    let roomMessageList: Observable<any> = this.stompClient.subscribe('/talk/room.message.list/'+talk.idx, {});
    this.roomMessageListSubscribe = roomMessageList.subscribe(roomMessage => {
      this.messages = roomMessage;
    });

    this.stompClient.send("/app/talk/room.enter/"+this.user.id, {}, JSON.stringify(talk));
  }

  exit(){
    this.roomMessageListSubscribe.unsubscribe();
    this.roomEnterSubscribe.unsubscribe();
    this.stompClient.unsubscribe('/talk/room.message.list/'+this.talk.idx);
    this.stompClient.unsubscribe('/talk/room.enter/'+this.user.id);
  }

  insert(talk) {
    this.stompClient.send("/app/talk/room.insert/"+this.user.id, {}, JSON.stringify(talk));
  }

  findMessage() {
    this.stompClient.send("/app/talk/room.message.find/"+(this.talk.idx ? this.talk.idx : 1), {}, JSON.stringify({}));
  }

  insertMessage(message) {
    this.stompClient.send("/app/talk/room.message.insert/"+this.talk.idx+"/"+this.user.id, {}, JSON.stringify(message));
  }

  // update() {
  //   this.stompClient.send("/app/talk/room.update", {}, JSON.stringify({}));
  // }
  //
  // delete() {
  //   this.stompClient.send("/app/talk/room.delete", {}, JSON.stringify({}));
  // }

}
