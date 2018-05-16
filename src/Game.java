import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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

        //读取team.txt,如果读取成功，则进入下一环节显示菜单；若不成功，则退出程序
        if (readFile())
        {
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

    public boolean readFile()
    {
        boolean readSuccessful = false;
        try
        {
            //定义文档名称
            String filename = "team.txt";
            //新建阅读器
            FileReader inputFile = new FileReader(filename);
            //新建解析器
            Scanner parser = new Scanner(inputFile);
            //逐行解析文档
            while(parser.hasNextLine())
            {
                //读取一行
                String line = parser.nextLine();
                //识别每一行的逗号，将前后的信息分别取出，存入数组中
                String[] detail = line.split(",");
                //新建String构造器
                StringBuilder subjectsToString = new StringBuilder();
                //新建Team对象，存入数组中的信息：第0位的是team name，第1位的是ranking
                Team newTeam = new Team(detail[0], Integer.parseInt(detail[1]));
                //将这支team存入team list中
                teams.add(newTeam);
                readSuccessful = true;
            }
            inputFile.close();

            if (teams.size() == 0)
                menu.emptyFileInfo();
            else
                //读取成功
                menu.readSuccessful();
        }
        catch (IOException e)//获取输入输出（读取文件）时的系统错误
        {
            menu.fileNotFoundInfo();
        }
        catch (RuntimeException e)//获取运行时的系统错误
        {
            menu.fileContentErrorInfo();
        }
        return readSuccessful;
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
                //球员姓名的规则首尾-不能有；中间只能有一个-；同队不能重名；至少两个字母；需要最大值
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

    public void readTeam()
    {
        //逐一读取teams列表中的所有team
        for (int i = 0; i < teams.size(); i++)
        {
            //读取team name
            String teamName = teams.get(i).getName();
            //读取team rank
            int teamRank = teams.get(i).getRanking();
            //显示team
            menu.displayTeam(teamName,teamRank);
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

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }

    
}
