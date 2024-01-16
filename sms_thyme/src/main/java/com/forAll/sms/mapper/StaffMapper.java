package com.forAll.sms.mapper;


import com.forAll.sms.dto.StaffDto;
import com.forAll.sms.entity.Staff;


public class StaffMapper {

    public static StaffDto mapToStaffDto(Staff staff){
        StaffDto staffDto = new StaffDto(
                staff.getId(),
                staff.getFirstName(),
                staff.getLastName(),
                staff.getEmail(),
                staff.getUserName()
        );
        return staffDto;
    }
    public static Staff mapToStaff(StaffDto staffDto){
       Staff staff = new Staff(
               staffDto.getId(),
               staffDto.getFirstName(),
               staffDto.getLastName(),
               staffDto.getEmail(),
               staffDto.getUserName()
       );
       return staff;
    }

}
