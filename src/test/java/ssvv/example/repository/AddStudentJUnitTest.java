package ssvv.example.repository;

import domain.Student;
import org.junit.Before;
import org.junit.Test;
import repository.StudentXMLRepository;
import validation.StudentValidator;

public class AddStudentJUnitTest {
    StudentXMLRepository studentXMLRepository;

    @Test
    public void addStudentTest() {
        studentXMLRepository = new StudentXMLRepository(new StudentValidator(), "studenti.xml");
        Student studentToBeAdded = new Student("14", "altNumeNou", 935);

        Student addedStudent = this.studentXMLRepository.save(studentToBeAdded);
        this.studentXMLRepository.delete(studentToBeAdded.getID());

        assert addedStudent == null;
    }
}
