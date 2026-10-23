package repositories;

import daos.MemberRoleDAO;
import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberRoleRepository {
    public boolean createMemberRole(MemberRoleDAO memberRole) {
        boolean OK = true;

        try(
                Connection conn = new DBConnection().connect();
                // Explanation on why is the statement declared here in MemberRepository.java
                PreparedStatement statement = conn.prepareStatement("")
        ) {
            // TODO insert the incoming member role data into the member_role table
        } catch (SQLException e) {
            OK = false;
        }

        return OK;
    }
}
