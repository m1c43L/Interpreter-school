/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.ui.cmd;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;


/**
 *
 * @author Michael
 */
public class Commands{
    
   private static final HashMap <String,CMD> COMMANDS;
   
   static {
       COMMANDS = new HashMap();
       COMMANDS.put("+", new SetBreakPointCMD());
       COMMANDS.put("-", new ClearBreakPointCMD());
       COMMANDS.put("c", new ContinueExecutionCMD());
       COMMANDS.put("f", new DisplayCurrentFunctionCMD());
       COMMANDS.put("v", new DisplayVariablesCMD());
       COMMANDS.put("?", new HelpCMD());
       COMMANDS.put("q", new QuitExecutionCMD());    
       COMMANDS.put("s", new PrintSourceCodeCMD());
       COMMANDS.put("l", new StepOverCMD());
       COMMANDS.put("i", new StepInCMD());
       COMMANDS.put("o", new StepOutCMD());
       COMMANDS.put("t", new TraceCMD());
   }
   
   public static CMD get(String commandName){
       return COMMANDS.get(commandName);
   }
   
   public static Collection <CMD> getCommands(){
       return COMMANDS.values();
   }
   
   public static boolean contains(String commandName){
       return COMMANDS.containsKey(commandName);
   }
   
}
