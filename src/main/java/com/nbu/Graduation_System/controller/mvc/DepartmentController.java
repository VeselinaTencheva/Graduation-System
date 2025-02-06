package com.nbu.Graduation_System.controller.mvc;

import com.nbu.Graduation_System.service.department.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.nbu.Graduation_System.util.MapperUtil;
import com.nbu.Graduation_System.viewmodel.department.DepartmentViewModel;

import java.util.List;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public String listDepartments(Model model) {
        List<DepartmentViewModel> departments = mapperUtil
                .mapList(this.departmentService.findAll(), DepartmentViewModel.class);
        model.addAttribute("departments", departments);
        return "/departments/list";
    }

    @GetMapping("/{id}")
    public String viewDepartment(@PathVariable Long id, Model model) {
        DepartmentViewModel department = mapperUtil.getModelMapper().map(
                departmentService.findById(id), DepartmentViewModel.class);
        model.addAttribute("department", department);
        return "departments/view";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        this.departmentService.deleteById(id);
        return "redirect:/departments";
    }
}
