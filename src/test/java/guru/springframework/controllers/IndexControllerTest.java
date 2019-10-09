package guru.springframework.controllers;

import guru.springframework.RecipeService;
import guru.springframework.RecipeServiceImpl;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import javafx.beans.binding.When;
import org.h2.index.Index;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {
    @Mock
    RecipeService recipeService;
    @Mock
    Model model;
    IndexController indexController;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }


    /*
    test whether mvc is running, and check view is running properly
     */
    @Test
    public void testMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void showIndex() {

        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());
        Recipe sth = new Recipe();
        sth.setId(3L);
        recipes.add(sth);
        when(recipeService.getRecipe()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
        //when
        String viewName = indexController.showIndex(model);
        assertEquals("index", viewName);

        //then
        Mockito.verify(recipeService, times(1)).getRecipe();
        Mockito.verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }
}