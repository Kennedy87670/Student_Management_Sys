package com.forAll.sms.service;

import com.forAll.sms.dto.StudentDto;
import com.forAll.sms.errorAndException.UserNameExistException;

import java.util.List;
import java.util.Map;

public interface StudentService {

    public List<StudentDto> getAllStudents();

    void createStudent(StudentDto studentDto) ;


    StudentDto getStudentById(Long studentId);

    void updateStudent(StudentDto studentDto);

    void deleteStudent(Long studentId);
}
