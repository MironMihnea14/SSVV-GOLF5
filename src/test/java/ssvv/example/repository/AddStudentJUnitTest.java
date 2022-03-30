package ssvv.example.repository;

import domain.Student;
import org.junit.Before;
import org.junit.Test;
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
    public void testInvalidNameLengthMin_shouldThrowError() {
        Student student = new Student("1", "a", 932, "george@ubbcluj.ro", "Marcel");

        assertThrows(Exception.class, () -> studentRepository.save(student));
    }

    @Test
    public void testInvalidNameLengthMax_shouldThrowError() {
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
    public void testInvalidTeacherLengthMin_shouldThrowError() {
        Student student = new Student("1", "George", 932, "george@ubbcluj.ro", "a");

        assertThrows(Exception.class, () -> studentRepository.save(student));
    }

    @Test
    public void testInvalidTeacherLengthMax_shouldThrowError() {
        Student student = new Student("1", "George", 932, "george@ubbcluj.ro", new String(new char[101]).replace('\0', 'a'));

        assertThrows(Exception.class, () -> studentRepository.save(student));
    }

    @Test
    public void testInvalidEmail_shouldThrowError() {
        Student student = new Student("1", "George", 932, "george", "Marcel");

        assertThrows(Exception.class, () -> studentRepository.save(student));
    }

    @Test
    public void testId0() {
        Student student = new Student("0", "George", 932, "george@ubbcluj.ro", "Marcel");

        assertThrows(Exception.class, () -> studentRepository.save(student));
    }

    @Test
    public void testId2() {
        Student student = new Student("2", "George", 932, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    public void testIdMaxMinus1() {
        Student student = new Student(String.valueOf(Integer.MAX_VALUE - 1), "George", 932, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    public void testIdMax() {
        Student student = new Student(String.valueOf(Integer.MAX_VALUE), "George", 932, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    public void testIdMaxPlusOne() {
        Student student = new Student(String.valueOf(Integer.MAX_VALUE + 1), "George", 932, "george@ubbcluj.ro", "Marcel");

        assertThrows(Exception.class, () -> studentRepository.save(student));
    }

    @Test
    public void testNameAb() {
        Student student = new Student("1", "ab", 932, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    public void testNameAbc() {
        Student student = new Student("1", "abc", 932, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    public void testNameA99() {
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
    public void testName100() {
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
    public void testTeacherAb() {
        Student student = new Student("1", "George", 932, "george@ubbcluj.ro", "ab");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    public void testTeacherAbc() {
        Student student = new Student("1", "George", 932, "george@ubbcluj.ro", "abc");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    public void testTeacherA99() {
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
    public void testTeacher100() {
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
    public void testGroup1() {
        Student student = new Student("1", "George", 1, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    public void testGroup2() {
        Student student = new Student("1", "George", 2, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    public void testGroupMaxMinus1() {
        Student student = new Student("1", "George", Integer.MAX_VALUE - 1, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    public void testGroupMax() {
        Student student = new Student("1", "George", Integer.MAX_VALUE, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }
}
