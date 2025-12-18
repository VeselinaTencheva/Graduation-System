package com.nbu.Graduation_System.entity;

import com.nbu.Graduation_System.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "defense_sessions")
public class DefenseSession extends BaseEntity {

    @Column(nullable = false)
    private LocalDateTime defenseDate;

    @ManyToMany
    @JoinTable(
        name = "defense_session_committee",
        joinColumns = @JoinColumn(name = "session_id"),
        inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private List<Teacher> committeeMembers = new ArrayList<>();

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ThesisDefense> defenses = new ArrayList<>();
}
