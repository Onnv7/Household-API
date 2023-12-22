package com.onnv.household.dto.response;

import com.onnv.household.enums.Gender;
import lombok.Data;
import java.util.Date;

@Data
public class GetUserProfileByIdResponse {
    private String email;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Date birthDate;
}
