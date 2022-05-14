package Domain;

import DataAccess.*;

import java.sql.Date;
import java.sql.SQLException;

public class UserController {
    Dao ud;
    Dao gd;
    Dao ld;
    Dao td;
    Dao rd;
    public UserController() {
        ud = UserDaoSQL.getInstance();
        gd = GameDaoSQL.getInstance();
        ld = LeagueDaoSQL.getInstance();
        td = TeamDaoSQL.getInstance();
        rd = RefereeDaoSQL.getInstance();
    }

    public void login(String email, String password) {
        try {
            boolean findUser = ud.findUserByEmail(email, password);
            if (findUser) {
                System.out.println("You have logged in successfully");
            }
            else {
                System.out.println("This user is not in the system");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerReferee(String refereeName, String leagueName) {
        try {
            boolean findReferee = ud.findUserByName(refereeName, "referee");
            if (!findReferee) {
                System.out.println("Referee not in system");
            }
            boolean findLeague = ld.findLeague(leagueName);
            if (!findLeague) {
                System.out.println("League not in system");
            }
            int findLeagueReferees = ld.findLeagueReferees(leagueName);
            if (!(findLeagueReferees < 12)) {
                System.out.println("The capacity of referees is full");
            }
            boolean refereeAdded = rd.addReferee(leagueName, refereeName);
            if (refereeAdded) {
                System.out.println("You have registered referee successfully");
            }
            else {
                System.out.println("The league is full");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void assginGames(String refereeName1, String refereeName2, String refereeName3, String refereeName4, String team1, String team2, String stadium, String leagueName, Date date) {
        try {
            boolean findLeague = ld.findLeague(leagueName);
            if (!findLeague) {
                System.out.println("League not in system");
            }
            boolean findReferee1 = ud.findUserByName(refereeName1, "referee");
            if (!findReferee1) {
                System.out.println("Referee not in system");
            }
            boolean findReferee2 = ud.findUserByName(refereeName2, "referee");
            if (!findReferee2) {
                System.out.println("Referee not in system");
            }
            boolean findReferee3 = ud.findUserByName(refereeName3, "referee");
            if (!findReferee3) {
                System.out.println("Referee not in system");
            }
            boolean findReferee4 = ud.findUserByName(refereeName4, "referee");
            if (!findReferee4) {
                System.out.println("Referee not in system");
            }
            boolean findGame = gd.findGame(team1, team2, leagueName);
            if (!findGame) {
                System.out.println("Game not in system");
            }
            boolean findGameStadium = gd.findGameStadium(team1, team2, stadium, leagueName);
            if (!findGameStadium) {
                System.out.println("Host stadium is already taken");
            }
            boolean dateGameFree1 = gd.dateGameFree(team1, date, leagueName);
            if (!dateGameFree1) {
                System.out.println("Team is already playing at this date");
            }
            boolean dateGameFree2 = gd.dateGameFree(team2, date, leagueName);
            if (!dateGameFree2) {
                System.out.println("Team is already playing at this date");
            }
            boolean hostCheck = td.hostCheck(team1);
            if (!hostCheck) {
                System.out.println("Team can't play home game");
            }
            boolean awayCheck = td.awayCheck(team2);
            if (!awayCheck) {
                System.out.println("Team can't play away game");
            }
            boolean gameAssigned = gd.assignGame(refereeName1, refereeName2, refereeName3, refereeName4, team1, team2, stadium, leagueName, date);
            if (gameAssigned) {
                System.out.println("Game assigned successfully");
            }
            else {
                System.out.println("Can't assign game");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
