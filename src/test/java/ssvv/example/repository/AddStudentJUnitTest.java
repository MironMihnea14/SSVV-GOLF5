package ssvv.example.repository;

import domain.Student;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import repository.StudentRepository;
import repository.StudentXMLRepository;
import validation.StudentValidator;

import static org.junit.jupiter.api.Assertions.*;

public class AddStudentJUnitTest {
    private StudentRepository studentRepository = new StudentRepository(new StudentValidator());

    @Test
    public void testValidEntityWorks() {
        Student student = new Student("1", "George", 932, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    public void testInvalidNameLengthMinThrowsError() {
        Student student = new Student("1", "a", 932, "george@ubbcluj.ro", "Marcel");

        assertThrows(Exception.class, () -> studentRepository.save(student));
    }

    @Test
    public void testInvalidNameLengthMaxThrowsError() {
        Student student = new Student(
                "1",
                new String(new char[105]).replace('\0', 'a'),
                932,
                "george@ubbcluj.ro",
                "Marcel"
        );

        assertThrows(Exception.class, () -> studentRepository.save(student));
    }

    @Test
    public void testInvalidTeacherLengthMinThrowsError() {
        Student student = new Student("1", "George", 932, "george@ubbcluj.ro", "a");

        assertThrows(Exception.class, () -> studentRepository.save(student));
    }

    @Test
    public void testInvalidTeacherLengthMaxThrowsError() {
        Student student = new Student("1", "George", 932, "george@ubbcluj.ro", new String(new char[101]).replace('\0', 'a'));

        assertThrows(Exception.class, () -> studentRepository.save(student));
    }

    @Test
    public void testInvalidEmailThrowsError() {
        Student student = new Student("1", "George", 932, "george", "Marcel");

        assertThrows(Exception.class, () -> studentRepository.save(student));
    }

    @Test
    public void testId0ThrowsError() {
        Student student = new Student("0", "George", 932, "george@ubbcluj.ro", "Marcel");

        assertThrows(Exception.class, () -> studentRepository.save(student));
    }

    @Test
    public void testId2ThrowsError() {
        Student student = new Student("2", "George", 932, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    public void testIdMaxMinus1Works() {
        Student student = new Student(String.valueOf(Integer.MAX_VALUE - 1), "George", 932, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    public void testIdMaxWorks() {
        Student student = new Student(String.valueOf(Integer.MAX_VALUE), "George", 932, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    public void testIdMaxPlusOneThrowsError() {
        Student student = new Student(String.valueOf(Integer.MAX_VALUE + 1), "George", 932, "george@ubbcluj.ro", "Marcel");

        assertThrows(Exception.class, () -> studentRepository.save(student));
    }

    @Test
    public void testNameAbWorks() {
        Student student = new Student("1", "ab", 932, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    public void testNameAbcWorks() {
        Student student = new Student("1", "abc", 932, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    public void testNameA99Works() {
        Student student = new Student(
                "1",
                new String(new char[99]).replace('\0', 'a'),
                932,
                "george@ubbcluj.ro",
                "Marcel"
        );

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    public void testName100Works() {
        Student student = new Student(
                "1",
                new String(new char[100]).replace('\0', 'a'),
                932,
                "george@ubbcluj.ro",
                "Marcel"
        );

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    public void testTeacherAbWorks() {
        Student student = new Student("1", "George", 932, "george@ubbcluj.ro", "ab");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    public void testTeacherAbcWorks() {
        Student student = new Student("1", "George", 932, "george@ubbcluj.ro", "abc");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    public void testTeacherA99Works() {
        Student student = new Student(
                "1",
                "George",
                932,
                "george@ubbcluj.ro",
                new String(new char[99]).replace('\0', 'a')
        );

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    public void testTeacher100Works() {
        Student student = new Student(
                "1",
                "George",
                932,
                "george@ubbcluj.ro",
                new String(new char[100]).replace('\0', 'a')
        );

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    public void testGroup1Works() {
        Student student = new Student("1", "George", 1, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    public void testGroup2Works() {
        Student student = new Student("1", "George", 2, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    public void testGroupMaxMinus1Works() {
        Student student = new Student("1", "George", Integer.MAX_VALUE - 1, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    public void testGroupMaxWorks() {
        Student student = new Student("1", "George", Integer.MAX_VALUE, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }
}
