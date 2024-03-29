package tss.g2.fyre.models.actions.simple;

import tss.g2.fyre.models.Answer;
import tss.g2.fyre.models.actions.Action;
import tss.g2.fyre.models.datastorage.DataStorage;

/**
 * Action class for get types.
 */
public class GetTypes implements Action {
  private DataStorage dataStorage;

  /**
   * Constructor.
   *
   * @param dataStorage data storage object
   */
  public GetTypes(DataStorage dataStorage) {
    this.dataStorage = dataStorage;
  }

  @Override
  public Answer getAnswer() {
    return new Answer<>(true, dataStorage.getTypesInformation());
  }
}
