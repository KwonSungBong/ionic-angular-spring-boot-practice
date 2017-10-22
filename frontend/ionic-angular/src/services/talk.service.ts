/**
 * Created by ksb on 2017. 10. 21..
 */
import {Injectable} from '@angular/core';

import Socket from 'sockjs-client'
import Stomp from 'stompjs'
let socket;
let stompClient;

@Injectable()
export class TalkService {
  user: any = {};
  items: any[] = [];
  talk: any = {};
  messages: any[] = [];

  constructor() {

  }

  init(user) {
    this.user = user;
    const socketUrl = "http://localhost:8899/websocket";

    const stompConnect = () => {
      socket = new Socket(socketUrl);
      stompClient = Stomp.over(socket);
      stompClient.debug = null;
      stompClient.connect({}, frame => {
        console.log('Connected: ' + frame);
        this.list();
        stompClient.subscribe('/talk/room.list', response => {
          const responseBody = JSON.parse(response.body);
          console.log("list", responseBody);
          this.items = responseBody;
        });
        stompClient.subscribe('/talk/room.enter/'+this.user.id, response => {
          const responseBody = JSON.parse(response.body);
          console.log("insert", responseBody);
          this.talk = responseBody;

          this.findMessage();
          stompClient.subscribe('/talk/room.message.list/'+this.talk.idx, response => {
            const responseBody = JSON.parse(response.body);
            console.log("messages", responseBody);
            this.messages = responseBody;
          });
        });
      }, stompReconnect);
    }

    const stompReconnect = () => {
      setTimeout(stompConnect, 1000);
    }

    stompConnect();
  }

  list() {
    stompClient.send("/app/talk/room.find", {}, JSON.stringify({}));
  }

  enter(talk) {
    stompClient.send("/app/talk/room.enter/"+this.user.id, {}, JSON.stringify(talk));
  }

  insert(talk) {
    stompClient.send("/app/talk/room.insert/"+this.user.id, {}, JSON.stringify(talk));
  }

  findMessage() {
    // console.log("room.message.find", "/app/talk/room.message.find/"+(this.talk.idx))
    stompClient.send("/app/talk/room.message.find/"+(this.talk.idx ? this.talk.idx : 1), {}, JSON.stringify({}));
  }

  insertMessage(message) {
    // console.log("room.message.insert", "/app/talk/room.message.insert/"+(this.talk.idx))
    stompClient.send("/app/talk/room.message.insert/"+this.talk.idx, {}, JSON.stringify(message));
  }

  // update() {
  //   stompClient.send("/app/talk/room.update", {}, JSON.stringify({}));
  // }
  //
  // delete() {
  //   stompClient.send("/app/talk/room.delete", {}, JSON.stringify({}));
  // }

  startTalk() {
    // stompClient.subscribe('/talk/room.message/'+this.talk.idx, response => {
    //   const responseBody = JSON.parse(response.body);
    //   console.log("messages", responseBody);
    //   this.messages = responseBody;
    // });
  }

  endTalk() {
    // stompClient.unsubscribe('/talk/room.message/'+this.talk.idx);
  }

}
