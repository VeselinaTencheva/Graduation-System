package com.nbu.Graduation_System.config;

import com.nbu.Graduation_System.entity.*;
import com.nbu.Graduation_System.entity.enums.DepartmentType;
import com.nbu.Graduation_System.entity.enums.ThesisApplicationStatusType;
import com.nbu.Graduation_System.entity.enums.UserRoleType;
import com.nbu.Graduation_System.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PasswordEncoder encoder;


    public DbInit() {
    }

    @Transactional
    public void createDepartments() {
        // Skip if departments already exist
        if (departmentRepository.count() > 0) {
            return;
        }

        Arrays.stream(DepartmentType.values()).forEach(type -> {
            Department department = new Department();
            department.setType(type);
            department.setDescription(type.getName() + " Department");
            department.setContactEmail(type.name().toLowerCase().replace('_', '.') + "@nbu.bg");
            departmentRepository.save(department);
        });
    }

    private Teacher createTeacher(String name, String email, String academicTitle, DepartmentType departmentType) {
        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setEmail(email);
        teacher.setRole(UserRoleType.TEACHER);
        teacher.setAcademicTitle(academicTitle);
        teacher.setPassword(encoder.encode("password"));
        teacher.setSupervisedTheses(new ArrayList<>());
        teacher.setReviews(new ArrayList<>());
        teacher.setDepartment(departmentRepository.findByType(departmentType)
                .orElseThrow(() -> new RuntimeException("Department not found: " + departmentType)));
        return teacherRepository.save(teacher);
    }

    private Student createStudent(String name, String email, DepartmentType departmentType) {
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setRole(UserRoleType.STUDENT);
        student.setPassword(encoder.encode("password"));
        student.setDepartment(departmentRepository.findByType(departmentType)
                .orElseThrow(() -> new RuntimeException("Department not found: " + departmentType)));
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
        thesis.setUploadDate(LocalDateTime.now());
        thesis.setContent(content);
        thesis.setThesisApplication(application);
        return thesisRepository.save(thesis);
    }
// 
    private ThesisReview createThesisReview(Thesis thesis, Teacher reviewer, boolean isPositive, String content) {
        ThesisReview review = new ThesisReview();
        review.setThesis(thesis);
        review.setReviewer(reviewer);
        review.setPositive(isPositive);
        review.setComments(content);
        review.setReviewDate(LocalDateTime.now());
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

    @Override
    @Transactional
    public void run(String... args) {
        createDepartments();
        // Only initialize if the database is empty
        if (teacherRepository.count() > 0 || studentRepository.count() > 0) {
            return;
        }

        // Create teachers
        Teacher johnDoe = createTeacher(
            "John Doe", 
            "john.doe@nbu.bg", 
            "Professor",
            DepartmentType.COMPUTER_SCIENCE
        );

        Teacher janeSmith = createTeacher(
            "Jane Smith", 
            "jane.smith@nbu.bg", 
            "Associate Professor",
            DepartmentType.INFORMATICS
        );

        Teacher peterBrown = createTeacher(
            "Peter Brown", 
            "peter.brown@nbu.bg", 
            "Assistant Professor",
            DepartmentType.INFORMATION_TECHNOLOGIES
        );

        // Create students
        Student aliceJohnson = createStudent(
            "Alice Johnson", 
            "alice.johnson@nbu.bg", 
            DepartmentType.COMPUTER_SCIENCE
        );

        Student bobWilson = createStudent(
            "Bob Wilson", 
            "bob.wilson@nbu.bg", 
            DepartmentType.INFORMATICS
        );

        Student carolClark = createStudent(
            "Carol Clark", 
            "carol.clark@nbu.bg", 
            DepartmentType.INFORMATION_TECHNOLOGIES
        );

        // Create thesis applications
        ThesisApplication app1 = createThesisApplication(
            "AI in Education",
            "Develop AI-based educational tools",
            "Research existing solutions, Design architecture, Implement prototype",
            "Python, TensorFlow, React",
            ThesisApplicationStatusType.SUBMITTED,
            aliceJohnson,
            johnDoe
        );

        ThesisApplication app2 = createThesisApplication(
            "Smart Cities Development",
            "Create IoT infrastructure for smart cities",
            "Analyze requirements, Design network topology, Implement sensors",
            "IoT, Arduino, Cloud",
            ThesisApplicationStatusType.ACCEPTED,
            bobWilson,
            janeSmith
        );

        ThesisApplication app3 = createThesisApplication(
            "Blockchain in Finance",
            "Implement blockchain for financial transactions",
            "Study blockchain tech, Design system, Develop prototype",
            "Ethereum, Solidity, Web3",
            ThesisApplicationStatusType.REJECTED,
            carolClark,
            peterBrown
        );

        // Create theses
        Thesis thesis1 = createThesis(
            "AI Education App",
            "An AI-powered educational application that helps students learn more effectively through personalized content.",
            app1
        );

        Thesis thesis2 = createThesis(
            "Smart City IoT",
            "A comprehensive IoT infrastructure system for modern smart cities with sensor networks and data analytics.",
            app2
        );

        Thesis thesis3 = createThesis(
            "Blockchain System",
            "A blockchain-based system for secure and transparent record-keeping in educational institutions.",
            app3
        );

        // Create thesis reviews
        createThesisReview(thesis1, janeSmith, true, "Detailed analysis of machine learning applications in healthcare.");
        createThesisReview(thesis2, peterBrown, true, "Comprehensive study of sustainable architecture principles.");
        createThesisReview(thesis3, johnDoe, false, "Needs more practical examples and implementation details.");

        // Create thesis defenses with committee members and grades
        Set<Teacher> committee1 = new HashSet<>();
        committee1.add(johnDoe);
        committee1.add(janeSmith);
        committee1.add(peterBrown);
        createThesisDefense(thesis1, LocalDateTime.now().plusDays(7), 5.5, committee1);

        Set<Teacher> committee2 = new HashSet<>();
        committee2.add(johnDoe);
        committee2.add(janeSmith);
        createThesisDefense(thesis2, LocalDateTime.now().plusDays(14), 3.0, committee2);

        Set<Teacher> committee3 = new HashSet<>();
        committee3.add(janeSmith);
        committee3.add(peterBrown);
        createThesisDefense(thesis3, LocalDateTime.now().plusDays(21), 4.0, committee3);
    }
}
