package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamDaoSQL implements Dao{
    private static final TeamDaoSQL instance = new TeamDaoSQL();

    //private constructor to avoid client applications to use constructor
    public static TeamDaoSQL getInstance(){
        return instance;
    }
    OracleCon dbc = OracleCon.getInstance();

    public int hostCheck(String team1) throws SQLException {
        int numberOFHost=1;
        PreparedStatement ps=null;
        try {
            Connection connection=this.dbc.startConnection();
            String qry="SELECT HOMEGAMES FROM TEAMS WHERE TEAMNAME='"+team1+"'";
            ps= connection.prepareStatement(qry);
            ResultSet rs=ps.executeQuery();
            rs.next();
            numberOFHost=rs.getInt("HOMEGAMES");
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.dbc.endConnection();
        }
        return numberOFHost;
    }

    public int awayCheck(String team2) throws SQLException {
        int numberOFAway=0;
        PreparedStatement ps=null;
        try {
            Connection connection=this.dbc.startConnection();
            String qry="SELECT * FROM TEAMS WHERE TEAMNAME='"+team2+"'";
            ps= connection.prepareStatement(qry);
            ResultSet rs=ps.executeQuery();
            rs.next();
            numberOFAway=rs.getInt("AWAYGAMES");
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.dbc.endConnection();
        }
        return numberOFAway;
    }
}
