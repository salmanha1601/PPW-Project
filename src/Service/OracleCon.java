package Service;

import jdk.nashorn.internal.runtime.ECMAException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OracleCon {
    //information to connect to the database
    private Connection conn=null;
    private final String username="salmanh";
    private final String password="abcd";
    private static final String connectionUrl="jdbc:oracle:thin:@ora1.ise.bgu.ac.il:1521/oracle";
    private String driver="oracle.jdbc.driver.OracleDriver";

    public OracleCon(){
        super();
        connect();
    }

    //connection to oracle database
    private void connect() {
        try{
            Class.forName(this.driver);
            this.conn= DriverManager.getConnection(connectionUrl,username,password);
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startConnection(){
        if(this.conn!=null){
            connect();
        }
    }

    public void endConnection() throws SQLException{
        if(this.conn != null){
            this.conn.close();
        }
    }

    public void creatTable(String qry){
        if(this.conn==null){
            connect();
        }
        PreparedStatement ps = null;
        String query = qry;
        try{
            ps = conn.prepareStatement(query); //compiling query in the DB
            ps.executeUpdate();
            conn.commit();
        }catch (SQLException e) {
            try{
                conn.rollback();
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
    }




}
