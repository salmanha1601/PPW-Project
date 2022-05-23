package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LeagueDaoSQL implements Dao{
    private static final LeagueDaoSQL instance = new LeagueDaoSQL();
    OracleCon dbc = OracleCon.getInstance();
    //private constructor to avoid client applications to use constructor
    public static LeagueDaoSQL getInstance(){
        return instance;
    }

    public boolean findLeague(String leagueName) throws SQLException {
        boolean result=false;
        PreparedStatement ps=null;
        try {
            Connection connection=this.dbc.startConnection();
            String qry="SELECT * FROM LEAGUE WHERE LEAGUENAME= '"+ leagueName+"'";
            ps= connection.prepareStatement(qry);
            ResultSet rs=ps.executeQuery();
            rs.next();
            if(rs.getRow()==1){
                result=true;
            }
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
        return result;
    }

    public int findLeagueReferees(String leagueName) throws SQLException {
        int refereesNumber=0;
        PreparedStatement ps=null;
        try {
            Connection connection=this.dbc.startConnection();
            String qry="SELECT * FROM LEAGUE WHERE LEAGUENAME= '"+ leagueName+"'";
            ps= connection.prepareStatement(qry);
            ResultSet rs=ps.executeQuery();
            rs.next();
            refereesNumber=rs.getInt("NUMREFEREES");
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
        return refereesNumber;
    }
}
