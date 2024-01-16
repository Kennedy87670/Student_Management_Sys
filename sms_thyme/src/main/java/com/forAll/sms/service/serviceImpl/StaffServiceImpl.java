package com.forAll.sms.service.serviceImpl;

import com.forAll.sms.dto.StaffDto;
import com.forAll.sms.entity.Staff;
import com.forAll.sms.mapper.StaffMapper;
import com.forAll.sms.repository.StaffRepository;
import com.forAll.sms.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    @Override
    public List<StaffDto> getAllStaff() {
        List<Staff> staffList = staffRepository.findAll();
        List<StaffDto> staffDtoList = staffList.stream()
                .map(StaffMapper::mapToStaffDto)
                .collect(Collectors.toList());
        return staffDtoList;
    }

    @Override
    public void createStaff(StaffDto staffDto) {
        Staff staff = StaffMapper.mapToStaff(staffDto);
        staffRepository.save(staff);
    }

    @Override
    public StaffDto getStaffById(Long staffId) {
        Staff staff = staffRepository.findById(staffId).orElse(null);
        if (staff != null) {
            return StaffMapper.mapToStaffDto(staff);
        }
        return null;
    }

    @Override
    public void updateStaff(StaffDto staffDto) {
        staffRepository.save(StaffMapper.mapToStaff(staffDto));
    }

    @Override
    public void deleteStaff(Long staffId) {
        staffRepository.deleteById(staffId);
    }

}
