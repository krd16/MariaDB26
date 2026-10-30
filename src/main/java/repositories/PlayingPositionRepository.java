package repositories;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayingPositionRepository {
    public int positionIdByName(String posName) {
        int positionId = 0;
        DBConnection dbConnection = new DBConnection();

        try(
                Connection conn = dbConnection.connect();
                PreparedStatement statement = conn.prepareStatement("select position_id from playing_position where name = ?");
        ) {
            statement.setString(1, posName);

            ResultSet res = statement.executeQuery();
            if (res.next()) {
                positionId = res.getInt("position_id");
            }
        } catch (SQLException e) {
            positionId = -1;
        }

        return positionId;
    }
}
