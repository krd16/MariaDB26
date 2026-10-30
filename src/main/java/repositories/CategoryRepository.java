package repositories;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRepository {
    public int categoryIdByName(String catName) {
        int catId = 0;
        DBConnection dbConnection = new DBConnection();

        try(
                Connection conn = dbConnection.connect();
                PreparedStatement statement = conn.prepareStatement("select category_id from team_category where name = ?")
        ) {
            statement.setString(1, catName);

            ResultSet res = statement.executeQuery();
            if (res.next()) {
                catId = res.getInt("category_id");
            }
        } catch (SQLException e) {
            catId = -1;
        }

        return catId;
    }
}
