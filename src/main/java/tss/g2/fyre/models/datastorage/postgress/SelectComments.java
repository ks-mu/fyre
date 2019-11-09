package tss.g2.fyre.models.datastorage.postgress;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tss.g2.fyre.models.entity.Comment;

public class SelectComments {
  private Logger selectCommentsLogger = LoggerFactory.getLogger(SelectComments.class);
  private Connection connection;
  private int recipeId;

  /**
   * Constructor.
   * @param connection connection to database
   * @param recipeId recipe id
   */
  public SelectComments(Connection connection, int recipeId) {
    this.connection = connection;
    this.recipeId = recipeId;
  }

  /**
   * Method for select all comments by recipe id.
   * @return comments list
   */
  public List<Comment> selectComments() {
    List<Comment> comments = new ArrayList<>();

    try (PreparedStatement selectCommentsStatement = connection
            .prepareStatement("select user_login, comment_text from comment where recipe_id = ?")) {
      selectCommentsStatement.setInt(1, recipeId);
      selectCommentsLogger.info(selectCommentsStatement.toString());

      try (ResultSet resultSet = selectCommentsStatement.executeQuery()) {
        while (resultSet.next()) {
          comments.add(new Comment(
                  resultSet.getString("user_login"),
                  resultSet.getString("comment_text")
          ));
        }
      }
    } catch (SQLException e) {
      selectCommentsLogger.error(e.getMessage());
    }

    return comments;
  }
}
