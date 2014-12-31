package word.puzzle;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Actions 
{
    String title;
    String word1;
    ArrayList<String> stringList;
    char[][] table;
    char[][] tableOfAnswer;
    int rows;
    int columns;
    boolean check1 = false;
    int numberOf = 0;
    public char randomLetter() //WORKS
    {
        char[] letters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        double randomNumber1 = Math.random() * 26.0;
        int randomNumber2 = (int)randomNumber1;
        return letters[randomNumber2];
    }
    public void collectTitle() //WORKS
    {
        String title1 = JOptionPane.showInputDialog("What is the title of this Wordsearch? ");
        title = title1.toUpperCase();
    }
    public void collectLetters() //WORKS
    {
        String numberOf1 = JOptionPane.showInputDialog("How many words will be in the Wordsearch (#'s please)? ");
        while(check1 == false)
        {
            if(numberOf1.matches("[0-9][0-9]*"))
            {
                numberOf = Integer.parseInt(numberOf1);
                break;
            }
            numberOf1 = JOptionPane.showInputDialog("That is not a number! How many words will be in the Wordsearch (#'s please)? ");
        }
        stringList = new ArrayList();
        for(int i = 0; i<numberOf; i++)
        {
            String input = JOptionPane.showInputDialog("Please enter word number " + (i+1) + ", then press enter. ");
            stringList.add(input.toUpperCase());
        }
        String rows1 = JOptionPane.showInputDialog("What size Wordsearch would you like (#'s please)? ");
        while(check1 == false)
        {
            if (rows1.matches("[0-9][0-9]*"))
            {
                rows = Integer.parseInt(rows1);
                columns = rows;
                break;
            } 
        rows1 = JOptionPane.showInputDialog("That is not a number! What size Wordsearch would you like (#'s please)? ");
        }
    }
    public void randomLetterMaker() //WORKS
    {
        table = new char[rows][columns];
        for(int row=0; row < table.length; row++)
        {
            for (int col=0; col < table[row].length; col++)
            {
                table[row][col] = randomLetter();
            }
        }
    }
    public void answerArrayMaker() //WORKS
    {
        tableOfAnswer = new char[rows][columns];
    }
    public String formatHelpInput(String one) //WORKS
    {
        String firstLetter1 = one.substring(0,1);
        String lastLetters1 = one.substring(1);
        String firstLetter = firstLetter1.toUpperCase();
        String lastLetters = lastLetters1.toLowerCase();
        return firstLetter + lastLetters;
    }
    public char[][] replaceWithWord() //They don't work together... hmmm
    {
        for(int i = 0; i<stringList.size(); i++)
        {
            int r = (int)(Math.random() * (table.length - stringList.get(i).length()));
            int t = (int)(Math.random() * (table.length));
            int firstPlace = (int)(Math.random() * (table.length - stringList.get(i).length()));
            int randomNumber = (int)(Math.random() * 8);
            int lengthString = stringList.get(i).length()+1;
            for(int j = 1; j<lengthString; j++)
            {
                String one = stringList.get(i);  
                if(randomNumber == 0) //vertical WORKING
                {   
                    boolean check = checkIfOpenVertical(firstPlace+j-1,t,one);
                    if(check == true)
                    {
                        table[firstPlace+j-1][t] = one.charAt(j-1);  
                        tableOfAnswer[firstPlace+j-1][t] = one.charAt(j-1); 
                    }
                    if(check == false)
                    {
                        i--; 
                        break;
                    }
                }
                if(randomNumber == 1) //diagonal WORKING
                {
                    boolean check = checkIfOpenDiagonal(r+j-1,firstPlace+j-1,one);
                    if(check == true)
                    {
                        table[r+j-1][firstPlace+j-1] = one.charAt(j-1); 
                        tableOfAnswer[r+j-1][firstPlace+j-1] = one.charAt(j-1); 
                    }
                    if(check == false)
                    {
                        i--; 
                        break;
                    }
                }
                if(randomNumber == 2) //horizontal WORKING
                {   
                    boolean check = checkIfOpenHorizontal(t,firstPlace+j-1,one);
                    if(check == true)
                    {
                        table[t][firstPlace+j-1] = one.charAt(j-1); 
                        tableOfAnswer[t][firstPlace+j-1] = one.charAt(j-1); 
                    }
                    if(check == false)
                    {
                        i--; 
                        break;
                    }
                }
                if(randomNumber == 3) //backwards Horizontal WORKING
                {
                    boolean check = checkIfOpenHorizontal(t,firstPlace+j-1,one);
                    if(check == true)
                    {
                        table[t][firstPlace+j-1] = one.charAt(lengthString-j-1);
                        tableOfAnswer[t][firstPlace+j-1] = one.charAt(lengthString-j-1);
                    }
                    if(check == false)
                    {
                        i--; 
                        break;
                    }
                }
                if(randomNumber == 4) //backwards Vertical WORKING
                {
                    boolean check = checkIfOpenVertical(firstPlace+j-1,t,one);
                    if(check == true)
                    {
                        table[firstPlace+j-1][t] = one.charAt(lengthString-j-1);  
                        tableOfAnswer[firstPlace+j-1][t] = one.charAt(lengthString-j-1);
                    }
                    if(check == false)
                    {
                        i--; 
                        break;
                    }
                }
                if(randomNumber == 5) //backwards Diagonal WORKING
                {
                    boolean check = checkIfOpenDiagonal(r+j-1,firstPlace+j-1,one);
                    if(check == true)
                    {
                        table[r+j-1][firstPlace+j-1] = one.charAt(lengthString-j-1); 
                        tableOfAnswer[r+j-1][firstPlace+j-1] = one.charAt(lengthString-j-1); 
                    }
                    if(check == false)
                    {
                        i--; 
                        break;
                    }
                }
                if(randomNumber == 6) //mirrored Diagonal WORKING
                {
                    boolean check = checkIfOpenMirrorDiagonal(r+j-1,firstPlace+one.length()-j,one);
                    if(check == true)
                    {
                        table[r+j-1][firstPlace+one.length()-j] = one.charAt(j-1); 
                        tableOfAnswer[r+j-1][firstPlace+one.length()-j] = one.charAt(j-1); 
                    }
                    if(check == false)
                    {
                        i--; 
                        break;
                    }
                }
                if(randomNumber == 7) //backwards mirrored Diagonal WORKING
                {
                    boolean check = checkIfOpenMirrorDiagonal(r+j-1,firstPlace+one.length()-j,one);
                    if(check == true)
                    {
                        table[r+j-1][firstPlace+one.length()-j] = one.charAt(lengthString-j-1); 
                        tableOfAnswer[r+j-1][firstPlace+one.length()-j] = one.charAt(lengthString-j-1); 
                    }
                    if(check == false)
                    {
                        i--; 
                        break;
                    }
                }
            }
            System.out.println(printAnswerTable() + "\n");
        }
        return table;
    }
    public boolean checkIfOpenHorizontal(int row, int col, String one) //WORKS
    {
        for(int i = col; i<one.length(); i++)
        {
            if(tableOfAnswer[row][i] != '\u0000') return false;
        }
        return true;
    }
    public boolean checkIfOpenVertical(int row, int col, String one) //WORKS
    {
        for(int i = row; i<one.length(); i++)
        {
            if(tableOfAnswer[i][col] != '\u0000') return false;
        }
        return true;
    }
    public boolean checkIfOpenDiagonal(int row, int col, String one) //WORKS
    {
        int test = 0;
        for(int i = col; i<one.length(); i++)
        {
            if(tableOfAnswer[row+test][i] != '\u0000') return false;
            test++;
        }
        return true;
    }
    public boolean checkIfOpenMirrorDiagonal(int row, int col, String one) //WORKS
    {
        int test = 0;
        for(int i = row; i<one.length(); i++)
        {
            if(tableOfAnswer[i][col-test] != '\u0000') return false;
            test++;
        }
        return true;
    }
    public String printTable() //WORKS
    {
        try
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
        catch(Exception e)
        {
            return "There was an unexpected error, sorry about that. Can you please try again?";
        }
    }
    public String printAnswerTable() //WORKS
    {
        String crossword = "";
        for(int row=0; row < tableOfAnswer.length; row++)
        {
            for(int col=0; col < tableOfAnswer[row].length; col++)
            {
               crossword += tableOfAnswer[row][col] + "\t";
            }
            crossword += ("\n" + "\n");
        }   
        return crossword;
    }
    public String printWordBank() //WORKS
    {
        String newString = "";
        for(int i = 0; i < stringList.size(); i++)
        {
            newString += stringList.get(i);
            newString += "\n";
        }
        return "\n" + newString;
    }
    public String printTitle() //WORKS
    {
        return title;
    }
    public boolean containedHorizontally() //WORKS
    {
        String word = word1.toUpperCase(); 
        boolean a = false; boolean b = false;
        for(int i = 0; i<table.length; i++)
        {
            for(int r = 0; r<table.length; r++)
            {
                if(table[i][r] == word.charAt(0) && r+word.length()<=table.length)
                {
                    a = true;
                    b = true;
                    for(int letter = 0; letter<word.length(); letter++)
                    {
                        if(table[i][r+letter] != word.charAt(letter))
                        {
                            a = false;
                            b = false;
                            break;
                        }
                        if(b == false) break;
                    }
                }
                if(b == true) break;
            }
            if(b == true) break;
        }
        return a;
    }
    public boolean containedVertically() //WORKS
    {
        String word = word1.toUpperCase(); 
        boolean a = false; boolean b = false;
        for(int i = 0; i<table.length; i++)
        {
            for(int r = 0; r<table.length; r++)
            {
                if(table[r][i] == word.charAt(0) && r+word.length()<=table.length)
                {
                    a = true;
                    b = true;
                    for(int letter = 0; letter<word.length(); letter++)
                    {
                        if(table[r+letter][i] != word.charAt(letter))
                        {
                            a = false;
                            b = false;
                            break;
                        }
                        if(b == false) break;
                    }
                }
                if(b == true) break;
            }
            if(b == true) break;
        }
        return a;
    }
    public boolean containedDiagonally() //WORKS
    {
        String word = word1.toUpperCase();
        boolean a = false; boolean b = false;
        for(int i = 0; i<table.length; i++)
        {
            for(int r = 0; r<table.length; r++)
            {
                if(table[r][i] == word.charAt(0) && r+word.length()<=table.length && i+word.length()<=table.length)
                {
                    a = true;
                    b = true;
                    for(int letter = 0; letter<word.length(); letter++)
                    {
                        if(table[r+letter][i+letter] != word.charAt(letter))
                        {
                            a = false;
                            b = false;
                            break;
                        }
                        if(b == false) break;
                    }
                }
                if(b == true) break;
            }
            if(b == true) break;
        }
        return a;
    }
    public boolean containedMirrorDiagonally() //WORKS
    {
        String word = word1.toUpperCase(); 
        boolean a = false; boolean b = false;
        for(int i = 0; i<table.length; i++)
        {
            for(int r = 0; r<table.length; r++)
            {
                if(table[i][r] == word.charAt(0) && r+word.length()<=table.length && i-word.length()+1>=0)
                {
                    a = true;
                    b = true;
                    for(int letter = 0; letter<word.length(); letter++)
                    {
                        if(table[i-letter][r+letter] != word.charAt(letter))
                        {
                            a = false;
                            b = false;
                            break;
                        }
                        if(b == false) break;
                    }
                }
                if(b == true) break;
            }
            if(b == true) break;
        }
        return a;
    }
    public boolean containedBackwardHorizontally() //WORKS
    {
        String word2 = new StringBuffer(word1).reverse().toString();
        String word = word2.toUpperCase(); 
        boolean a = false; boolean b = false;
        for(int i = 0; i<table.length; i++)
        {
            for(int r = 0; r<table.length; r++)
            {
                if(table[i][r] == word.charAt(0) && r+word.length()<=table.length)
                {
                    a = true;
                    b = true;
                    for(int letter = 0; letter<word.length(); letter++)
                    {
                        if(table[i][r+letter] != word.charAt(letter))
                        {
                            a = false;
                            b = false;
                            break;
                        }
                        if(b == false) break;
                    }
                }
                if(b == true) break;
            }
            if(b == true) break;
        }
        return a;
    }
    public boolean containedBackwardVertically() //WORKS
    {       
        String word2 = new StringBuffer(word1).reverse().toString();
        String word = word2.toUpperCase();
        boolean a = false; boolean b = false;
        for(int i = 0; i<table.length; i++)
        {
            for(int r = 0; r<table.length; r++)
            {
                if(table[r][i] == word.charAt(0) && r+word.length()<=table.length)
                {
                    a = true;
                    b = true;
                    for(int letter = 0; letter<word.length(); letter++)
                    {
                        if(table[r+letter][i] != word.charAt(letter))
                        {
                            a = false;
                            b = false;
                            break;
                        }
                        if(b == false) break;
                    }
                }
                if(b == true) break;
            }
            if(b == true) break;
        }
        return a;
    }
    public boolean containedBackwardDiagonally() //WORKS
    {
        String word2 = new StringBuffer(word1).reverse().toString();
        String word = word2.toUpperCase();
        boolean a = false; boolean b = false;
        for(int i = 0; i<table.length; i++)
        {
            for(int r = 0; r<table.length; r++)
            {
                if(table[r][i] == word.charAt(0) && r+word.length()<=table.length && i+word.length()<=table.length)
                {
                    a = true;
                    b = true;
                    for(int letter = 0; letter<word.length(); letter++)
                    {
                        if(table[r+letter][i+letter] != word.charAt(letter))
                        {
                            a = false;
                            b = false;
                            break;
                        }
                        if(b == false) break;
                    }
                }
                if(b == true) break;
            }
            if(b == true) break;
        }
        return a;
    }
    public boolean containedBackwardMirrorDiagonally() //WORKS
    {
        String word2 = new StringBuffer(word1).reverse().toString();
        String word = word2.toUpperCase();
        boolean a = false; boolean b = false;
        for(int i = 0; i<table.length; i++)
        {
            for(int r = 0; r<table.length; r++)
            {
                if(table[i][r] == word.charAt(0) && r+word.length()<=table.length && i-word.length()+1>=0)
                {
                    a = true;
                    b = true;
                    for(int letter = 0; letter<word.length(); letter++)
                    {
                        if(table[i-letter][r+letter] != word.charAt(letter))
                        {
                            a = false;
                            b = false;
                            break;
                        }
                        if(b == false) break;
                    }
                }
                if(b == true) break;
            }
            if(b == true) break;
        }
        return a;
    }
    public String printContained() //WORKING
    {
        try
        {
            word1 = JOptionPane.showInputDialog("What is the word you are looking for? ");
            if(containedHorizontally() == true) return  formatHelpInput(word1) + " is in the Wordsearch, horizontally."; //WORKING
            if(containedBackwardHorizontally() == true) return formatHelpInput(word1) + " is in the Wordsearch, backward horizontally."; //WORKING
            if(containedVertically() == true) return formatHelpInput(word1) + " is in the Wordsearch, vertically."; //WORKING
            if(containedBackwardVertically() == true) return  formatHelpInput(word1) + " is in the Wordsearch, backward vertically."; //WORKING
            if(containedDiagonally() == true) return formatHelpInput(word1) + " is in the Wordsearch, diagonally."; //WORKING
            if(containedBackwardDiagonally() == true) return formatHelpInput(word1) + " is in the Wordsearch, backward diagonally."; //WORKING
            if(containedMirrorDiagonally() == true) return formatHelpInput(word1) + " is in the Wordsearch, mirror diagonally."; //WORKING
            if(containedBackwardMirrorDiagonally() == true) return formatHelpInput(word1) + " is in the Wordsearch, backward mirror diagonally."; // WORKING
            else return formatHelpInput(word1) + " is not in the Wordsearch.";
        }
        catch(Exception ex)
        {
            return formatHelpInput(word1) + " is not in the Wordsearch.";
        }
    }
}