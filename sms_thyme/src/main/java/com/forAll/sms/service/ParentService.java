package com.forAll.sms.service;

import com.forAll.sms.dto.ParentsDto;

import java.util.List;

public interface ParentService {


    void updateParent(ParentsDto parentDto);

    ParentsDto getParentById(Long parentId);

    void createParent(ParentsDto parentDto);

    List<ParentsDto> getAllParents();


    void deleteParent(Long parentId);
}

