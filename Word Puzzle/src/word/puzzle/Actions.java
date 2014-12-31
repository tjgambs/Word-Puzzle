package word.puzzle;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Actions 
{
    String title;
    ArrayList<String> stringList;
    Letter[][] table;
    int rows;
    int columns;
    public char randomLetter()
    {
        char[] letters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        double randomNumber1 = Math.random() * 26.0;
        int randomNumber2 = (int)randomNumber1;
        return letters[randomNumber2];
    }
    public void collectTitle()
    {
        String title1 = JOptionPane.showInputDialog("What is the title of this Wordsearch? ");
        title = title1.toUpperCase();
    }
    public void collectLetters()
    {
        String numberOf1 = JOptionPane.showInputDialog("How many words will be in the Wordsearch (#'s please)? ");
        int numberOf = Integer.parseInt(numberOf1);
        
        stringList = new ArrayList();
        for(int i = 0; i<numberOf; i++)
        {
            String input = JOptionPane.showInputDialog("Please enter one word then press enter: ");
            stringList.add(input.toUpperCase());
        }
        String rows1 = JOptionPane.showInputDialog("How many rows of letters do you want? ");
        rows = Integer.parseInt(rows1);

        String columns1 = JOptionPane.showInputDialog("How many columns of letters do you want? ");
        columns = Integer.parseInt(columns1);
    }
    public void randomLetterMaker()
    {
        table = new Letter[rows][columns];
        for(int row=0; row < table.length; row++)
        {
            for (int col=0; col < table[row].length; col++)
            {
                table[row][col] = new Letter(randomLetter());
            }
        }
    }
    public Letter[][] replaceHorizontal()
    {
        for(int i = 0; i<stringList.size(); i++)
        {
            double firstPlace2 = Math.random() * (table.length - stringList.get(i).length());
            int r = (int)firstPlace2;
            double firstPlace1 = Math.random() * (table.length - stringList.get(i).length());
            int firstPlace = (int)firstPlace1;
            for(int j = 0; j<stringList.get(i).length(); j++)
            {
                String one = stringList.get(i);          
                table[r][firstPlace+j] = new Letter(one.charAt(j));
            }
        }
        return table;
    }
  public Letter[][] replaceVertical()
    {
        for(int i = 0; i<stringList.size(); i++)
        {
            double firstPlace2 = Math.random() * (table.length - stringList.get(i).length());
            int r = (int)firstPlace2;
            double firstPlace1 = Math.random() * (table.length - stringList.get(i).length());
            int firstPlace = (int)firstPlace1;
            for(int j = 0; j<stringList.get(i).length(); j++)
            {
                String one = stringList.get(i);
                table[firstPlace+j][r] = new Letter(one.charAt(j));
            }
        }
        return table;   
    }
  //create a new array of the same dimensions but fill them all with 0's
  //when a letter is placed change that 0 to a 1
  //when the program runs make sure that the places are all 0 or if it cross a 1 make sure that is the letter you are looking for
    public Letter[][] replaceDiagonal()
    {
        for(int i = 0; i<stringList.size(); i++)
        {
            double firstPlace2 = Math.random() * (table[1].length - stringList.get(i).length());
            int r = (int)firstPlace2;
            double firstPlace1 = Math.random() * (table.length - stringList.get(i).length());
            int firstPlace = (int)firstPlace1;
            for(int j = 0; j<stringList.get(i).length(); j++)
            {
                String one = stringList.get(i);
                table[r+j][firstPlace+j] = new Letter(one.charAt(j));
            }
        }
        return table;   
    }
    public String printTable()
    {
        String crossword = "";
        for(int row=0; row < table.length; row++)
        {
            for(int col=0; col < table[row].length; col++)
            {
               crossword += table[row][col] + "\t";
            }
               crossword += ("\n" + "\n");
        }   
        return crossword;
    }
    public String printWordBank()
    {
        String newString = "";
        for(int i = 0; i < stringList.size(); i++)
        {
            newString += stringList.get(i);
            newString += "\n";
        }
        return "\n" + newString;
    }
    public String printTitle()
    {
        return title;
    }
}
