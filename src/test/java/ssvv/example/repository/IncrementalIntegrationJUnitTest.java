package ssvv.example.repository;

import domain.Nota;
import domain.Pair;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.NotaRepository;
import repository.StudentRepository;
import repository.TemaRepository;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IncrementalIntegrationJUnitTest {

    private StudentRepository studentRepository;
    private TemaRepository temaRepository;
    private NotaRepository notaRepository;

    @BeforeEach
    public void setUp() {
        studentRepository = new StudentRepository(new StudentValidator());
        temaRepository = new TemaRepository(new TemaValidator());
        notaRepository = new NotaRepository(new NotaValidator());
    }

    @Test
    void testAddStudent() {
        Student student = new Student("1", "George", 932, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));
        List<Student> studentList = StreamSupport
                .stream(
                        studentRepository
                                .findAll()
                                .spliterator(),
                        false
                ).collect(
                        Collectors.toList()
                );
        assertEquals(1, studentList.size());
    }

    @Test
    void testAddAssignmentPlusStudent() {
        Tema tema = new Tema("1", "test", 12, 10);
        Student student = new Student("1", "George", 932, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> temaRepository.save(tema));
        assertDoesNotThrow(() -> studentRepository.save(student));

        List<Tema> assignmentList = StreamSupport
                .stream(
                        temaRepository
                                .findAll()
                                .spliterator(),
                        false
                ).collect(
                        Collectors.toList()
                );
        List<Student> studentList = StreamSupport
                .stream(
                        studentRepository
                                .findAll()
                                .spliterator(),
                        false
                ).collect(
                        Collectors.toList()
                );
        assertEquals(1, assignmentList.size());
        assertEquals(1, studentList.size());
    }

    @Test
    void testAllAtOnce() {
        Student student = new Student("2", "George", 932, "george@ubbcluj.ro", "Marcel");
        Tema tema = new Tema("2", "test", 12, 10);
        Nota grade = new Nota(new Pair<>("2", "2"), 9.5, 11, "Almost perfect, great job");

        assertDoesNotThrow(() -> studentRepository.save(student));
        assertDoesNotThrow(() -> temaRepository.save(tema));
        assertDoesNotThrow(() -> notaRepository.save(grade));

        List<Student> studentList = StreamSupport
                .stream(
                        studentRepository
                                .findAll()
                                .spliterator(),
                        false
                ).collect(
                        Collectors.toList()
                );



        List<Tema> assignmentList = StreamSupport
                .stream(
                        temaRepository
                                .findAll()
                                .spliterator(),
                        false
                ).collect(
                        Collectors.toList()
                );



        List<Nota> gradesList = StreamSupport
                .stream(
                        notaRepository
                                .findAll()
                                .spliterator(),
                        false
                ).collect(
                        Collectors.toList()
                );

        assertEquals(1, studentList.size());
        assertEquals(1, assignmentList.size());
        assertEquals(1, gradesList.size());
    }
}