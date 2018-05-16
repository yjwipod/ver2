import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game
{
    private ArrayList<Team> teams;
    private Menu menu;

    public Game() {
        teams = new ArrayList<>();
        menu = new Menu();
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
            //toUpperCase()用来将输入值变为大写，避免因为用户输入小写而无法识别
            while (!input.toUpperCase().equals("X")) {
                //game开始，显示menu
                menu.display();
                //读取用户输入值并赋值到变量中
                input = console.nextLine();
                switch (input.toUpperCase()) {
                    case "A":
                        break;
                    case "B":
                        break;
                    case "C":
                        readTeam();
                        break;
                    case "D":
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

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }

    
}
