/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.ui.cmd;

import interpreter.ui.Commands;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Michael
 */
public class HelpCMD extends CMD {

    @Override
    public void execute(String[] inputs) {
        List commands = new ArrayList(Commands.getCommands());
        Collections.sort(commands);
        for(Object out: commands){
            System.out.println(out.toString());
        }
                
    }
    
    private void printDefinition(String definition){
        System.out.println(definition);
    }
    
    @Override
    public String toString(){
        return "?       - (Help)";
    }
    
}
