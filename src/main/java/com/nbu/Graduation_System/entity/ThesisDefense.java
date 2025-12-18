package com.nbu.Graduation_System.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nbu.Graduation_System.entity.base.BaseEntity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "thesis_defenses")
public class ThesisDefense extends BaseEntity {

    private Double grade;

    @ManyToOne(optional = false)
    @JoinColumn(name = "session_id")
    private DefenseSession session;

    @ManyToOne(optional = false)
    @JoinColumn(name = "thesis_id")
    private Thesis thesis;
}
