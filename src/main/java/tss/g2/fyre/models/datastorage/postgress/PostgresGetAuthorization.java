package tss.g2.fyre.models.datastorage.postgress;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tss.g2.fyre.models.entity.Person;

/**
 * CLass for get authorization from postgres.
 *
 * @author Anton Kudryavtsev
 */
class PostgresGetAuthorization {

  private Connection connection;
  private String login;

  /**
   * Constructor.
   *
   * @param connection postgres jdbc connection
   * @param login for authorization
   */
  PostgresGetAuthorization(Connection connection, String login) {
    this.connection = connection;
    this.login = login;
  }

  /**
   * Method for get authorization info.
   *
   * @return authorization
   */
  Person getAuthorization() {
    Person result = null;
    try (PreparedStatement statement =
                 connection.prepareStatement("SELECT * FROM person WHERE login = ?")) {
      statement.setString(1, login);
      System.out.println(statement.toString());
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        String password = resultSet.getString("password");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        boolean bannedStatus = resultSet.getBoolean("bannedStatus");
        String email = resultSet.getString("email");
        result = new Person(login, password, name, surname, bannedStatus, email);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
}
