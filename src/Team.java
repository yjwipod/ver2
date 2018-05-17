public class Team 
{
    private String name;
    private int ranking;
    private int played;
    private int wins;
    private int losts;
    private int drawns;
    private int goals;
    private int points;
    private int fairPlayScore;
    private Player player1;
    private Player player2;

    public Team(String inputName, int inputRanking, int inputPlayed, int inputWins, int inputLosts, int inputDrawns,
                int inputGoals, int inputPoints, int inputFairPlayScore, Player inputPlayer1, Player inputPlayer2)
    {
        name = inputName;
        ranking = inputRanking;
        played = inputPlayed;
        wins = inputWins;
        losts = inputLosts;
        drawns = inputDrawns;
        goals = inputGoals;
        points = inputPoints;
        fairPlayScore = inputFairPlayScore;
        player1 = inputPlayer1;
        player2 = inputPlayer2;
    }

    public Team(String inputName, int inputRanking)
    {
        name = inputName;
        ranking = inputRanking;
        played = 0;
        wins = 0;
        losts = 0;
        drawns = 0;
        goals = 0;
        points = 0;
        fairPlayScore = 0;
        player1 = new Player();
        player2 = new Player();
    }



    public String getName()
    {
        return name;
    }

    public void setName(String inputName)
    {
        name = inputName;
    }

    public int getRanking()
    {
        return ranking;
    }

    public void setRanking(int inputRanking)
    {
        ranking = inputRanking;
    }

    public Player getPlayer1()
    {
        return player1;
    }

    public void setPlayer1(Player inputPlayer1)
    {
        player1 = inputPlayer1;
    }

    public Player getPlayer2()
    {
        return player2;
    }

    public void setPlayer2(Player inputPlayer2)
    {
        player2 = inputPlayer2;
    }

    public int getPlayed()
    {
        return played;
    }

    public void setPlayed(int inputPlayed)
    {
        played = inputPlayed;
    }

    public int getWins()
    {
        return wins;
    }

    public void setWins(int inputWins)
    {
        wins = inputWins;
    }

    public int getLosts()
    {
        return losts;
    }

    public void setLosts(int inputLosts)
    {
        losts = inputLosts;
    }

    public int getDrawns()
    {
        return drawns;
    }

    public void setDrawns(int inputDrawns)
    {
        drawns = inputDrawns;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int inputGoals)
    {
        goals = inputGoals;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int inputPoints)
    {
        points = inputPoints;
    }

    public int getFairPlayScore()
    {
        return fairPlayScore;
    }

    public void setFairPlayScore(int inputFairPlayScore)
    {
        fairPlayScore = inputFairPlayScore;
    }
}
