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

  constructor(private stompClient: StompClient) {
  }

  init(user) {
    this.user = user;

    console.log("TEST", user);

    let roomList: Observable<any> = this.stompClient.subscribe('/talk/room.list', {});
    roomList.subscribe(response => {
        console.log("list", response);
        this.items = response;
    });

    let roomEnter: Observable<any> = this.stompClient.subscribe('/talk/room.enter/'+user.id, {});
    roomEnter.subscribe(response => {
      this.talk = response;
      this.findMessage();
      let roomMessageList: Observable<any> = this.stompClient.subscribe('/talk/room.message.list/'+this.talk.idx, {});
      roomMessageList.subscribe(response => {
        console.log("messages"+this.talk.idx, response);
        this.messages = response;
      });
    });

    this.list();
  }

  list() {
    this.stompClient.send("/app/talk/room.find", {}, JSON.stringify({}));
  }

  enter(talk) {
    this.stompClient.send("/app/talk/room.enter/"+this.user.id, {}, JSON.stringify(talk));
  }

  exit(){
    this.stompClient.unsubscribe('/talk/room.message.list/'+this.talk.idx);
  }

  insert(talk) {
    this.stompClient.send("/app/talk/room.insert/"+this.user.id, {}, JSON.stringify(talk));
  }

  findMessage() {
    // console.log("room.message.find", "/app/talk/room.message.find/"+(this.talk.idx))
    this.stompClient.send("/app/talk/room.message.find/"+(this.talk.idx ? this.talk.idx : 1), {}, JSON.stringify({}));
  }

  insertMessage(message) {
    // console.log("room.message.insert", "/app/talk/room.message.insert/"+(this.talk.idx))
    this.stompClient.send("/app/talk/room.message.insert/"+this.talk.idx, {}, JSON.stringify(message));
  }


  // update() {
  //   stompClient.send("/app/talk/room.update", {}, JSON.stringify({}));
  // }
  //
  // delete() {
  //   stompClient.send("/app/talk/room.delete", {}, JSON.stringify({}));
  // }

}
