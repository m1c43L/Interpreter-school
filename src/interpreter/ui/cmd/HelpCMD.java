/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.ui.cmd;

import interpreter.debugger.DebuggerVirtualMachine;
import interpreter.ui.Commands;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michael
 */
public class HelpCMD extends CMD {
    private List commands;
    
    private void printDefinition(String definition){
        System.out.println(definition);
    }
    

    @Override
    public void execute(DebuggerVirtualMachine dvm) {
        //Collections.sort(commands);
        for(Object out: commands){
            System.out.println(((CMD)out).getStringDefinition());
        }
    }

    @Override
    public void setParameters(String[] inputs) {
        commands = new ArrayList(Commands.getCommands());
    }

    @Override
    public String getStringDefinition() {
        return "?       - (Help)";
    }
    
}
