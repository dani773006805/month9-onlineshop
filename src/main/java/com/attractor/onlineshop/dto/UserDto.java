package com.attractor.onlineshop.dto;

import com.attractor.onlineshop.model.BaseEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto extends BaseEntity {
    @Size(min=8)
    @NotBlank
    private String email;
    @Size(min = 8)
    @NotBlank
    private String password;
    @NotBlank
    @Size(min = 5)
    private String username;
}
