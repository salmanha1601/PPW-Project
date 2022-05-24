import DataAccess.*;
import Domain.UserController;
import Service.UserApplication;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class AcceptenceTests {

    UserApplication ua = new UserApplication();
    //TEST DATABASE
    GameDaoSQL gameDaoSQL=GameDaoSQL.getInstance();
    //LeagueDaoSQL leagueDaoSQL=LeagueDaoSQL.getInstance();
    RefereeDaoSQL refereeDaoSQL=RefereeDaoSQL.getInstance();
    //TeamDaoSQL teamDaoSQL=TeamDaoSQL.getInstance();
    //UserDaoSQL userDaoSQL=UserDaoSQL.getInstance();

    @Test
    public void testLogin() {
        assertEquals("Must get 'You have logged in successfully'", "You have logged in successfully", ua.Login("salman@gmail.com", "123456s"));
        assertEquals("Must get 'This user is not in the system'", "This user is not in the system", ua.Login("gsa", "gsa"));
        assertEquals("Must get 'This user is not in the system'", "This user is not in the system", ua.Login("gsa", "salman"));
        assertEquals("Must get 'This user is not in the system'", "This user is not in the system", ua.Login("salman", "gsa"));
    }

    @Test
    public void testRegisterReferee() throws SQLException {
        assertEquals("Must get You have registered referee successfully", "You have registered referee successfully", ua.registerReferee("shachar", "LA LEAGUE"));
        assertEquals("Must get The referee is already registered to the system", "The referee is already registered to the system", ua.registerReferee("shachar", "LA LEAGUE"));
        refereeDaoSQL.deletereferee("shachar", "LA LEAGUE");
        assertEquals("Must get 'Referee not in system'", "Referee not in system", ua.registerReferee("gsa", "LA LEAGUE"));
        assertEquals("Must get 'Referee not in system'", "Referee not in system", ua.registerReferee("salman", "LA LEAGUE"));
        assertEquals("Must get 'League not in system'", "League not in system", ua.registerReferee("eyal", "Israeli LEAGUE"));
        assertEquals("Must get 'The capacity of referees is full'", "The capacity of referees is full", ua.registerReferee("eyal", "Premier League"));
    }

    @Test
    public void testAssginGames() throws SQLException {
        assertEquals("Must get 'Game assigned successfully'", "Game assigned successfully", ua.assginGames("tair", "eyal", "shachar", "rony", "valencia", "barcelona", "sntiago", "LA LEAGUE", Date.valueOf("2021-12-18")));
        gameDaoSQL.revertGame("valencia", "barcelona");
        assertEquals("Must get 'League not in system'", "League not in system", ua.assginGames("tair", "eyal", "shachar", "rony", "real madrid", "valencia", "bernabeu", "Israeli LEAGUE", Date.valueOf("2021-12-1")));
        assertEquals("Must get 'Referee not in system'", "Referee not in system", ua.assginGames("gsa", "eyal", "shachar", "rony", "real madrid", "valencia", "bernabeu", "LA LEAGUE", Date.valueOf("2021-12-2")));
        assertEquals("Must get 'Referee not in system'", "Referee not in system", ua.assginGames("tair", "gsa", "shachar", "rony", "real madrid", "valencia", "bernabeu", "LA LEAGUE", Date.valueOf("2021-12-3")));
        assertEquals("Must get 'Referee not in system'", "Referee not in system", ua.assginGames("tair", "eyal", "gsa", "rony", "real madrid", "valencia", "bernabeu", "LA LEAGUE", Date.valueOf("2021-12-4")));
        assertEquals("Must get 'Referee not in system'", "Referee not in system", ua.assginGames("tair", "eyal", "shachar", "gsa", "real madrid", "valencia", "bernabeu", "LA LEAGUE", Date.valueOf("2021-12-5")));
        assertEquals("Must get 'Game not in system'", "Game not in system", ua.assginGames("tair", "eyal", "shachar", "rony", "macabi haifa", "valencia", "bernabeu", "LA LEAGUE", Date.valueOf("2021-12-6")));
        assertEquals("Must get 'Game not in system'", "Game not in system", ua.assginGames("tair", "eyal", "shachar", "rony", "real madrid", "hapoel beer sheva", "bernabeu", "LA LEAGUE", Date.valueOf("2021-12-7")));
        assertEquals("Must get 'Game already assigned'", "Game already assigned", ua.assginGames("tair", "eyal", "shachar", "rony", "real madrid", "barcelona", "sntiago", "LA LEAGUE", Date.valueOf("2021-12-13")));
        assertEquals("Must get 'Team is already playing at this date'", "Team is already playing at this date", ua.assginGames("tair", "eyal", "shachar", "rony", "valencia", "barcelona", "bernabeu", "LA LEAGUE", Date.valueOf("2021-12-16")));
        assertEquals("Must get 'Team is already playing at this date'", "Team is already playing at this date", ua.assginGames("tair", "eyal", "shachar", "rony", "valencia", "barcelona", "san pihoan", "LA LEAGUE", Date.valueOf("2021-12-17")));
        assertEquals("Must get 'Team can't play home game'", "Team can't play home game", ua.assginGames("tair", "eyal", "shachar", "rony", "liverpool", "barcelona", "pihoan", "LA LEAGUE", Date.valueOf("2021-12-29")));
        assertEquals("Must get 'Team can't play away game'", "Team can't play away game", ua.assginGames("tair", "eyal", "shachar", "rony", "barcelona", "chelsea", "metrapolitano", "LA LEAGUE", Date.valueOf("2021-12-4")));
    }
}