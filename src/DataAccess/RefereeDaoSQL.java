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
        return true;
    }
}
