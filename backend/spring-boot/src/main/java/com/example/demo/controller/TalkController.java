package com.example.demo.controller;

import com.example.demo.dto.MessageDto;
import com.example.demo.dto.RoomDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.service.TalkService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;

/**
 * Created by whilemouse on 17. 10. 13.
 */
@Controller
public class TalkController {

    @Autowired
    private SimpMessagingTemplate messageSender;

    @Autowired
    private TalkService talkServiceImpl;

    @Autowired
    private UserService userServiceImpl;

    @SubscribeMapping("/talk/room.find")
    @SendTo("/talk/room.list")
    public List<RoomDto.Summary> findRoom() {
        return talkServiceImpl.findRoomSummaryList();
    }

    @SubscribeMapping("/talk/room.enter/{id}")
    public void enterRoom(@DestinationVariable long id, RoomDto.Refer room, Principal user) {
        User principalUser = userServiceImpl.getUesr(user);

        talkServiceImpl.participateRoom(room.getIdx(), principalUser.getId());
        messageSender.convertAndSend("/talk/room.enter/" + id, talkServiceImpl.findRoomDetail(room.getIdx()));
    }

    @SubscribeMapping("/talk/room.insert/{id}")
//    @SendTo({"/talk/room.list", "/talk/room.insert/{id}"})
    public void insertRoom(@DestinationVariable long id, RoomDto.Create room, Principal user) {
        User principalUser = userServiceImpl.getUesr(user);
        room.setCreatedUser(new UserDto.Refer(principalUser.getId()));
        messageSender.convertAndSend("/talk/room.enter/" + id, talkServiceImpl.createRoom(room));
        messageSender.convertAndSend("/talk/room.list", talkServiceImpl.findRoomSummaryList());
    }

    @SubscribeMapping("/talk/room.update")
    @SendTo("/talk/room.list")
    public List<RoomDto.Summary> updateRoom(RoomDto.Update room) {
        talkServiceImpl.updateRoom(room);
        return talkServiceImpl.findRoomSummaryList();
    }

    @SubscribeMapping("/talk/room.delete")
    @SendTo("/talk/room.list")
    public List<RoomDto.Summary> deleteRoom(RoomDto.Delete room) {
        talkServiceImpl.deleteRoom(room);
        return talkServiceImpl.findRoomSummaryList();
    }

//    @SubscribeMapping("/talk/room.participate/{room_idx}")
//    @SendTo("/talk/room.list")
//    public List<RoomDto.Summary> participateRoom(@DestinationVariable int roomIdx) {
//        talkServiceImpl.participateRoom(roomIdx);
//        return talkServiceImpl.findRoomSummaryList();
//    }
//
//    @SubscribeMapping("/talk/room.desert/{room_idx}/participant/{participant_idx}")
//    @SendTo("/talk/room.list")
//    public List<RoomDto.Summary> desertRoom(@DestinationVariable int roomIdx, @DestinationVariable int participantIdx) {
//        talkServiceImpl.desertRoom(roomIdx, participantIdx);
//        return talkServiceImpl.findRoomSummaryList();
//    }

    @SubscribeMapping("/talk/room.message.find/{roomIdx}")
    @SendTo("/talk/room.message.list/{roomIdx}")
    public List<MessageDto.Summary> findMessage(@DestinationVariable long roomIdx) {
        return talkServiceImpl.findMessageSummaryList(roomIdx);
    }

    @SubscribeMapping("/talk/room.message.insert/{roomIdx}")
    @SendTo("/talk/room.message.list/{roomIdx}")
    public List<MessageDto.Summary> insertMessage(@DestinationVariable long roomIdx, MessageDto.Create message, Principal user) {
        User principalUser = userServiceImpl.getUesr(user);
        message.setCreatedUser(new UserDto.Refer(principalUser.getId()));
        message.setRoom(new RoomDto.Refer(roomIdx));
        talkServiceImpl.createMessage(message);
        return talkServiceImpl.findMessageSummaryList(roomIdx);
    }

}
