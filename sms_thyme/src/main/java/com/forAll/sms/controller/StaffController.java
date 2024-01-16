package com.forAll.sms.controller;

import com.forAll.sms.dto.ParentsDto;
import com.forAll.sms.dto.StaffDto;
import com.forAll.sms.dto.StudentDto;
import com.forAll.sms.errorAndException.UserNameExistException;
import com.forAll.sms.service.ParentService;
import com.forAll.sms.service.StaffService;
import com.forAll.sms.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;
    private final StudentService studentService;
    private final ParentService parentService;

    // Staff
    @GetMapping({"/all", "/list", "staff"})
    public ModelAndView listStaff() {
        ModelAndView mav =  new ModelAndView("staff");
        List<StaffDto> staff = staffService.getAllStaff();
        mav.addObject("staff", staff);
        return mav;
    }

    @GetMapping("/new")
    public ModelAndView newStaff(){
        ModelAndView mav = new ModelAndView("create_staff");
        StaffDto staffDto = new StaffDto();
        mav.addObject("staff", staffDto);
        return mav;
    }

    @PostMapping("/save")
    public String saveStaff(@Valid @ModelAttribute("staff") StaffDto staffDto,
                            BindingResult result,
                            Model model){
        if(result.hasErrors()){
            model.addAttribute("staff", staffDto);
            return "create_staff";
        }
        staffService.createStaff(staffDto);
        return "redirect:/staff/all";
    }

    @GetMapping("/{staffId}/edit")
    public ModelAndView editStaff(@PathVariable("staffId") Long staffId) {
        ModelAndView mav = new ModelAndView("edit_staff");
        StaffDto staff = staffService.getStaffById(staffId);
        mav.addObject("staff", staff);
        return mav;
    }

    @PostMapping("/{staffId}/update")
    public String updateStaff(@PathVariable("staffId") Long staffId,
                              @Valid @ModelAttribute("staff") StaffDto staffDto,
                              BindingResult result,
                              Model model){
        if(result.hasErrors()){
            model.addAttribute("staff", staffDto);
            return "edit_staff";
        }
        staffDto.setId(staffId);
        staffService.updateStaff(staffDto);
        return "redirect:/staff/all";
    }

    @GetMapping("/{staffId}/delete")
    public String deleteStudent(@PathVariable("staffId") Long staffId){
        staffService.deleteStaff(staffId);
        return "redirect:/staff/all";
    }

    // Student
    @GetMapping("/student/all")
    public ModelAndView listStudents() {
        ModelAndView mav =  new ModelAndView("staff_students");
        List<StudentDto> students = studentService.getAllStudents();
        mav.addObject("students", students);
        return mav;
    }

    @GetMapping("/student/new")
    public ModelAndView newStudent(){
        ModelAndView mav = new ModelAndView("create_staff_student");
        StudentDto studentDto = new StudentDto();
        mav.addObject("student", studentDto);
        return mav;
    }

    @PostMapping("/student/save")
    public String saveStudent(@Valid @ModelAttribute("student") StudentDto studentDto,
                              BindingResult result,
                              Model model) throws UserNameExistException {
        if(result.hasErrors()){
            model.addAttribute("student", studentDto);
            return "create_staff_student";
        }
        studentService.createStudent(studentDto);
        return "redirect:/staff/student/all";
    }

    @GetMapping("/student/{studentId}/edit")
    public ModelAndView editStudent(@PathVariable("studentId") Long studentId) {
        ModelAndView mav = new ModelAndView("edit_staff_student");
        StudentDto student = studentService.getStudentById(studentId);
        mav.addObject("student", student);
        return mav;
    }

    @PostMapping("/student/{studentId}/update")
    public String updateStudent(@PathVariable("studentId") Long studentId,
                                @Valid @ModelAttribute("student") StudentDto studentDto,
                                BindingResult result,
                                Model model){
        if(result.hasErrors()){
            model.addAttribute("student", studentDto);
            return "edit_staff_student";
        }
        studentDto.setId(studentId);
        studentService.updateStudent(studentDto);
        return "redirect:/staff/student/all";
    }

    // Parent
    @GetMapping({"parent/all", "parent/list"})
    public ModelAndView listParents() {
        ModelAndView mav =  new ModelAndView("staff_parents");
        List<ParentsDto> parents = parentService.getAllParents();
        mav.addObject("parents", parents);
        return mav;
    }

    @GetMapping("/parent/new")
    public ModelAndView newParent() {
        ModelAndView mav = new ModelAndView("create_staff_parent");
        ParentsDto parentDto = new ParentsDto();
        mav.addObject("parent", parentDto);
        return mav;
    }

    @PostMapping("/parent/save")
    public String saveParent(@Valid @ModelAttribute("parent") ParentsDto parentDto,
                             BindingResult result,
                             Model model) {
        if(result.hasErrors()){
            model.addAttribute("parent", parentDto);
            return "create_staff_parent";
        }
        parentService.createParent(parentDto);
        return "redirect:/staff/parent/all";
    }

    @GetMapping("/parent/{parentId}/edit")
    public ModelAndView editParent(@PathVariable("parentId") Long parentId) {
        ModelAndView mav = new ModelAndView("edit_staff_parent");
        ParentsDto parent = parentService.getParentById(parentId);
        mav.addObject("parent", parent);
        return mav;
    }

    @PostMapping("/{parentId}/update")
    public String updateParent(@PathVariable("parentId") Long parentId,
                               @Valid @ModelAttribute("parent") ParentsDto parentDto,
                               BindingResult result,
                               Model model) {
        if(result.hasErrors()){
            model.addAttribute("parent", parentDto);
            return "edit_staff_parent";
        }
        parentDto.setId(parentId);
        parentService.updateParent(parentDto);
        return "redirect:/parent/all";
    }
}
