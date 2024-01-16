package com.forAll.sms.mapper;


import com.forAll.sms.dto.StudentDto;
import com.forAll.sms.entity.Student;


public class StudentMapper {

    public static StudentDto maptToStudentDto(Student student){
        StudentDto studentDto = new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getUserName(),
                student.getEmail()
        );
        return studentDto;
    }
    public static Student mapToStudent(StudentDto studentDto){
        Student students = new Student(
                studentDto.getId(),
                studentDto.getFirstName(),
                studentDto.getLastName(),
                studentDto.getUserName(),
                studentDto.getEmail()

        );
        return students;
    }




}
