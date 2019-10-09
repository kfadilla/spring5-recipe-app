package guru.springframework;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository);
    }
    @Test
    public void getRecipe() throws Exception{

        Recipe recipe = new Recipe();
        Set recipeData = new HashSet();
        recipeData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipeData);
        Set<Recipe> recipes = recipeService.getRecipe();

        assertEquals(recipes.size(), 1);

        verify(recipeRepository, times(1)).findAll();

    }

    @Test
    public void findByIdTest() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        //recipeRepository.save(recipe);
        Optional<Recipe> optionalRecipe = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);
        Recipe returned = recipeService.findById(1L);
        assertEquals(recipe, returned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }
}