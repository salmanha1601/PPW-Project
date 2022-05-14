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

    public String login(String email, String password) {
        try {
            boolean findUser = ud.findUserByEmail(email, password);
            if (findUser) {
                return "You have logged in successfully";
            }
            else {
                return "This user is not in the system";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String registerReferee(String refereeName, String leagueName) {
        try {
            boolean findReferee = ud.findUserByName(refereeName, "referee");
            if (!findReferee) {
                return "Referee not in system";
            }
            boolean findLeague = ld.findLeague(leagueName);
            if (!findLeague) {
                return "League not in system";
            }
//            number of referee in this league
            int findLeagueReferees = ld.findLeagueReferees(leagueName);
            if (!(findLeagueReferees < 12)) {
                return "The capacity of referees is full";
            }
//            add on referee to league
            boolean refereeAdded = rd.addReferee(leagueName, refereeName);
            if (refereeAdded) {
                return "You have registered referee successfully";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String assginGames(String refereeName1, String refereeName2, String refereeName3, String refereeName4, String team1, String team2, String stadium, String leagueName, Date date) {
        try {
            boolean findLeague = ld.findLeague(leagueName);
            if (!findLeague) {
                return "League not in system";
            }
            boolean findReferee1 = ud.findUserByName(refereeName1, "referee");
            if (!findReferee1) {
                return "Referee not in system";
            }
            boolean findReferee2 = ud.findUserByName(refereeName2, "referee");
            if (!findReferee2) {
                return "Referee not in system";
            }
            boolean findReferee3 = ud.findUserByName(refereeName3, "referee");
            if (!findReferee3) {
                return "Referee not in system";
            }
            boolean findReferee4 = ud.findUserByName(refereeName4, "referee");
            if (!findReferee4) {
                return "Referee not in system";
            }
            boolean findGame = gd.findGame(team1, team2, leagueName);
            if (!findGame) {
                return "Game not in system";
            }
            boolean findGameStadium = gd.findGameStadium(team1, team2, stadium, leagueName);
            if (!findGameStadium) {
                return "Host stadium is already taken";
            }
            boolean dateGameFree1 = gd.dateGameFree(team1, date, leagueName);
            if (!dateGameFree1) {
                return "Team is already playing at this date";
            }
            boolean dateGameFree2 = gd.dateGameFree(team2, date, leagueName);
            if (!dateGameFree2) {
                return "Team is already playing at this date";
            }
//            number of host game
            int hostCheck = td.hostCheck(team1);
            if (!(hostCheck < 15)) {
                return "Team can't play home game";
            }
//            number of away game
            int awayCheck = td.awayCheck(team2);
            if (!(awayCheck < 15)) {
                return "Team can't play away game";
            }
            boolean gameAssigned = gd.assignGame(refereeName1, refereeName2, refereeName3, refereeName4, team1, team2, stadium, leagueName, date);
            if (gameAssigned) {
                return "Game assigned successfully";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
