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
            String qry="SELECT * FROM GAMES WHERE FIRSTTEAM= '"+team1+"' AND SECONDTEAM= '"+team2+"' AND LEAGUE= '"+leagueName+"'";
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

    public boolean findGameStadium(String team1, String team2, String stadium, String leagueName) throws SQLException {
        boolean result=false;
        PreparedStatement ps=null;
        try {
            Connection connection=this.dbc.startConnection();
            String qry="SELECT * FROM GAMES WHERE FIRSTTEAM= '"+team1+"' AND SECONDTEAM= '"+team2+"' AND LEAGUE= '"+leagueName+"' AND STADIUM IS NULL";
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

    public boolean dateGameFree(String team, Date date, String leagueName) throws SQLException {
        boolean result=false;
        PreparedStatement ps=null;
        try {
            Connection connection=this.dbc.startConnection();
            String qry="SELECT * FROM GAMES WHERE (FIRSTTEAM= '"+team+"' OR SECONDTEAM= '"+team+"') AND LEAGUE= '"+leagueName+ "' AND GAMEDATE=TO_DATE('"+date+"', 'yyyy-mm-dd') ";
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

    public boolean assignGame(String refereeName1, String refereeName2, String refereeName3, String refereeName4, String team1, String team2,
                              String stadium, String leagueName, Date date) throws SQLException {
        boolean result=false;
        PreparedStatement ps=null;

        try{
            Connection connection=this.dbc.startConnection();
            String qry="UPDATE GAMES SET STADIUM='"+stadium+"',MAINREFEREE = '"+refereeName1+"', FIRSTLINEREFEREE= '"+refereeName2+"', SECONDLINEREFEREE='"+refereeName3+"', HELPREFEREE='"+refereeName4+"',GAMEDATE=TO_DATE('"+date+"', 'yyyy-mm-dd') WHERE FIRSTTEAM='"+team1+"' AND SECONDTEAM='"+team2+"'";
            ps = connection.prepareStatement(qry); //compiling query in the DB
            ps.executeUpdate();
            connection.commit();
        }catch (SQLException e) {
            try{
                this.dbc.endConnection();
            }catch (SQLException e2) {
                e2.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            try{
                if(ps != null){
                    ps.close();
                }
            }catch (SQLException e3) {
                e3.printStackTrace();
            }
            this.dbc.endConnection();
        }
        return true;
    }



    public boolean revertGame( String team1, String team2) throws SQLException {
        boolean result=false;
        PreparedStatement ps=null;

        try{
            Connection connection=this.dbc.startConnection();
            String qry="UPDATE GAMES SET STADIUM=NULL ,MAINREFEREE = NULL, FIRSTLINEREFEREE= NULL, SECONDLINEREFEREE=NULL, HELPREFEREE=NULL,GAMEDATE=NULL WHERE FIRSTTEAM='"+team1+"' AND SECONDTEAM='"+team2+"'";
            ps = connection.prepareStatement(qry); //compiling query in the DB
            ps.executeUpdate();
            connection.commit();
        }catch (SQLException e) {
            try{
                this.dbc.endConnection();
            }catch (SQLException e2) {
                e2.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            try{
                if(ps != null){
                    ps.close();
                }
            }catch (SQLException e3) {
                e3.printStackTrace();
            }
            this.dbc.endConnection();
        }
        return true;
    }

}
