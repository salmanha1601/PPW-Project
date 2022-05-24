import DataAccess.*;
import Domain.UserController;
import Service.UserApplication;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class unitTest {

    UserApplication ua = new UserApplication();
    //TEST DATABASE
    GameDaoSQL gameDaoSQL = GameDaoSQL.getInstance();
    LeagueDaoSQL leagueDaoSQL = LeagueDaoSQL.getInstance();
    RefereeDaoSQL refereeDaoSQL = RefereeDaoSQL.getInstance();
    TeamDaoSQL teamDaoSQL = TeamDaoSQL.getInstance();
    UserDaoSQL userDaoSQL = UserDaoSQL.getInstance();

    @Test
    public void TestFindGame() throws SQLException {
        assertEquals("Must get true", true, gameDaoSQL.findGame("real madrid", "barcelona", "LA LEAGUE"));
        assertEquals("Must get false", false, gameDaoSQL.findGame("macabi haifa", "Hapoel Beer Sheva", "LA League"));
    }

    @Test
    public void TestFindGameStadium() throws SQLException {
        assertEquals("Must get false", false, gameDaoSQL.findGameStadium("real madrid", "barcelona", "sintago", "LA LEAGUE"));
        assertEquals("Must get true", true, gameDaoSQL.findGameStadium("liverpool", "barcelona", "sintago", "LA LEAGUE"));
    }
    @Test
    public void TestDateGameFree() throws SQLException {
        assertEquals("Must get true",true,gameDaoSQL.dateGameFree("barcelona",Date.valueOf("2021-12-16"),"LA LEAGUE"));
        assertEquals("Must get false",false,gameDaoSQL.dateGameFree("barcelona",Date.valueOf("2021-12-20"),"LA LEAGUE"));
    }
    @Test
    public void testHostCheck() throws SQLException {
        assertTrue(teamDaoSQL.hostCheck("real madrid")==3);
        assertFalse(teamDaoSQL.hostCheck("barcelona")==2);
    }
    @Test
    public void testAwayCheck() throws SQLException {
        assertTrue(teamDaoSQL.awayCheck("chelsea")==15);
        assertFalse(teamDaoSQL.awayCheck("valencia")==9);
    }

    @Test
    public void testFindLeague() throws SQLException {
        assertEquals("Must get true",true,leagueDaoSQL.findLeague("LA LEAGUE"));
        assertEquals("Must get false",false,leagueDaoSQL.findLeague("ISRAELI LEAGUE"));
    }

    @Test
    public void testFindUserByName() throws SQLException {
        assertEquals("Must get true",true,userDaoSQL.findUserByName("rony","referee"));
        assertEquals("Must get false",false,userDaoSQL.findUserByName("eee","referee"));
        assertEquals("Must get false",false,userDaoSQL.findUserByName("eyal","rrr"));
    }

    @Test
    public void testFindUserByEmail() throws SQLException {
        assertEquals("Must get true",true,userDaoSQL.findUserByEmail("salman@gmail.com", "123456s"));
        assertEquals("Must get false",false,userDaoSQL.findUserByEmail("sss@gmail","aaa"));
    }
    @Test
    public void testFindLeagueReferees() throws SQLException {
        assertEquals("Must get 4",4,leagueDaoSQL.findLeagueReferees("LA LEAGUE"));
        assertEquals("Must get 15",15,leagueDaoSQL.findLeagueReferees("Premier League"));
    }
    @Test
    public void testFindRefereeByName() throws SQLException {
        assertEquals("Must get true",true,refereeDaoSQL.findRefereeByName("rony","LA LEAGUE"));
        assertEquals("Must get false",false,refereeDaoSQL.findRefereeByName("aaa","LA LEAGUE"));
        assertEquals("Must get false",false,refereeDaoSQL.findRefereeByName("eyal","Premier League"));

    }


}