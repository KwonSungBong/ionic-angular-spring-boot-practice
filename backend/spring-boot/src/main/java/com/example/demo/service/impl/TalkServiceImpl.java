package com.example.demo.service.impl;

import com.example.demo.dto.MessageDto;
import com.example.demo.dto.RoomDto;
import com.example.demo.entity.Message;
import com.example.demo.entity.Participant;
import com.example.demo.entity.Room;
import com.example.demo.entity.User;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TalkService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by whilemouse on 17. 10. 13.
 */
@Service("talkService")
public class TalkServiceImpl implements TalkService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public List<RoomDto.Summary> findRoomSummaryList() {
        return roomRepository.findAll().stream()
                .map(room ->  modelMapper.map(room, RoomDto.Summary.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RoomDto.Detail findRoomDetail(long id) {
        return modelMapper.map(roomRepository.findOne(id), RoomDto.Detail.class);
    }

    @Override
    @Transactional
    public RoomDto.Detail createRoom(RoomDto.Create room) {
        Room result = modelMapper.map(room, Room.class);

        User user = userRepository.findOne(result.getCreatedUser().getId());
        Participant participant = new Participant();
        participant.setUser(user);
        participant.setRoom(result);
        List<Participant> participantList = Arrays.asList(participant);
        result.setParticipantList(participantList);

        roomRepository.save(result);
        return modelMapper.map(result, RoomDto.Detail.class);
    }

    @Override
    @Transactional
    public RoomDto.Detail updateRoom(RoomDto.Update room) {
        Room result = modelMapper.map(room, Room.class);
        roomRepository.save(result);
        return modelMapper.map(result, RoomDto.Detail.class);
    }

    @Override
    public void deleteRoom(RoomDto.Delete room) {
        if(roomRepository.exists(room.getIdx())){
            roomRepository.delete(room.getIdx());
        }
    }

    @Override
    @Transactional
    public List<MessageDto.Summary> findMessageSummaryList(long roomIdx) {
        return messageRepository.findAll().stream()
                .filter(message -> message.getRoom().getIdx() == roomIdx)
                .map(message ->  modelMapper.map(message, MessageDto.Summary.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void createMessage(MessageDto.Create message) {
        Message result = modelMapper.map(message, Message.class);
        result.setCreatedDate(new Date());
        messageRepository.save(result);
    }

    @Override
    public void participateRoom(int roomIdx) {

    }

    @Override
    @Transactional
    public void participateRoom(long roomIdx, long userId) {
        Room room = roomRepository.findOne(roomIdx);
        User user = userRepository.findOne(userId);

        if(room.getParticipantList()
                .stream()
                .filter(participant -> participant.getRoom().getIdx() == roomIdx &&
                        participant.getUser().getId() == userId)
                .count() == 0) {
            Participant participant = new Participant();
            participant.setUser(user);
            participant.setRoom(room);
            room.getParticipantList().add(participant);
        }
    }

    @Override
    public void desertRoom(int roomIdx, int participantIdx) {

    }
}
