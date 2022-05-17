package DataAccess;

public class TeamDaoSQL implements Dao{
    private static final TeamDaoSQL instance = new TeamDaoSQL();

    //private constructor to avoid client applications to use constructor
    public static TeamDaoSQL getInstance(){
        return instance;
    }

    public int hostCheck(String team1) {
        return 0;
    }

    public int awayCheck(String team2) {
        return 0;
    }
}
