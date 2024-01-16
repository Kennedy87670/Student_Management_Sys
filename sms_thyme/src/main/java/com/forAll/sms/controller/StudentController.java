package com.forAll.sms.controller;

import com.forAll.sms.dto.StudentDto;
import com.forAll.sms.errorAndException.UserNameExistException;
import com.forAll.sms.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    // handle list students request

    @GetMapping({"/all", "/list"})
    public ModelAndView listStudents() {
        ModelAndView mav =  new ModelAndView("students");
        List<StudentDto> students = studentService.getAllStudents();
        mav.addObject("students", students);
                return mav;
    }

    // handler to handle new students request
    @GetMapping("/new")
    public ModelAndView newStudent(){
        ModelAndView mav = new ModelAndView("create_student");
        StudentDto studentDto = new StudentDto();
        mav.addObject("student", studentDto);
        return mav;
    }

    // handler to handle save students form
    @PostMapping("/save")
    public String saveStudent(@Valid @ModelAttribute("student") StudentDto studentDto,
                              BindingResult result,
                              Model model)
//            throws UserNameExistException
    {
        if(result.hasErrors()){
            model.addAttribute("student", studentDto);
            return "create_student";
        }
        studentService.createStudent(studentDto);
        return "redirect:/student/all";
    }

    //handler to handle edit student request

    @GetMapping("/{studentId}/edit")
    public ModelAndView editStudent(@PathVariable("studentId") Long studentId) {
        ModelAndView mav = new ModelAndView("edit_student");
        StudentDto student = studentService.getStudentById(studentId);
        mav.addObject("student", student);
        return mav;
    }

    //handler medthod to handle edit student form
    @PostMapping("/{studentId}/update")
    public String updateStudent(@PathVariable("studentId") Long studentId,
                                @Valid @ModelAttribute("student") StudentDto studentDto,
                                BindingResult result,
                                Model model){
        if(result.hasErrors()){
            model.addAttribute("student", studentDto);
            return "edit_student";
        }
        studentDto.setId(studentId);
        studentService.updateStudent(studentDto);
        return "redirect:/student/all";
}

    // Handler method to handle delete student request
    @GetMapping("/{studentId}/delete")
    public String deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
        return "redirect:/student/all";
    }
}
