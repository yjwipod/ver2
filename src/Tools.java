import java.util.Scanner;

public class Tools {

    private Menu menu;
    public Tools()
    {
        menu = new Menu();
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
