package com.nbu.Graduation_System.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.nbu.Graduation_System.entity.User;
import com.nbu.Graduation_System.entity.enums.UserRoleType;
import com.nbu.Graduation_System.service.teacher.TeacherService;
import com.nbu.Graduation_System.service.student.StudentService;
import com.nbu.Graduation_System.viewmodel.teacher.TeacherViewModel;
import com.nbu.Graduation_System.viewmodel.student.StudentViewModel;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SecurityUtils {
    
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final MapperUtil mapperUtil;

    public <T> T getCurrentUser(Class<T> viewModelClass) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        
        if (user.getRole() == UserRoleType.TEACHER && viewModelClass == TeacherViewModel.class) {
            return viewModelClass.cast(mapperUtil.getModelMapper().map(
                teacherService.findById(user.getId()), 
                TeacherViewModel.class
            ));
        } else if (user.getRole() == UserRoleType.STUDENT && viewModelClass == StudentViewModel.class) {
            return viewModelClass.cast(mapperUtil.getModelMapper().map(
                studentService.findById(user.getId()), 
                StudentViewModel.class
            ));
        }
        
        throw new IllegalStateException("User role " + user.getRole() + " does not match requested type " + viewModelClass.getSimpleName());
    }

    public TeacherViewModel getCurrentTeacher() {
        return getCurrentUser(TeacherViewModel.class);
    }

    public StudentViewModel getCurrentStudent() {
        return getCurrentUser(StudentViewModel.class);
    }

    public User getCurrentUserEntity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    public boolean isTeacher() {
        return getCurrentUserEntity().getRole() == UserRoleType.TEACHER;
    }

    public boolean isStudent() {
        return getCurrentUserEntity().getRole() == UserRoleType.STUDENT;
    }
}
