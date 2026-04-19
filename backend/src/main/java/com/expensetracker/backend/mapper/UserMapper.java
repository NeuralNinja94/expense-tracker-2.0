package com.expensetracker.backend.mapper;

import com.expensetracker.backend.entities.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.expensetracker.backend.dto.UserDto;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserDto toDto(AppUser appUser);
    @Mapping(target = "passwort", ignore = true)
    AppUser toEntity(UserDto userDto);
}
