import java.util.ArrayList;
import java.util.Scanner;

public class Game
{
    private ArrayList<Team> teams;
    private ArrayList<String> results;
    private Menu menu;
    private Tools tools;
    private int preliminaryStageFlag;
    private int finalStageFlag;

    public Game()
    {
        teams = new ArrayList<>();
        results = new ArrayList<>();
        results.add("Final stage is not played.");
        menu = new Menu();
        tools = new Tools();
        //初赛和预赛的标识符，0为未比赛，1为已比赛
        preliminaryStageFlag = 0;
        finalStageFlag = 0;
    }

    public void startGame()
    {
        //准备读取用户输入值
        Scanner console = new Scanner(System.in);
        //初始化用户输入值的变量
        String input = "";
        teams = tools.readFile();

        //读取team.txt,如果读取成功，则进入下一环节显示菜单；若不成功，则退出程序
        if (teams.size() != 0)
        {
            //输入球员信息
            inputPlayer();
            //toUpperCase()用来将输入值变为大写，避免因为用户输入小写而无法识别
            while (!input.toUpperCase().equals("X"))
            {
                //game开始，显示menu
                menu.display();
                //读取用户输入值并赋值到变量中
                input = console.nextLine();
                switch (input.toUpperCase())
                {
                    case "A":
                        playPreliminaryStage();
                        break;
                    case "B":
                        playFinalStage();
                        break;
                    case "C":
                        menu.displayTeamHeader();
                        readTeam();
                        break;
                    case "D":
                        readPlayers();
                        break;
                    case "E":
                        displayResult();
                        break;
                    case "X":
                        writeResultsToFile();
                        menu.finishInfo();
                        break;
                    default:
                        menu.inputErrorInfo();
                        break;
                }
            }
        }
    }

    public void inputPlayer()
    {
        //因为teams已经在列表中存在，所以逐一读取team
        for (int i = 0; i < teams.size(); i ++)
        {
            //获取当前team的名字
            String teamName = teams.get(i).getName();
            //因为有两个球员，所以需要一个一个写入
            for (int j = 1; j < 3; j ++)
            {
                //显示信息“'某'队的球员'几号'”
                menu.typePlayerInfo(teamName, j);
                //球员姓名初始化
                String playerName = tools.typeIn();
                //放入while循环判断，如果不符合要求则不跳出循环
                //球员名字只能用字母；球员姓名的规则首尾-不能有；中间只能有一个-；同队不能重名；至少两个字母；需要最大值
                while (!tools.allCharacter(playerName) || !tools.noStartAndEndHyphen(playerName) || !tools.oneHyphen(playerName)
                        || !tools.noDuplicatedName(teams.get(i).getPlayer1().getName(),playerName) || !tools.noLessTwoLength(playerName))
                {
                    //要求重新输入“'某'队的球员'几号'”的姓名
                    menu.typePlayerInfo(teamName, j);
                    //再次给姓名赋值
                    playerName = tools.typeIn();
                }
                switch (j)
                {
                    case 1:
                        teams.get(i).getPlayer1().setName(playerName);
                        break;
                    case 2:
                        teams.get(i).getPlayer2().setName(playerName);
                        break;
                }
            }
        }
    }

    public void playPreliminaryStage()
    {
        //若预赛标识符为0，则可以进行预赛
        if (preliminaryStageFlag == 0)
        {
            //开始预赛，读取每一个球队
            for (int i = 0; i < teams.size(); i++)
            {
                //这样的取值方法下，teamA的排序永远比teamB靠前。重要！！！
                Team teamA = teams.get(i);
                //获取teamA之后的球队
                for (int j = i + 1; j < teams.size(); j++)
                {
                    Team teamB = teams.get(j);
                    String[] info = playGame(teamA, teamB);
                    menu.displayPreliminaryResult(teamA.getName(), teamB.getName(), info);
                }
            }
            //预赛标识符置1，代表预赛踢完了
            preliminaryStageFlag = 1;
            //踢完预赛后，teams列表排序，根据points，goals从高到低排序
            teams = tools.sortTeams(teams);
        }
        else
            menu.preliminaryPlayedError();
    }

    public void playFinalStage()
    {
        if (preliminaryStageFlag == 1 && finalStageFlag == 0)
        {
            Team teamA = teams.get(0);
            Team teamB = teams.get(1);
            String[] finalInfo = playGame(teamA,teamB);
            menu.displayPreliminaryResult(teamA.getName(), teamB.getName(), finalInfo);
            int goalA = Integer.parseInt(finalInfo[0]);
            int goalB = Integer.parseInt(finalInfo[1]);
            if (goalA == goalB)
            {
                menu.displayPenaltyShootOutInfo();
                int[] finalGoals = playPenaltyShootOut();
                int penaltyShootOutGoalA = finalGoals[0];
                int penaltyShootOutGoalB = finalGoals[1];
                playOneRound(teamA,teamB,penaltyShootOutGoalA,penaltyShootOutGoalB,0,0);
                goalA = goalA + penaltyShootOutGoalA;
                goalB = goalB + penaltyShootOutGoalB;
                menu.displayPenaltyShootOutResult(teamA,teamB,goalA,goalB,penaltyShootOutGoalA,penaltyShootOutGoalB);
            }
            teams = tools.sortTeams(teams);
            finalStageFlag = 1;
        }
        else
        {
            if (preliminaryStageFlag == 0)
                menu.noPreliminaryError();
            if (finalStageFlag == 1)
                menu.finalStagePlayedError();
        }
    }

    public int[] playPenaltyShootOut()
    {
        int penaltyShootOutGoalA = 0;
        int penaltyShootOutGoalB = 0;
        //先进行5轮点球大战
        for (int i = 0; i < 6; i ++)
        {
            penaltyShootOutGoalA = penaltyShootOutGoalA + tools.randomNumber(2);
            penaltyShootOutGoalB = penaltyShootOutGoalB + tools.randomNumber(2);
        }
        //如果5轮点球大战后还是打平，则进行一轮点球大战，直到有一方超过另一方
        while (penaltyShootOutGoalA == penaltyShootOutGoalB)
        {
            penaltyShootOutGoalA = penaltyShootOutGoalA + tools.randomNumber(2);
            penaltyShootOutGoalB = penaltyShootOutGoalB + tools.randomNumber(2);
        }
        int[] penaltyShootOutGoals = {penaltyShootOutGoalA, penaltyShootOutGoalB};
        return penaltyShootOutGoals;
    }

    public void writeResultsToFile()
    {
        displayResult();
        tools.writeFile(results);
    }

    public void displayResult()
    {
        if (finalStageFlag != 0)
        {
            Team championTeam = teams.get(0);
            String teamName = championTeam.getName();
            results.clear();
            results.add("Football World Cup Winner: " + teamName);
            ArrayList<String> awardedPlayers = getGoldenBootAward();
            for (int i = 0; i < awardedPlayers.size(); i ++)
            {
                String playerInfo = awardedPlayers.get(i);
                String[] str = playerInfo.split(",");
                String playerName = str[0];
                String playerCountry = str[1];
                results.add("Golden Boot Award: " + playerName + " from " + playerCountry);
            }
            ArrayList<Team> awardedTeams = tools.setFairPlayAward(teams);
            for (int i = 0; i < awardedTeams.size(); i ++)
            {
                results.add("Fair Play Award: " + awardedTeams.get(i).getName());
            }
        }
        menu.displayCupResult(results);
    }

    public String[] playGame(Team team1, Team team2)
    {
        int rank1 = team1.getRanking();
        int rank2 = team2.getRanking();
        int goal1 = 0;
        int goal2 = 0;
        String card1 = teamCard();
        String card2 = teamCard();
        int fairPlayScore1 = getFairPlayScore(card1);
        int fairPlayScore2 = getFairPlayScore(card2);

        //名次高的一队的得分。得分范围是0至5 + 0~2的随机数
        goal1 = tools.randomNumber(6 + tools.randomNumber(3));
        //名次低的一队的得分。得分范围是0至5 - 名次之差 + 0~2的随机数
        goal2 = tools.randomNumber(6 - (rank2 - rank1) + tools.randomNumber(3));
        //设定每一轮的比分
        playOneRound(team1,team2,goal1,goal2,fairPlayScore1,fairPlayScore2);
        //goal+""的作用是把int转为String，否则无法存入到String[]数组中
        String[] information = {goal1+"", goal2+"", card1, card2};
        setPlayerGoal(team1,team2,goal1,goal2);
        return information;
    }

    public void setPlayerGoal(Team team1, Team team2, int goal1, int goal2)
    {
        //生成0~goal1的随机数，给team1的player1
        int t1Player1Goal = tools.randomNumber(goal1 + 1);
        int t1player2Goal = goal1 - t1Player1Goal;
        team1.getPlayer1().setGoals(team1.getPlayer1().getGoals() + t1Player1Goal);
        team1.getPlayer2().setGoals(team1.getPlayer2().getGoals() + t1player2Goal);

        int t2Player1Goal = tools.randomNumber(goal2 + 1);
        int t2player2Goal = goal2 - t2Player1Goal;
        team2.getPlayer1().setGoals(team2.getPlayer1().getGoals() + t2Player1Goal);
        team2.getPlayer2().setGoals(team2.getPlayer2().getGoals() + t2player2Goal);
    }

    public void playOneRound(Team team1, Team team2, int goal1, int goal2, int fairPlayScore1, int fairPlayScore2)
    {
        if (goal1 > goal2)
        {
            teamInfoUpdate(team1, 1 , 0, 0 , goal1, 3, fairPlayScore1);
            teamInfoUpdate(team2, 0 , 1, 0 , goal2, 0, fairPlayScore2);
        }
        else
        {
            if (goal1 < goal2)
            {
                teamInfoUpdate(team1, 0 , 1, 0 , goal1, 0, fairPlayScore1);
                teamInfoUpdate(team2, 1 , 0, 0 , goal2, 3, fairPlayScore2);
            }
            else
            {
                teamInfoUpdate(team1, 0 , 0, 1 , goal1, 1, fairPlayScore1);
                teamInfoUpdate(team2, 0 , 0, 1 , goal2, 1, fairPlayScore2);
            }
        }
    }

    public int getFairPlayScore(String card)
    {
        int fairPlayScore = 0;
        switch (card)
        {
            case "yellow":
                fairPlayScore = 1;
                break;
            case "red":
                fairPlayScore = 2;
                break;
            default:
                fairPlayScore = 0;
                break;
        }
        return fairPlayScore;
    }

    public ArrayList<String> getGoldenBootAward()
    {
        ArrayList<String> awardedPlayers = new ArrayList<>();
        awardedPlayers = tools.setGoldenBootAward(teams);
        return awardedPlayers;
    }

    public void readTeam()
    {
        //逐一读取teams列表中的所有team
        for (int i = 0; i < teams.size(); i++)
        {
            //读取team name
            String teamName = teams.get(i).getName();
            //读取team rank
            int teamRank = teams.get(i).getRanking();
            //读取team played
            int teamPlayed = teams.get(i).getPlayed();
            //读取team wins
            int teamWins = teams.get(i).getWins();
            //读取team Losts
            int teamLosts = teams.get(i).getLosts();
            //读取team Draws
            int teamDrawns = teams.get(i).getDrawns();
            //读取team Goals
            int teamGoals = teams.get(i).getGoals();
            //读取team Points
            int teamPoints = teams.get(i).getPoints();
            //读取team FairPlayScore
            int teamFairPlayScore = teams.get(i).getFairPlayScore();
            //显示team
            menu.displayTeamInfo(teamName,teamRank,teamPlayed,teamWins,teamLosts,teamDrawns,teamGoals,teamPoints,teamFairPlayScore);
        }
    }

    public String teamCard()
    {
        String card = "no";
        //在100个数中生成一个随机数，用来判定是否拿牌
        int cardRate = tools.randomNumber(100);
        //如果是80~99这20个数，则拿黄牌
        if (cardRate > 79 && cardRate < 100)
        {
            card = "yellow";
        }
        else
        {
            //如果是60~64这5个数，则拿红牌（红牌的概率是黄牌的四分之一）
            if (cardRate > 59 && cardRate < 65)
            {
                card = "red";
            }
        }
        return card;
    }

    public void readPlayers()
    {
        //逐一读取teams列表中，每个team的两名球员
        for (int i = 0; i < teams.size(); i++)
        {
            String teamName = teams.get(i).getName();
            for (int j = 1; j < 3; j++)
            {
                switch (j)
                {
                    case 1:
                        String player1Name = teams.get(i).getPlayer1().getName();
                        int player1Goal = teams.get(i).getPlayer1().getGoals();
                        menu.displayPlayers(teamName,player1Name,player1Goal);
                        break;
                    case 2:
                        String player2Name = teams.get(i).getPlayer2().getName();
                        int player2Goal = teams.get(i).getPlayer2().getGoals();
                        menu.displayPlayers(teamName,player2Name,player2Goal);
                }
            }
        }
    }

    public void teamInfoUpdate(Team team, int wins, int losts, int drawns,
                               int goals, int points, int fairPlayScore)
    {
        team.setPlayed(team.getPlayed() + 1);
        team.setWins(team.getWins() + wins);
        team.setLosts(team.getLosts() + losts);
        team.setDrawns(team.getDrawns() + drawns);
        team.setGoals(team.getGoals() + goals);
        team.setPoints(team.getPoints() + points);
        team.setFairPlayScore(team.getFairPlayScore() + fairPlayScore);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
    
}
