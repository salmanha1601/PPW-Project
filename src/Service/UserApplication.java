package Service;

import Domain.*;

import java.sql.Date;
import java.sql.SQLException;

public class UserApplication {
    UserController uc=new UserController();
    public String Login(String email, String password) {
        return uc.login(email, password);
    }

    public String registerReferee(String refereeName, String league) {
        return uc.registerReferee(refereeName, league);
    }

    public String assginGames(String refereeName1, String refereeName2, String refereeName3, String refereeName4, String team1, String team2, String stadium, String league, Date date) {
        return uc.assginGames(refereeName1, refereeName2, refereeName3, refereeName4, team1, team2, stadium,league, date);
    }
}
