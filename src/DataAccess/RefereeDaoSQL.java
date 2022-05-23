package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefereeDaoSQL implements Dao{
    private static final RefereeDaoSQL instance = new RefereeDaoSQL();
    OracleCon dbc = OracleCon.getInstance();
    //private constructor to avoid client applications to use constructor
    public static RefereeDaoSQL getInstance(){
        return instance;
    }

    public boolean addReferee(String leagueName, String refereeName) throws SQLException {
        boolean result=false;
        PreparedStatement ps=null;
        Connection connection=null;
        try{
            String qry="INSERT INTO REFEREES(REFEREENAME, LEAUGENAME)"+
                    " VALUES(?,?)"; //query
            connection=this.dbc.startConnection();
            ps = connection.prepareStatement(qry); //compiling query in the DB
            ps.setString(1, refereeName);
            ps.setString(2, leagueName);
            ps.executeUpdate();
            connection.commit();
            result=true;
        }catch (SQLException e) {
            try{
                connection.rollback();
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
        return result;
    }
}
