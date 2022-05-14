import Domain.UserController;
import Service.UserApplication;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class unitTest {

    UserApplication ua = new UserApplication();

    @Test
    public void testLogin() {
        assertEquals("Must get 'You have logged in successfully'", "You have logged in successfully", ua.Login("salman", "salman"));
        assertEquals("Must get 'This user is not in the system'", "This user is not in the system", ua.Login("gsa", "gsa"));
        assertEquals("Must get 'This user is not in the system'", "This user is not in the system", ua.Login("gsa", "salman"));
        assertEquals("Must get 'This user is not in the system'", "This user is not in the system", ua.Login("salman", "gsa"));
    }

    @Test
    public void testRegisterReferee() {
        assertEquals("Must get 'You have registered referee successfully'", "You have registered referee successfully", ua.registerReferee("eyal", "LA LEAGUE"));
        assertEquals("Must get 'Referee not in system'", "Referee not in system", ua.registerReferee("gsa", "LA LEAGUE"));
        assertEquals("Must get 'Referee not in system'", "Referee not in system", ua.registerReferee("salman", "LA LEAGUE"));
        assertEquals("Must get 'League not in system'", "League not in system", ua.registerReferee("eyal", "Israeli LEAGUE"));
//        need to insert full league with referee
        assertEquals("Must get 'The capacity of referees is full'", "The capacity of referees is full", ua.registerReferee("eyal", "LA LEAGUE"));
    }

    @Test
    public void testAssginGames() {
        assertEquals("Must get 'Game assigned successfully'", "Game assigned successfully", ua.assginGames("tair", "eyal", "shahar", "roni", "real madrid", "valencia", "bernabeu", "LA LEAGUE", Date.valueOf("2021-12-16")));
        assertEquals("Must get 'League not in system'", "League not in system", ua.assginGames("tair", "eyal", "shahar", "roni", "real madrid", "valencia", "bernabeu", "Israeli LEAGUE", Date.valueOf("2021-12-16")));
        assertEquals("Must get 'Referee not in system'", "Referee not in system", ua.assginGames("gsa", "eyal", "shahar", "roni", "real madrid", "valencia", "bernabeu", "LA LEAGUE", Date.valueOf("2021-12-16")));
        assertEquals("Must get 'Referee not in system'", "Referee not in system", ua.assginGames("tair", "gsa", "shahar", "roni", "real madrid", "valencia", "bernabeu", "LA LEAGUE", Date.valueOf("2021-12-16")));
        assertEquals("Must get 'Referee not in system'", "Referee not in system", ua.assginGames("tair", "eyal", "gsa", "roni", "real madrid", "valencia", "bernabeu", "LA LEAGUE", Date.valueOf("2021-12-16")));
        assertEquals("Must get 'Referee not in system'", "Referee not in system", ua.assginGames("tair", "eyal", "shahar", "gsa", "real madrid", "valencia", "bernabeu", "LA LEAGUE", Date.valueOf("2021-12-16")));
        assertEquals("Must get 'Game not in system'", "Game not in system", ua.assginGames("tair", "eyal", "shahar", "roni", "macabi haifa", "valencia", "bernabeu", "LA LEAGUE", Date.valueOf("2021-12-16")));
        assertEquals("Must get 'Game not in system'", "Game not in system", ua.assginGames("tair", "eyal", "shahar", "roni", "real madrid", "hapoel beer sheva", "bernabeu", "LA LEAGUE", Date.valueOf("2021-12-16")));
        assertEquals("Must get 'Host stadium is already taken'", "Host stadium is already taken", ua.assginGames("tair", "eyal", "shahar", "roni", "real madrid", "barcelona", "bernabeu", "LA LEAGUE", Date.valueOf("2021-12-20")));
        assertEquals("Must get 'Team is already playing at this date'", "Team is already playing at this date", ua.assginGames("tair", "eyal", "shahar", "roni", "real madrid", "atletico madrid", "bernabeu", "LA LEAGUE", Date.valueOf("2021-12-20")));
        assertEquals("Must get 'Team is already playing at this date'", "Team is already playing at this date", ua.assginGames("tair", "eyal", "shahar", "roni", "atletico madrid", "barcelona", "san pihoan", "LA LEAGUE", Date.valueOf("2021-12-20")));
        assertEquals("Must get 'Team can't play home game'", "Team can't play home game", ua.assginGames("tair", "eyal", "shahar", "roni", "sevillia", "atletico madrid", "pihoan", "LA LEAGUE", Date.valueOf("2021-12-29")));
        assertEquals("Must get 'Team can't play away game'", "Team can't play away game", ua.assginGames("tair", "eyal", "shahar", "roni", "atletico madrid", "barcelona", "metrapolitano", "LA LEAGUE", Date.valueOf("2021-12-4")));
    }
}