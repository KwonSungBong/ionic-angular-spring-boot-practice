package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created by whilemouse on 17. 10. 13.
 */
public class MessageDto {

    @Data
    public static class Detail {
        private long idx;
        private String content;
        private UserDto.Summary createdUser;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        private Date createdDate;
    }

    @Data
    public static class Summary {
        private long idx;
        private String content;
        private UserDto.Summary createdUser;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
        private Date createdDate;
    }

    @Data
    public static class Create {
        private String content;
        private RoomDto.Refer room;
        private UserDto.Refer createdUser;
    }

}
