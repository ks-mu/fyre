package tss.g2.fyre.models.datastorage.postgress;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for adding type.
 *
 * @author Andrey Sherstyuk
 */
class AddType {
  private Logger addTypeLogger = LoggerFactory.getLogger(AddType.class);

  private Connection connection;
  private String typeName;
  private String description;

  /**
   * Constructor.
   *
   * @param connection connection to database
   * @param typeName name of type
   * @param description recipe description
   */
  AddType(Connection connection, String typeName, String description) {
    this.connection = connection;
    this.typeName = typeName;
    this.description = description;
  }

  /**
   * Method for adding type.
   *
   * @return result of adding type
   */
  boolean addType() {
    boolean result = false;
    try (PreparedStatement addTypeStatement = connection
            .prepareStatement("insert into type values (?, ?)")) {
      addTypeStatement.setString(1, typeName);
      addTypeStatement.setString(2, description);

      addTypeLogger.info(addTypeStatement.toString());
      result = addTypeStatement.executeUpdate() == 1;
    } catch (SQLException e) {
      addTypeLogger.error(e.getMessage());
    }

    return result;
  }
}
