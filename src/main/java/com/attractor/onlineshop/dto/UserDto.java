package com.attractor.onlineshop.dto;

import com.attractor.onlineshop.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseEntity {
    private String email;
    private String password;
}
