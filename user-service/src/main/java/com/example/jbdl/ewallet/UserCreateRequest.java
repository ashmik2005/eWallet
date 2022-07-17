package com.example.jbdl.ewallet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    @NotBlank
    private String name;

    @Email
    private String email;

    private String contact;

    public User to(){
        return User.builder()
                .name(this.name)
                .email(this.email)
                .contact(this.contact)
                .build();
    }

}
