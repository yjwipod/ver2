public class Team 
{
    private String name;
    private int ranking;
    private Player player1;
    private Player player2;

    public Team(String inputName, int inputRanking, Player inputPlayer1, Player inputPlayer2)
    {
        name = inputName;
        ranking = inputRanking;
        player1 = inputPlayer1;
        player2 = inputPlayer2;
    }

    public Team(String inputName, int inputRanking)
    {
        name = inputName;
        ranking = inputRanking;
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
}
