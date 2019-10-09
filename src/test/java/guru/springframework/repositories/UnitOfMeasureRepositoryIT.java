package guru.springframework.repositories;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void findByDescription() {
        Optional<UnitOfMeasure> ts = unitOfMeasureRepository.findByDescription("Teaspoon");
        assertEquals("Teaspoon", ts.get().getDescription());
        Optional<UnitOfMeasure> tbs = unitOfMeasureRepository.findByDescription("Tablespoon");
        assertEquals("Tablespoon", tbs.get().getDescription());
        Optional<UnitOfMeasure> cup = unitOfMeasureRepository.findByDescription("Cup");
        assertEquals("Cup", cup.get().getDescription());
        Optional<UnitOfMeasure> pinch = unitOfMeasureRepository.findByDescription("Pinch");
        assertEquals("Pinch", pinch.get().getDescription());
        Optional<UnitOfMeasure> ounce = unitOfMeasureRepository.findByDescription("Ounce");
        assertEquals("Ounce", ounce.get().getDescription());
        Optional<UnitOfMeasure> dash = unitOfMeasureRepository.findByDescription("Dash");
        assertEquals("Dash", dash.get().getDescription());
        Optional<UnitOfMeasure> each = unitOfMeasureRepository.findByDescription("Each");
        assertEquals("Each", each.get().getDescription());
    }
}