package com.meetandcraft.api.user;

import com.meetandcraft.api.member.MemberDto;

public class UserEntityMapper {
    public static UserEntityDto mapToDto (UserEntity userEntity) {
        UserEntityDto userEntityDto = new UserEntityDto();
        userEntityDto.setId(userEntity.getId());
        userEntityDto.setUsername(userEntity.getUsername());
        userEntityDto.setPassword(userEntity.getPassword());
        return userEntityDto;
    }
}
