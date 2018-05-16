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

    public void displayTeam(String teamName, int teamRank)
    {
        System.out.println(teamName + "," + teamRank);
    }
}
