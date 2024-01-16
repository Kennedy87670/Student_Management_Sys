package com.forAll.sms.mapper;


import com.forAll.sms.dto.ParentsDto;
import com.forAll.sms.entity.Parents;

public class ParentMapper {

    public static ParentsDto mapToParentDto(Parents parents){
        ParentsDto parentsDto = new ParentsDto(
                parents.getId(),
                parents.getFirstName(),
                parents.getLastName(),
                parents.getEmail(),
                parents.getUserName()
        );

        return parentsDto;
    }

    public static Parents mapToParent(ParentsDto parentsDto){
        Parents parents = new Parents(
                parentsDto.getId(),
                parentsDto.getFirstName(),
                parentsDto.getLastName(),
                parentsDto.getEmail(),
                parentsDto.getUserName()
        );
        return parents;
    }



}
