package tss.g2.fyre.models.actions.auth;

import tss.g2.fyre.models.Answer;
import tss.g2.fyre.models.datastorage.DataStorage;

/**
 * Action class for delete recipe from database.
 */
public class DeleteRecipe implements ActionAuth {

  private DataStorage dataStorage;
  private String recipeId;

  /**
   * Constructor.
   *
   * @param dataStorage data storage object
   * @param recipeId recipe id
   */
  public DeleteRecipe(DataStorage dataStorage, String recipeId) {
    this.dataStorage = dataStorage;
    this.recipeId = recipeId;
  }

  @Override
  public Answer getAnswer(String user, String role) {
    return new Answer<>(true, dataStorage.deleteRecipe(recipeId, user));
  }
}
