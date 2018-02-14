package com.example.demo.service;

import com.example.demo.dto.MessageDto;
import com.example.demo.dto.RoomDto;

import java.util.List;

/**
 * Created by whilemouse on 17. 10. 13.
 */
public interface TalkService {

    List<RoomDto.Summary> findRoomSummaryList();

    RoomDto.Detail findRoomDetail(long id);

    RoomDto.Detail createRoom(RoomDto.Create room);

    RoomDto.Detail updateRoom(RoomDto.Update room);

    void deleteRoom(RoomDto.Delete room);

    List<MessageDto.Summary> findMessageSummaryList(long roomIdx);

    void createMessage(MessageDto.Create message);

    void participateRoom(int roomIdx);

    void participateRoom(long roomIdx, long userIdx);

    void desertRoom(int roomIdx, int participantIdx);

}
