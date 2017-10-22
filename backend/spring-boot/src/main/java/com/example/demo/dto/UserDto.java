package com.example.demo.dto;

import lombok.Data;
import org.joda.time.DateTime;

/**
 * Created by whilemouse on 17. 10. 13.
 */
public class UserDto {

    @Data
    public static class Create {
        private String password;
        private String userName;
    }

    @Data
    public static class Update {
        private long id;
        private String password;
        private String userName;
    }

    @Data
    public static class Summary {
        private long id;
        private String userName;
    }

    @Data
    public static class Response {
        private long id;
        private String userName;
        private boolean enabled;
        private DateTime createdDate;
        private DateTime lastModifiedDate;

//        private List<UserRoleDto.Response> userRoleList;
    }


    @Data
    public static class Refer {
        private long id;

        Refer() {}

        public Refer(long id) {
            this.id = id;
        }
    }

}
