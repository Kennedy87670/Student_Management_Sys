package com.forAll.sms.service;

import com.forAll.sms.dto.StaffDto;

import java.util.List;

public interface StaffService {


    List<StaffDto> getAllStaff();

    void createStaff(StaffDto staffDto);

    StaffDto getStaffById(Long staffId);

    void updateStaff(StaffDto staffDto);

    void deleteStaff(Long staffId);
}
