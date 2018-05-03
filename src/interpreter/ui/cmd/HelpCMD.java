/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.ui.cmd;

import interpreter.debugger.DebuggerVirtualMachine;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Michael
 */
public class HelpCMD extends CMD {
    private List<CMD> commands;
    private StringBuilder output;
    

    @Override
    public void executeTo(DebuggerVirtualMachine dvm) {
        output = new StringBuilder("***********Commands**************");
        for(CMD out: commands){     
            output.append("\n").append(out.definition());
        }
    }

    @Override
    public void setParameters(String[] inputs) {
        commands = new ArrayList(Commands.getCommands());
    }

    @Override
    public String definition() {
        return "'?' \t- (Help) Prints all Commands and its definition";
    }

    @Override
    public StringBuilder output() {
        return output;
    }
    
}
