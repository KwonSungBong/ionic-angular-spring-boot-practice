package com.example.demo.dto;

import lombok.Data;

/**
 * Created by whilemouse on 17. 10. 13.
 */
public class ParticipantDto {

    @Data
    public static class Summary {
        private UserDto.Summary user;
    }

    @Data
    public static class Detail {
        private long idx;
        private UserDto.Summary user;
    }

    @Data
    public static class Create {
        private RoomDto.Refer room;
    }

    @Data
    public static class Delete {
        private long idx;
    }

}
