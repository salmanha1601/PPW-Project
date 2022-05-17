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
            connection=this.dbc.startConnection();
            String qry="SELECT * FROM LEAGUE WHERE LEAGUENAME= "+ leagueName;
            ps = connection.prepareStatement(query); //compiling query in the DB
            ps.setLong(1, mid);
            ps.setString(2, title);
            ps.setInt(3, prod_year);
            ps.executeUpdate();
            connection.commit();
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
        }
        return result;
    }
}
