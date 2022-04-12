package ssvv.example.repository;

import domain.Tema;
import org.junit.jupiter.api.Test;
import repository.TemaXMLRepository;
import service.Service;
import validation.TemaValidator;

import static org.junit.jupiter.api.Assertions.*;

public class AddTemaJUnitTest {

    private TemaXMLRepository temaXMLRepository= new TemaXMLRepository(new TemaValidator(),"tema.xml");

    private Service service = new Service(null, temaXMLRepository, null);

    @Test
    public void testSuccesfulAdd() {
        int result = service.saveTema("20", "Cea mai grea tema", 14, 12);

        assertEquals(1, result);

        service.deleteTema("20");


    }
    @Test
    public void testUnsuccesfulAdd() {
        int result = service.saveTema("22", "Cea mai grea tema", 14,12);

        assertEquals(1, result);

        int result1 = service.saveTema("22", "Cea mai grea tema", 14,12);

        assertEquals(0, result1);

        service.deleteTema("22");


    }
}
