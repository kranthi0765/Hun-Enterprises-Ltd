package com.hub_entrprice.ltd.dto;
import com.hub_entrprice.ltd.constants.AuthConstants;
import com.hub_entrprice.ltd.constants.GrnericConstant;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    @NotNull(message = AuthConstants.ERROR_FIRST_NAME_REQUIRED)
    @Size(min = 3, message = AuthConstants.ERROR_FIRST_NAME_MIN_3_CHARS)
    private String firstName;

    @NotNull(message = AuthConstants.ERROR_LAST_NAME_REQUIRED)
    @Size(min = 3, message = AuthConstants.ERROR_LAST_NAME_MIN_3_CHARS)
    private String lastName;

    @NotNull(message = AuthConstants.ERROR_EMAIL_REQUIRED)
    @Pattern(regexp = GrnericConstant.EMAIL_REGEX, message = AuthConstants.ERROR_EMAIL_INVALID)
    private String email;

    @NotNull(message = AuthConstants.ERROR_PASSWORD_REQUIRED)
    @Size(min = 6, message = AuthConstants.ERROR_PASSWORD_MIN_6_CHARS)
    private String password;

    @NotNull(message = AuthConstants.ERROR_PHONE_NUMBER_REQUIRED)
    private String phoneNumber;

    public Object getUsername() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
    }
    
}
