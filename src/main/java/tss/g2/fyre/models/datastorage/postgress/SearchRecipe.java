package tss.g2.fyre.models.datastorage.postgress;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tss.g2.fyre.models.entity.recipe.Recipe;

/**
 * Class for search recipe by ingredient name.
 * @author Andrey Sherstyuk
 */
public class SearchRecipe {
  private Connection connection;
  private String ingredient;

  /**
   * Constructor.
   *
   * @param connection connection to database
   * @param ingredient ingredient for search
   */
  public SearchRecipe(Connection connection, String ingredient) {
    this.connection = connection;
    this.ingredient = ingredient;
  }

  /**
   * Method for search recipe by ingredient.
   * @return the recipe found
   */
  public List<Recipe> searchRecipe() {
    List<Recipe> recipeList = new ArrayList<>();

    try (PreparedStatement searchStatement = connection
            .prepareStatement("select * from recipe "
                    + "where recipecomposition like '%' || ? || '%'")) {
      searchStatement.setString(1, ingredient);

      try (ResultSet resultSet = searchStatement.executeQuery()) {
        new SelectRecipes().fillRecipeList(recipeList, resultSet);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return recipeList;
  }
}