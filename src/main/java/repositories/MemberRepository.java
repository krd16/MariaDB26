package repositories;

import daos.MemberDAO;
import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// This repository allows to cast the database model to a java model
public class MemberRepository {
    public boolean createMemeber(MemberDAO member) {
        boolean OK = true;

        try (
                Connection conn = new DBConnection().connect();
                // statement variable created in try-with-resources because creates a closable data stream
                // (try-with-resources manages closable streams automatically)
                PreparedStatement statement = conn.prepareStatement("insert into member(first_name, last_name, birth_year, join_date, leave_date, position_id, category_id) values(?, ?, ?, ?, ?, ?, ?)")
        ) {
            statement.setString(1, member.getFirstName());
            statement.setString(2, member.getLastName());
            statement.setInt(3, member.getBirthYear());
            statement.setDate(4, member.getJoinDate());
            statement.setDate(5, member.getLeaveDate());
            statement.setInt(6, member.getPositionId());
            statement.setInt(7, member.getCategoryId());

            statement.executeUpdate();
        } catch (SQLException e) {
            OK = false;
        }

        return OK;
    }
}
