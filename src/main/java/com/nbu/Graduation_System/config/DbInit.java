package com.nbu.Graduation_System.config;

import com.nbu.Graduation_System.entity.*;
import com.nbu.Graduation_System.entity.enums.ThesisApplicationStatusType;
import com.nbu.Graduation_System.entity.enums.UserRoleType;
import com.nbu.Graduation_System.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class DbInit implements CommandLineRunner {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ThesisApplicationRepository thesisApplicationRepository;

    @Autowired
    private ThesisRepository thesisRepository;

    @Autowired
    private ThesisReviewRepository thesisReviewRepository;

    @Autowired
    private ThesisDefenseRepository thesisDefenseRepository;

    public DbInit() {
    }

    @Override
    @Transactional
    public void run(String... args) {
        // Only initialize if the database is empty
        if (teacherRepository.count() > 0 || studentRepository.count() > 0) {
            return;
        }

        // Create teachers
        Teacher johnSmith = createTeacher("John Smith", "john.smith@nbu.bg", "Professor");
        Teacher mariaJones = createTeacher("Maria Jones", "maria.jones@nbu.bg", "Associate Professor");
        Teacher peterBrown = createTeacher("Peter Brown", "peter.brown@nbu.bg", "Assistant Professor");

        // Create students
        Student alexJohnson = createStudent("Alex Johnson", "alex.student@nbu.bg", "F12345");
        Student emmaWilson = createStudent("Emma Wilson", "emma.student@nbu.bg", "F12346");
        Student michaelDavis = createStudent("Michael Davis", "michael.student@nbu.bg", "F12347");

        // Create thesis applications
        ThesisApplication app1 = createThesisApplication(
            "AI in Education",
            "Develop AI-based educational tools",
            "Research existing solutions, Design architecture, Implement prototype",
            "Python, TensorFlow, React",
            ThesisApplicationStatusType.SUBMITTED,
            alexJohnson,
            johnSmith
        );

        ThesisApplication app2 = createThesisApplication(
            "Smart Cities Development",
            "Create IoT infrastructure for smart cities",
            "Analyze requirements, Design network topology, Implement sensors",
            "IoT, Arduino, Cloud",
            ThesisApplicationStatusType.ACCEPTED,
            emmaWilson,
            mariaJones
        );

        ThesisApplication app3 = createThesisApplication(
            "Blockchain in Finance",
            "Implement blockchain for financial transactions",
            "Study blockchain tech, Design system, Develop prototype",
            "Ethereum, Solidity, Web3",
            ThesisApplicationStatusType.REJECTED,
            michaelDavis,
            peterBrown
        );

        // Create theses
        Thesis thesis1 = createThesis(
            "Machine Learning in Healthcare",
            "Comprehensive research on applying ML algorithms in medical diagnosis...",
            app1
        );

        Thesis thesis2 = createThesis(
            "Sustainable Architecture",
            "Detailed study of eco-friendly building designs...",
            app2
        );

        Thesis thesis3 = createThesis(
            "Quantum Computing Applications",
            "In-depth exploration of practical applications of quantum computing...",
            app3
        );

        // Create thesis reviews
        createThesisReview(thesis1, mariaJones, true, "Detailed analysis of machine learning applications in healthcare.");
        createThesisReview(thesis2, peterBrown, true, "Comprehensive study of sustainable architecture principles.");
        createThesisReview(thesis3, johnSmith, false, "Needs more practical examples and implementation details.");

        // Create thesis defenses with committee members and grades
        Set<Teacher> committee1 = new HashSet<>();
        committee1.add(johnSmith);
        committee1.add(mariaJones);
        committee1.add(peterBrown);
        createThesisDefense(thesis1, LocalDateTime.now().plusDays(7), 9.5, committee1);

        Set<Teacher> committee2 = new HashSet<>();
        committee2.add(johnSmith);
        committee2.add(mariaJones);
        createThesisDefense(thesis2, LocalDateTime.now().plusDays(14), 8.0, committee2);

        Set<Teacher> committee3 = new HashSet<>();
        committee3.add(mariaJones);
        committee3.add(peterBrown);
        createThesisDefense(thesis3, LocalDateTime.now().plusDays(21), 9.0, committee3);
    }

    private Teacher createTeacher(String name, String email, String academicTitle) {
        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setEmail(email);
        teacher.setRole(UserRoleType.TEACHER);
        teacher.setAcademicTitle(academicTitle);
        teacher.setSupervisedTheses(new ArrayList<>());
        teacher.setReviews(new ArrayList<>());
        return teacherRepository.save(teacher);
    }

    private Student createStudent(String name, String email, String facultyNumber) {
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setRole(UserRoleType.STUDENT);
        student.setFacultyNumber(facultyNumber);
        return studentRepository.save(student);
    }

    private ThesisApplication createThesisApplication(
            String title,
            String objective,
            String tasks,
            String technologies,
            ThesisApplicationStatusType status,
            Student student,
            Teacher supervisor) {
        ThesisApplication application = new ThesisApplication();
        application.setTitle(title);
        application.setObjective(objective);
        application.setTasks(tasks);
        application.setTechnologies(technologies);
        application.setStatus(status);
        application.setStudent(student);
        application.setSupervisor(supervisor);
        application.setSubmissionDate(LocalDateTime.now());
        return thesisApplicationRepository.save(application);
    }

    private Thesis createThesis(String title, String content, ThesisApplication application) {
        Thesis thesis = new Thesis();
        thesis.setTitle(title);
        thesis.setContent(content);
        thesis.setThesisApplication(application);
        return thesisRepository.save(thesis);
    }

    private ThesisReview createThesisReview(Thesis thesis, Teacher reviewer, boolean isPositive, String content) {
        ThesisReview review = new ThesisReview();
        review.setThesis(thesis);
        review.setReviewer(reviewer);
        review.setPositive(isPositive);
        review.setContent(content);
        review.setSubmissionDate(LocalDateTime.now());
        return thesisReviewRepository.save(review);
    }

    private ThesisDefense createThesisDefense(Thesis thesis, LocalDateTime defenseDate, Double grade, Set<Teacher> committeeMembers) {
        ThesisDefense defense = new ThesisDefense();
        defense.setThesis(thesis);
        defense.setDefenseDate(defenseDate);
        defense.setGrade(grade);
        defense.setCommitteeMembers(committeeMembers);
        return thesisDefenseRepository.save(defense);
    }
}
