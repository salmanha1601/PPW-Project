package DataAccess;

import java.sql.*;

public class UserDaoSQL implements Dao{
    private static final UserDaoSQL instance = new UserDaoSQL();
    OracleCon dbc = OracleCon.getInstance();
    //private constructor to avoid client applications to use constructor
    public static UserDaoSQL getInstance(){
        return instance;
    }

    public boolean findUserByEmail(String email, String password) throws SQLException {
        boolean result=false;
        Statement statement=null;
        try {
            Connection connection=this.dbc.startConnection();
            String qry="SELECT * FROM USERS WHERE EMAIL= '"+email+"' AND PASS= '"+password+"'";
            statement= connection.createStatement();
            ResultSet rs=statement.executeQuery(qry);
            rs.next();
            if(rs.getRow()==1){
                result=true;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.dbc.endConnection();
        }
        return result;
    }

    public boolean findUserByName(String refereeName, String role) throws SQLException {
        boolean result=false;
        PreparedStatement ps=null;
        try {
            Connection connection=this.dbc.startConnection();
            String qry="SELECT * FROM USERS WHERE USERNAME= "+refereeName+"AND USERROLE= "+role;
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
}
