package ssvv.example.repository;

import domain.Tema;
import org.junit.jupiter.api.Test;
import repository.TemaRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.TemaValidator;

import static org.junit.jupiter.api.Assertions.*;

public class AddTemaJUnitTest {

    private final TemaRepository temaRepository = new TemaRepository(new TemaValidator());


    @Test
    public void testSuccesfulAdd() {
        Tema tema = temaRepository.save(new Tema("1", "Cea mai grea tema", 14, 12));

        assertNull(tema);


    }

    @Test
    public void testUnsuccesfulAdd_Duplicate() {
        temaRepository.save(new Tema("22", "Cea mai grea tema", 14,12));


        assertThrows(Exception.class,() -> temaRepository.save(new Tema("22", "Cea mai grea tema", 14,12)));
    }
    @Test
    public void testUnsuccesfulAdd_InvalidDeadline() {

        assertThrows(Exception.class,() -> temaRepository.save(new Tema("22", "Cea mai grea tema", 15, 12)));

    }
    @Test
    public void testUnsuccesfulAdd_InvalidStartline() {

        assertThrows(Exception.class,() -> temaRepository.save(new Tema("22", "Cea mai grea tema", 14, 15)));
    }

    @Test
    public void testUnsuccesfulAdd_StartlineHigherThanDeadline() {
        assertThrows(Exception.class,() -> temaRepository.save(new Tema("22", "Cea mai grea tema", 14, 15)));

    }

    @Test
    public void testUnsuccesfulAdd_DescriptionEmpty() {
        assertThrows(Exception.class,() -> temaRepository.save(new Tema("22", "", 14, 15)));

    }
    @Test
    public void testUnsuccesfulAdd_IdEmpty() {
        assertThrows(Exception.class,() -> temaRepository.save(new Tema("", "Cea mai grea tema", 14, 15)));
    }

    @Test
    public void testUnsuccesfulAdd_DescriptionNull() {
        assertThrows(Exception.class,() -> temaRepository.save(new Tema("12", null, 14, 15)));

    }
}
