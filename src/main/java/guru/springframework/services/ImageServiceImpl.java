package guru.springframework.services;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {


    private final RecipeRepository recipeRepository;

    public ImageServiceImpl( RecipeRepository recipeService) {

        this.recipeRepository = recipeService;
    }

    @Override
    @Transactional
    public void saveImage(Long recipeId, MultipartFile file) {
        try {
            Recipe recipe = recipeRepository.findById(recipeId).get();
            log.debug("received image");
            Byte[] image = new Byte[file.getBytes().length];

            int i = 0;
            for (byte b : file.getBytes()) {
                image[i++] = b;
            }
            recipe.setImage(image);
            recipeRepository.save(recipe);
        }
        catch (IOException e) {
            log.debug("exceptions", e);
            e.printStackTrace();
        }
    }
}
