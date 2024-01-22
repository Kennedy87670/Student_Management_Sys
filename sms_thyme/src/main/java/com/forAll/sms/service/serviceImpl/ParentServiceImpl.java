package com.forAll.sms.service.serviceImpl;

import com.forAll.sms.dto.ParentsDto;

import com.forAll.sms.dto.StaffDto;
import com.forAll.sms.entity.Parents;
import com.forAll.sms.mapper.ParentMapper;
import com.forAll.sms.mapper.StaffMapper;
import com.forAll.sms.repository.ParentRepository;
import com.forAll.sms.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

    private final ParentRepository parentRepository;

    @Override
    public List<ParentsDto> getAllParents() {
        List<Parents> parents = parentRepository.findAll();
        List<ParentsDto> parentsDto = parents.stream()
                .map(ParentMapper::mapToParentDto)
                .collect(Collectors.toList());
        return parentsDto;
    }

    @Override
    public void deleteParent(Long parentId) {
        parentRepository.deleteById(parentId);
    }

    @Override
    public ParentsDto findUserByEmail(String email) {
        return parentRepository.findByEmail(email);
    }


    @Override
    public void createParent(ParentsDto parentDto) {
        Parents parent = ParentMapper.mapToParent(parentDto);
        parentRepository.save(parent);
    }

    @Override
    public ParentsDto getParentById(Long parentId) {
        Parents parent = parentRepository.findById(parentId).orElse(null);
        if (parent != null) {
            return ParentMapper.mapToParentDto(parent);
        }
        return null;
    }


    @Override
    public void updateParent(ParentsDto parentDto) {
        parentRepository.save(ParentMapper.mapToParent(parentDto));
    }

}
