public class Player
{

    private String name;
    private int goals;

    public Player()
    {
        name = "";
        goals = 0;
    }

    public Player(String inputName, int inputGoals)
    {
        name = inputName;
        goals = inputGoals;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String inputName)
    {
        name = inputName;
    }

    public int getGoals() 
    {
        return goals;
    }

    public void setGoals(int inputGoals)
    {
        goals = inputGoals;
    }
}
