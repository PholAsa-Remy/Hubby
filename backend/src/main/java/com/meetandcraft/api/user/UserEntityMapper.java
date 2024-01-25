package com.meetandcraft.api.user;

import com.meetandcraft.api.member.MemberDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserEntityMapper {
    public static UserEntityDto mapToDto (UserEntity userEntity) {
        UserEntityDto userEntityDto = new UserEntityDto();
        userEntityDto.setId(userEntity.getId());
        userEntityDto.setUsername(userEntity.getUsername());
        userEntityDto.setRoles(new ArrayList<>(userEntity.getRoles()));

        return userEntityDto;
    }

    public static UserEntity mapToEntity (UserEntityDto userEntityDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userEntityDto.getId());
        userEntity.setUsername(userEntityDto.getUsername());
        userEntity.setRoles(new ArrayList<>(userEntityDto.getRoles()));
        return userEntity;
    }
}
