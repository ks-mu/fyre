package tss.g2.fyre.models.actions.simple;

import tss.g2.fyre.models.Answer;
import tss.g2.fyre.models.actions.Action;
import tss.g2.fyre.models.datastorage.DataStorage;

/**
 * Action for get recipe.
 */
public class GetRecipe  implements Action {

  private String recipeId;
  private DataStorage dataStorage;

  /**
   * Constructor.
   * @param dataStorage storage
   * @param recipeId id of recipe
   */
  public GetRecipe(DataStorage dataStorage, String recipeId) {
    this.recipeId = recipeId;
    this.dataStorage = dataStorage;
  }

  @Override
  public Answer getAnswer() {
    try {
      return new Answer<>(true, dataStorage.getRecipe(recipeId));
    } catch (Exception e) {
      return new Answer(false);
    }
  }
}
