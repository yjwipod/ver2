import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test
{
    private ArrayList<Student> students;
    private int MAXIMUM_NUMBER_OF_TEAMS;
    private ArrayList<Team> newTeam;

    public Test()
    {
        students = new ArrayList<Student>();
    }

    public Team readTeam(ArrayList<Team> teams)
    {
        Team team = new Team();
        team = teams.get(0);
        MAXIMUM_NUMBER_OF_TEAMS = 10;
        newTeam = new ArrayList<>();

        return team;
    }

    public void inputUserName()
    {
        Scanner console = new Scanner(System.in);
        String name = "";
        name = console.nextLine();
    }

    public void getTeamName(ArrayList<Team> teams)
    {
        ArrayList<String> teamNames = new ArrayList<>();
        for (int i = 0; i < teams.size(); i ++)
        {
            String name = "";
            name = teams.get(i).getName();
            teamNames.add(name);
        }
    }

    public void temperatures()
    {
        Scanner console = new Scanner(System.in);
        int temp = console.nextInt();
        int count = 0;
        int countOver = 0;
        int sumTemp = 0;
        double avgTemp = 0;

        while (temp != -99)
        {
            if (temp > 40)
                countOver ++;

            sumTemp = sumTemp + temp;
            count ++;
            temp = console.nextInt();
        }
        if (count != 0)
        {
            avgTemp = sumTemp / count;
            System.out.println("Average temp is " + avgTemp + ". There are " + countOver + "over 40.");
        }

    }

    public String getLongestString(String[] input)
    {
        String longestString = "";
        int length = 0;
        for (int i = 0; i < input.length; i ++)
        {
             if (length < input[i].length())
             {
                 length = input[i].length();
                 longestString = input[i];
             }
        }
        return longestString;
    }

    public boolean loadTeam(String fileName){
        boolean valid = false;
        try
        {
            FileReader inputFile = new FileReader(fileName);
            Scanner parser = new Scanner(inputFile);

            while (parser.hasNextLine() && newTeam.size() <= MAXIMUM_NUMBER_OF_TEAMS)
            {
                String teamName = parser.nextLine();
                String numberOfTeam = parser.nextLine();
                String country = parser.nextLine();
                Team team = new Team(teamName, numberOfTeam, country);
                newTeam.add(team);
            }
            valid = true;
        }catch(FileNotFoundException e)
        {

        }
        return valid;

    }


}
