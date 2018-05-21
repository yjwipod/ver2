public class Menu {

    public Menu()
    {

    }

    //显示菜单
    public void display()
    {
        System.out.println("A. Play Preliminary Stage");
        System.out.println("B. Play Final");
        System.out.println("C. Display Teams");
        System.out.println("D. Display Players");
        System.out.println("E. Display Cup Result");
        System.out.println("X. Close");
        System.out.print("Choose an option: " );
    }

    public void inputErrorInfo()
    {
        System.out.println("Please type in A, B, C, D, E or X for option.");
    }

    public void finishInfo()
    {
        System.out.println("You have exited the game.");
    }

    public void readSuccessful()
    {
        System.out.println("Team text is loaded...");
    }


    public void emptyFileInfo()
    {
        System.out.println("Empty File");
    }

    public void fileNotFoundInfo()
    {
        System.out.println("File not found.");
    }

    public void fileContentErrorInfo()
    {
        System.out.println("File contents error!");
    }

    public void displayTeam(String teamName, int teamRank, int teamPlayed, int teamWins,
                            int teamLosts,int teamDrawns,int teamGoals, int teamPoints,int teamFairPlayScore)
    {
        System.out.println(teamName + ", Rank: " + teamRank + ", Played: " + teamPlayed + ", Wins:" + teamWins + ", Losts: "
                + teamLosts + ", Drawns: " + teamDrawns + ", Goals: " + teamGoals + ", Points:" + teamPoints + ", Fair Play Score: " + teamFairPlayScore);
    }

    public void typePlayerInfo(String teamName, int playerNumber)
    {
        System.out.println("Please input the name of player" + playerNumber + " in team " + teamName + ":");
    }

    public void typePlayerCharError()
    {
        System.out.println("Please enter all letters in the player's name.");
    }

    public void displayPlayers(String teamName, String playerName, int playerGoal)
    {
        System.out.println(playerName + " (" + teamName + ") - " + playerGoal );
    }

    public void multiHyphenError()
    {
        System.out.println("Only one hyphen allowed in player's name.");
    }

    public void startAndEndHyphenError()
    {
        System.out.println("The player's name is not allowed to start or end with \"-\".");
    }

    public void duplicatedNameError()
    {
        System.out.println("The player's name can not be same as the other player's.");
    }

    public void nameLengthError()
    {
        System.out.println("Player's name should have at least 2 characters and less then 20 characters.");
    }

    public void displayPreliminaryResult(String name, String name1, Integer[] goals)
    {
        int goalA = goals[0];
        int goalB = goals[1];
        System.out.println("Game result: " + name + " " + goalA +  " vs. " + name1 + " " + goalB);
    }
}
