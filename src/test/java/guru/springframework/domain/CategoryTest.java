package guru.springframework.domain;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


public class CategoryTest {

    Category category;

    @Before
    public void setUp() {
        category = new Category();
    }


    @Test
    public void getId() {
        Long id = 4L;
        category.setId(id);

        assertEquals(id, category.getId());
    }

    @Test
    public void getRecipes() {

    }

    @Test
    public void getDescription() {
    }
}
