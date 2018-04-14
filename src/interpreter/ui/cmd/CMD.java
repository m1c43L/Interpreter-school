/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.ui.cmd;

/**
 *
 * @author Michael
 */
public abstract class CMD {
    
    /**
     * execute the command
     * 0 position contains the actual command.
     * eg. [0] = setBreakpoint [1] = 1
     *     command             parameter
     * @param inputs 
     */
    public abstract void execute(String [] inputs);
    
}
