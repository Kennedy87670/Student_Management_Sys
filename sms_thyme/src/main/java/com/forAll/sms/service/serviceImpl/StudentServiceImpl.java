package com.forAll.sms.service.serviceImpl;

import com.forAll.sms.dto.StudentDto;
import com.forAll.sms.entity.Student;
import com.forAll.sms.errorAndException.UserNameExistException;
import com.forAll.sms.mapper.StudentMapper;
import com.forAll.sms.repository.StudentRepository;
import com.forAll.sms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDto = students.stream()
                .map((student) -> StudentMapper.maptToStudentDto(student))
                .collect(Collectors.toList());
        return studentDto;
    }

    @Override
    public void createStudent(StudentDto studentDto)
//            throws UserNameExistException
    {
//        Student student = StudentMapper.mapToStudent(studentDto);
//        studentRepository.save(student);


//    @Override
//    public StudentDto getStudentById(Long studentId) {
//        Student student = studentRepository.findById(studentId).get();
//        StudentDto studentDto = StudentMapper.maptToStudentDto(student);
//        return studentDto;
//    }

        // Check if the username already exists
//        if (isUsernameExists(studentDto.getUserName())) {
//            // Handle the case where the username already exists
////            throw new UserNameExistException("Username already exists: " + studentDto.getUserName());
//        }

        // If the username doesn't exist, proceed to create the student
        Student student = StudentMapper.mapToStudent(studentDto);
        studentRepository.save(student);
    }


    private boolean isUsernameExists(String username) {
        return studentRepository.existsByUserName(username);
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student != null) {
            return StudentMapper.maptToStudentDto(student);
        }
        return null;
    }

    @Override
    public void updateStudent(StudentDto studentDto) {
        studentRepository.save(StudentMapper.mapToStudent(studentDto));
    }

    @Override
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

}
