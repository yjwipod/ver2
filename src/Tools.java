import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Tools {

    private Menu menu;

    public Tools()
    {
        menu = new Menu();
    }

    public int randomNumber(int amount)
    {
        int randomNumber = -1;
        Random builder = new Random();
        //生成一个0~amount - 1的随机数。如amount = 6，则生成0~5
        randomNumber = builder.nextInt(amount);
        return randomNumber;
    }

    public int newRandom(int min, int max)
    {
        int random = 0;
        Random builder = new Random();
        //min ~ max + min
        random = builder.nextInt(max + 1) + min;
        return random;
    }

    public ArrayList readFile()
    {
        ArrayList teams = new ArrayList<>();
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
                //新建Team对象，存入数组中的信息：第0位的是team name，第1位的是ranking
                Team newTeam = new Team(detail[0], Integer.parseInt(detail[1]));
                //将这支team存入team list中
                teams.add(newTeam);
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
        return teams;
    }

    public void writeFile(ArrayList<String> content)
    {
        try
        {
            String fileName = "statistics.txt";
            PrintWriter outPutFile = new PrintWriter(fileName);
            for (int i = 0; i < content.size(); i ++)
            {
                outPutFile.println(content.get(i));
            }
            outPutFile.close();
            menu.writeFileSuccessful();
        }
        catch (IOException e)
        {
            menu.writeFileErrorInfo();
        }
    }

    public boolean writePlayer(ArrayList<Player> players, String textName)
    {
        boolean success = false;
        try
        {
            PrintWriter outPutFile = new PrintWriter(textName);
            for (int i = 0; i < players.size(); i ++)
            {
                String name = players.get(i).getName();
                int goals = players.get(i).getGoals();
                String content = name + "," + goals;
                outPutFile.println(content);
            }
            outPutFile.close();
        }
        catch (IOException e)
        {

        }
        return success;
    }

    public ArrayList<Team> sortTeams(ArrayList<Team> teams)
    {
        ArrayList<Team> sortedTeams = new ArrayList<>();
        int maxPoints = 0;
        int ranking = 1;
        Team topRankTeam = new Team();

        while (teams.size() != 0)
        {
            maxPoints = teams.get(0).getPoints();
            topRankTeam = teams.get(0);
            for (int j = 1; j < teams.size(); j ++)
            {
                if (maxPoints < teams.get(j).getPoints())
                {
                    maxPoints = teams.get(j).getPoints();
                    topRankTeam = teams.get(j);
                }
                else
                {
                    //积分相同的情况下，比较goals
                    if (maxPoints == teams.get(j).getPoints())
                    {
                        int maxGoals = topRankTeam.getGoals();
                        int teamJGoals = teams.get(j).getGoals();
                        if (maxGoals < teamJGoals)
                            topRankTeam = teams.get(j);
                        else
                        {
                            //goals也相同的情况下，随机
                            if (maxGoals == teamJGoals)
                            {
                                //取一个值，0或1
                                int random = randomNumber(2);
                                if (random == 0)
                                    topRankTeam = teams.get(j);
                            }
                        }
                    }
                }
            }
            topRankTeam.setRanking(ranking);
            sortedTeams.add(topRankTeam);
            teams.remove(topRankTeam);
            ranking ++;
        }
        return sortedTeams;
    }

    public ArrayList<String> setGoldenBootAward(ArrayList<Team> teams)
    {
        ArrayList<String> goldenBootPlayers = new ArrayList<>();
        int maxGoal = 0;
        int ranking = 1;
        Player goldenBootPlayer = new Player();

        for (int i = 0; i < teams.size(); i ++)
        {
            ArrayList<Player> players = new ArrayList<>();
            players.add(teams.get(i).getPlayer1());
            players.add(teams.get(i).getPlayer2());
            String teamName = teams.get(i).getName();

            for (int j = 0; j < players.size(); j ++)
            {
                //若maxGoal小于当前读取的球员Goal，则更新maxGoal，并清空goldenBootPlayers表，将最大goal所对应的球员信息存入其中。
                if (maxGoal < players.get(j).getGoals())
                {
                    maxGoal = players.get(j).getGoals();
                    //将列表清空
                    goldenBootPlayers.clear();
                    goldenBootPlayers.add(players.get(j).getName() + "," + teamName);
                }
                else
                {
                    //若maxGoal与当前读取的球员Goal相等，则在goldenBootPlayers中加入（append），因为Golden Boot Awards允许多个球员获得。
                    if (maxGoal == players.get(j).getGoals())
                    {
                        goldenBootPlayers.add(players.get(j).getName() + "," + teamName);
                    }
                }
            }
        }

        return goldenBootPlayers;
    }

    public ArrayList<Team> setFairPlayAward(ArrayList<Team> teams)
    {
        ArrayList<Team> awardedTeam = new ArrayList<>();
        //因为每个队伍最多只比赛4轮（3轮预赛+1轮决赛），所以改分数最多为8分（每轮都是红牌，得2分）
        int minFairPlayAward = 8;
        for (int i = 0; i < teams.size(); i ++)
        {
            //如果最小分数大于某一队伍的分数，则更新该最小分数，并刷新获奖队伍列表，将最新的最小分数的队伍加入。
            if (minFairPlayAward > teams.get(i).getFairPlayScore())
            {
                minFairPlayAward = teams.get(i).getFairPlayScore();
                awardedTeam.clear();
                awardedTeam.add(teams.get(i));
            }
            else
            {
                //如果等于，则不清空获奖队伍列表，直接加入
                if (minFairPlayAward == teams.get(i).getFairPlayScore())
                {
                    awardedTeam.add(teams.get(i));
                }
            }
        }
        return awardedTeam;
    }

    public boolean allCharacter(String input)
    {
        boolean valid = false;
        int i = 0;
        for (; i < input.length(); i++)
        {
            //如果某一位不是字母或不是-，则跳出（break）
            if (!Character.isLetter((input.charAt(i))) && !Character.toString(input.charAt(i)).equals("-"))
                break;
        }
        if (i == input.length())
            valid = true;
        else
            menu.typePlayerCharError();
        return valid;
    }

    public boolean oneHyphen(String input)
    {
        boolean valid = false;
        //number用来统计-出现的次数
        int number = 0;
        //index用来标志字符串的位数
        int index = 0;
        //遍历string的每个字符
        for (int i = 0; i < input.length(); i++)
        {
            //返回从index位开始算起，最近的一个“-”的位置，如果没有“-”则为-1
            index = input.indexOf("-", index + 1);
            //如果返回了大于等于0的位置，则number+1，表明发现一个“-”
            if (index >= 0)
                number ++;
            else //如果index == -1，则说明从index位开始算起没有发现“-”，则退出循环
                break;
        }
        //允许没有或只有一个
        if (number == 0 || number == 1)
            valid = true;
        else
            menu.multiHyphenError();
        return valid;
    }

    public boolean noStartAndEndHyphen(String input)
    {
        boolean valid = false;
        if (!input.startsWith("-") && !input.endsWith("-"))
            valid = true;
        else
            menu.startAndEndHyphenError();
        return valid;
    }

    public boolean noDuplicatedName(String name, String input)
    {
        boolean valid = false;
        if (!name.equals(input))
            valid = true;
        else
            menu.duplicatedNameError();
        return valid;

    }

    public boolean noLessTwoLength(String input)
    {
        boolean valid = false;
        if (input.trim().length() >= 2 && input.trim().length() <= 20)
            valid = true;
        else
            menu.nameLengthError();
        return valid;
    }

    public String typeIn()
    {
        Scanner console = new Scanner(System.in);
        String typeIn = console.nextLine();
        return typeIn;
    }
}
