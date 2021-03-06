package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by whilemouse on 17. 10. 13.
 */
public class RoomDto {

    @Data
    public static class Summary {
        private long idx;
        private String subject;
        private String description;
        private int participantLimit;
        private List<ParticipantDto.Summary> participantList;
        private UserDto.Summary createdUser;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        private Date createdDate;
    }

    @Data
    public static class Detail {
        private long idx;
        private String subject;
        private String description;
        private int participantLimit;
        private List<ParticipantDto.Detail> participantList;
        private List<MessageDto.Detail> messageList;
        private UserDto.Summary createdUser;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        private Date createdDate;
    }

    @Data
    public static class Create {
        private String subject;
        private String description;
        private int participantLimit;
        private UserDto.Refer createdUser;
    }

    @Data
    public static class Update {
        private long idx;
        private String subject;
        private String description;
        private int participantLimit;
    }

    @Data
    public static class Delete {
        private long idx;
    }

    @Data
    public static class Refer {
        private long idx;

        public Refer() {}

        public Refer(long idx) {
            this.idx = idx;
        }
    }

}
