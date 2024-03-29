package tss.g2.fyre.models.actions.auth;

import tss.g2.fyre.models.Answer;
import tss.g2.fyre.models.datastorage.DataStorage;

/**
 * Action class for update recipe.
 */
public class UpdateRecipe implements ActionAuth {
  private DataStorage dataStorage;
  private String recipeId;
  private String recipeName;
  private String composition;
  private String cookingSteps;

  /**
   * Constructor.
   *
   * @param dataStorage data storage object
   * @param recipeId recipe id
   * @param recipeName recipe name
   * @param composition composition
   * @param cookingSteps cooking steps
   */
  public UpdateRecipe(DataStorage dataStorage, String recipeId, String recipeName,
                      String composition, String cookingSteps) {
    this.dataStorage = dataStorage;
    this.recipeId = recipeId;
    this.recipeName = recipeName.replace("<", "&lt");
    this.composition = composition.replace("<", "&lt");
    this.cookingSteps = cookingSteps.replace("<img", "&ltimg")
            .replace("<script", "&ltscript")
            .replace("<meta", "&ltmeta")
            .replace("<style", "&ltstyle")
            .replace("<IMG", "&ltIMG")
            .replace("<SCRIPT", "&ltSCRIPT")
            .replace("<META", "&ltMETA")
            .replace("<STYLE", "&ltSTYLE");;
  }

  @Override
  public Answer getAnswer(String login, String role) {
    return new Answer<>(true, dataStorage
        .updateRecipe(recipeId, recipeName, composition, cookingSteps, login));
  }
}
