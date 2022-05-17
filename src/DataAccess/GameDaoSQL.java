package DataAccess;

import java.sql.*;

public class GameDaoSQL implements Dao{
    private static final GameDaoSQL instance = new GameDaoSQL();
    OracleCon dbc = OracleCon.getInstance();

    //private constructor to avoid client applications to use constructor
    public static GameDaoSQL getInstance(){
        return instance;
    }

    public boolean findGame(String team1, String team2, String leagueName) throws SQLException {
        boolean result=false;
        PreparedStatement ps=null;
        try {
            Connection connection=this.dbc.startConnection();
            String qry="SELECT * FROM GAME WHERE FIRSTTEAM= "+team1+" AND SECONDTEAM= "+team2+" AND LEAGUE= "+leagueName;
            ps= connection.prepareStatement(qry);
            ResultSet rs=ps.executeQuery();
            rs.last();
            if(rs.getRow()>0){
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

    public boolean findGameStadium(String team1, String team2, String stadium, String leagueName) throws SQLException {
        boolean result=false;
        PreparedStatement ps=null;
        try {
            Connection connection=this.dbc.startConnection();
            String qry="SELECT * FROM GAME WHERE FIRSTTEAM= "+team1+" AND SECONDTEAM= "+team2+" AND LEAGUE= "+leagueName+" AND STADIUM= "+ stadium;
            ps= connection.prepareStatement(qry);
            ResultSet rs=ps.executeQuery();
            rs.last();
            if(rs.getRow()>0){
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

    public boolean dateGameFree(String team, Date date, String leagueName) throws SQLException {
        boolean result=false;
        PreparedStatement ps=null;
        try {
            Connection connection=this.dbc.startConnection();
            String qry="SELECT * FROM GAME WHERE (FIRSTTEAM= "+team+" OR SECONDTEAM= "+team+") AND LEAGUE= "+leagueName+ " AND GAMEDATE= "+date;
            ps= connection.prepareStatement(qry);
            ResultSet rs=ps.executeQuery();
            rs.last();
            if(rs.getRow()>0){
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

    public boolean assignGame(String refereeName1, String refereeName2, String refereeName3, String refereeName4, String team1, String team2, String stadium, String leagueName, Date date) throws SQLException {
        return true;
    }
}
