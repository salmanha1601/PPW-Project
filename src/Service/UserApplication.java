package Service;

import Domain.*;

import java.sql.Date;
import java.sql.SQLException;

public class UserApplication {
    UserController uc=new UserController();
    public void Login(String email, String password) {
        uc.login(email, password);
    }

    public void registerReferee(String refereeName, String league) {
        uc.registerReferee(refereeName, league);
    }

    public void assginGames(String refereeName1, String refereeName2, String refereeName3, String refereeName4, String team1, String team2, String stadium, String league, Date date) {
        uc.assginGames(refereeName1, refereeName2, refereeName3, refereeName4, team1, team2, stadium,league, date);
    }
}
