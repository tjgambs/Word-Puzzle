/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package word.puzzle;


public class Letter {
    
    private char let;
    private boolean usedForWord;
    
    public Letter (char c)
            //constructor - executes when you create a NEW instance of this class
    {
        let = c;
        usedForWord = false;
    }
    
    public void replace(char c)
    {
        let = c;
        usedForWord = true;
    }
    
    public boolean wasNotReplaced()
    {
        return !usedForWord;
    }
    
    public String toString()
    {
        return ""+let;
    }
    
}
