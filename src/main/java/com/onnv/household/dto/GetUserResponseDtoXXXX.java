package com.onnv.household.dto;

import com.onnv.household.enums.Gender;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GetUserResponseDtoXXXX {
    public GetUserResponseDtoXXXX() {
    }

    private String id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private Gender gender;

    private Date birthDate;

    private List<UserRole> userRoleList;

    @Data
    private static class UserRole {
        private UserRoleId id;

        @Data
        private static class UserRoleId {
            private String user_id;
            private String role_id;
        }
    }
}
