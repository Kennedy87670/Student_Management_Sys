package com.forAll.sms.controller;

import com.forAll.sms.dto.ParentsDto;
import com.forAll.sms.entity.User;
import com.forAll.sms.service.ParentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/parent")
@RequiredArgsConstructor
public class ParentController {
    private final ParentService parentService;

    // handle list parents request
    @GetMapping({"/all", "/list"})
    public ModelAndView listParents() {
        ModelAndView mav =  new ModelAndView("parents");
        List<ParentsDto> parents = parentService.getAllParents();
        mav.addObject("parents", parents);
        return mav;
    }

    // handler to handle new parent request
    @GetMapping("/new")
    public ModelAndView newParent() {
        ModelAndView mav = new ModelAndView("create_parent");
        ParentsDto parentDto = new ParentsDto();
        mav.addObject("parent", parentDto);
        return mav;
    }

    // handler to handle save parent form
    @PostMapping("/save")
    public String saveParent(@Valid @ModelAttribute("parent") ParentsDto parentDto,
                             BindingResult result,
                             Model model) {
        if(result.hasErrors()){
            model.addAttribute("parent", parentDto);
            return "create_parent";
        }


        parentService.createParent(parentDto);
        return "redirect:/parent/all";
    }

    // handler to handle edit parent request
    @GetMapping("/{parentId}/edit")
    public ModelAndView editParent(@PathVariable("parentId") Long parentId) {
        ModelAndView mav = new ModelAndView("edit_parent");
        ParentsDto parent = parentService.getParentById(parentId);
        mav.addObject("parent", parent);
        return mav;
    }

    // handler method to handle edit parent form
    @PostMapping("/{parentId}/update")
    public String updateParent(@PathVariable("parentId") Long parentId,
                               @Valid @ModelAttribute("parent") ParentsDto parentDto,
                               BindingResult result,
                               Model model) {
        if(result.hasErrors()){
            model.addAttribute("parent", parentDto);
            return "edit_parent";
        }
        parentDto.setId(parentId);
        parentService.updateParent(parentDto);
        return "redirect:/parent/all";
    }

    // Handler method to handle delete student request
    @GetMapping("/{parentId}/delete")
    public String deleteStudent(@PathVariable("parentId") Long parentId){
        parentService.deleteParent(parentId);
        return "redirect:/parent/all";
    }
}

