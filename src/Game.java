import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Game
{
    private ArrayList<Team> teams;
    private Menu menu;
    private Tools tools;

    public Game() {
        teams = new ArrayList<>();
        menu = new Menu();
        tools = new Tools();
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
                        break;
                    case "C":
                        readTeam();
                        break;
                    case "D":
                        readPlayers();
                        break;
                    case "E":
                        break;
                    case "X":
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
        //开始预赛，读取每一个球队
        for (int i = 0; i < teams.size(); i++)
        {
            Team teamA = teams.get(i);
            //获取teamA之后的球队
            for (int j = i + 1; j < teams.size(); j++)
            {
                Team teamB = teams.get(j);
                Integer[] goals = playGame(teamA, teamB);
                menu.displayPreliminaryResult(teamA.getName(), teamB.getName(), goals);
            }
        }
    }

    public void displayResult()
    {

    }

    public Integer[] playGame(Team team1, Team team2)
    {
        int rank1 = team1.getRanking();
        int rank2 = team2.getRanking();
        int goal1 = 0;
        int goal2 = 0;
        if (rank1 < rank2)
        {
            //名次高的一队的得分。得分范围是0至5 + 0~2的随机数
            goal1 = tools.randomNumber(6 + tools.randomNumber(3));
            //名次低的一队的得分。得分范围是0至5 - 名次之差 + 0~2的随机数
            goal2 = tools.randomNumber(6 - (rank2 - rank1) + tools.randomNumber(3));
            if (goal1 > goal2)
            {
                teamInfoUpdate(team1, 1 , 0, 0 , goal1, 0, 0);
                teamInfoUpdate(team2, 0 , 1, 0 , goal2, 0, 0);
            }
            else
            {
                if (goal1 < goal2)
                {
                    teamInfoUpdate(team1, 0 , 1, 0 , goal1, 0, 0);
                    teamInfoUpdate(team2, 1 , 0, 0 , goal2, 0, 0);
                }
                else
                {
                    teamInfoUpdate(team1, 0 , 0, 1 , goal1, 0, 0);
                    teamInfoUpdate(team2, 0 , 0, 1 , goal2, 0, 0);
                }
            }

        }
        else
        {
            //名次高的一队的得分。得分范围是0至5 + 0~2的随机数
            goal2 = tools.randomNumber(6 + tools.randomNumber(3));
            //名次低的一队的得分。得分范围是0至5 - 名次之差 + 0~2的随机数
            goal1 = tools.randomNumber(6 - (rank1 - rank2) + tools.randomNumber(3));
            if (goal1 > goal2)
            {
                teamInfoUpdate(team1, 1 , 0, 0 , goal1, 0, 0);
                teamInfoUpdate(team2, 0 , 1, 0 , goal2, 0, 0);
            }
            else
            {
                if (goal1 < goal2)
                {
                    teamInfoUpdate(team1, 0 , 1, 0 , goal1, 0, 0);
                    teamInfoUpdate(team2, 1 , 0, 0 , goal2, 0, 0);
                }
                else
                {
                    teamInfoUpdate(team1, 0 , 0, 1 , goal1, 0, 0);
                    teamInfoUpdate(team2, 0 , 0, 1 , goal2, 0, 0);
                }
            }
        }
        Integer[] goals = {goal1, goal2};

        return goals;
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
            menu.displayTeam(teamName,teamRank,teamPlayed,teamWins,teamLosts,teamDrawns,teamGoals,teamPoints,teamFairPlayScore);
        }
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
