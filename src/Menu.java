import java.util.ArrayList;

public class Menu {

    public Menu()
    {

    }

    //显示菜单
    public void display()
    {
        System.out.println("========================================");
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

    public void writeFileErrorInfo()
    {
        System.out.println("Write text file unsuccessfully.");
    }

    public void writeFileSuccessful() {
        System.out.println("Write file statistics.txt successfully!");
    }

    public void displayTeamHeader()
    {
        System.out.printf("%20s%8s%6s%7s%8s%7s%8s%17s\n","Rank","Played","Wins","Losts","Drawns","Goals","Points","Fair Play Sores");
    }

    public void displayTeamInfo(String teamName, int teamRank, int teamPlayed, int teamWins,
                            int teamLosts,int teamDrawns,int teamGoals, int teamPoints,int teamFairPlayScore)
    {
        int nameSpace = 0;
        if (teamName.length() > 5)
            nameSpace = 14 - teamName.length() + 5;
        else
        {
            if (teamName.length() < 5)
                nameSpace = 14 + 5 - teamName.length();
            else
                nameSpace = 14;
        }
        System.out.printf("%s%" + nameSpace + "s%6s%8s%6s%7s%8s%7s%11s\n",teamName,teamRank,teamPlayed,teamWins,teamLosts,teamDrawns,teamGoals,teamPoints,teamFairPlayScore);
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

    public void displayPreliminaryResult(String name, String name1, String[] info)
    {
        String goalA = info[0];
        String goalB = info[1];
        String cardA = info[2];
        String cardB = info[3];
        System.out.println("----------------------------------------");
        System.out.println("Game result: " + name + " " + goalA +  " vs. " + name1 + " " + goalB);
        System.out.println("Cards awarded: " + name + " - " + cardA + " card." );
        System.out.println("Cards awarded: " + name1 + " - " + cardB + " card." );
    }

    public void noPreliminaryError()
    {
        System.out.println("The preliminary stage is not played.");
    }

    public void displayGoldenBootPlayersInfo(String playerName, String playerCountry)
    {
        System.out.println("Golden Boot Award: " + playerName + " from " + playerCountry);
    }

    public void displayPenaltyShootOutInfo()
    {
        System.out.println("Drawn! Now Starting Penalty Shootout!");
    }

    public void displayPenaltyShootOutResult(Team teamA, Team teamB, int goalA, int goalB, int penaltyShootOutGoalA, int penaltyShootOutGoalB)
    {
        System.out.println("Penalty Shootout Result is " + teamA.getName() + " " + penaltyShootOutGoalA
                + " vs. " + teamB.getName() + " " + penaltyShootOutGoalB);
        System.out.println("Final Game Result after Penalty Shootout: " + teamA.getName() + " "
                + goalA + " vs. " + teamB.getName() + " " + goalB);
    }

    public void preliminaryPlayedError()
    {
        System.out.println("Preliminary stage is played!");
    }

    public void finalStagePlayedError()
    {
        System.out.println("Final stage is played!");
    }



    public void displayCupResult(ArrayList<String> results)
    {
        for (int i = 0; i < results.size(); i ++)
        {
            System.out.println(results.get(i));
        }
    }

}

