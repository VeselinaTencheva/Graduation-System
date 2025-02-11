package com.nbu.Graduation_System.entity;

import com.nbu.Graduation_System.entity.enums.UserRoleType;
import com.nbu.Graduation_System.entity.base.BaseEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = "email", name = "uk_users_email")
})
public class User extends BaseEntity {
    private String name;
    
    @Column(unique = true)
    private String email;
    
    private String password;
    
    @Enumerated(EnumType.STRING)
    private UserRoleType role;
}
