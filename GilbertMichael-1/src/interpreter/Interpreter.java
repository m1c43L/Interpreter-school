package interpreter;
import interpreter.ByteCodeLoader;
import interpreter.debugger.DebuggerVirtualMachine;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
* <pre>
*
*
*
* Interpreter class runs the interpreter:
* 1. Perform all initializations
* 2. Load the bytecodes from file
* 3. Run the virtual machine
*
*
* </pre>
*/
public class Interpreter {
    
    ByteCodeLoader bcl;
    private boolean isDebugMode;
    private String sourceCodeFile;
    
    public Interpreter(String codeFile) {
        isDebugMode = false;
        try {
            CodeTable.init();
            bcl = new ByteCodeLoader(codeFile);
        } catch (IOException e) {
        System.out.println("**** " + e);
        }
    }
    
    public Interpreter(String baseFileName, String sourceExtention,
            String byteCodeExtention){
        isDebugMode = true;
        try {
            CodeTable.init();
            CodeTable.initDebuggerByteCodes();
            bcl = new ByteCodeLoader(baseFileName + byteCodeExtention);
            sourceCodeFile = baseFileName + sourceExtention;
        } catch (IOException e) {
        System.out.println("**** " + e);
        }
    }
    
    void run() {
        System.out.println("****************Debugging " + sourceCodeFile + "**************" );
        Program program = bcl.loadCodes();
        VirtualMachine vm;
        if(isDebugMode){
            vm = new DebuggerVirtualMachine(program);
            try {
                ((DebuggerVirtualMachine)vm).loadSourceCode(sourceCodeFile);
                new interpreter.ui.UI(((DebuggerVirtualMachine)vm)).run();
            } catch (IOException ex) {
                System.out.println("*********File Not Found*********");
                System.exit(1);
            }
        }else{
            vm = new VirtualMachine(program);
            vm.executeProgram();
        }
        
        
        
    }
    
    
   
    public static void main(String args[]) {
        if (args.length == 0) {
            System.out.println("***Incorrect usage, try: "
                    + "java interpreter.Interpreter <file>");
            System.exit(1);
        }else if(args[0].equals("-d") && args.length == 2){
            new Interpreter(args[1],".x",".x.cod").run();
        }else{
             new Interpreter(args[0]).run();
        }
            
    }
}
  /*
   
    // for testing purpose
    public static void main(String args[]) {
        
      Interpreter test  = new Interpreter("factorial",".x",".x.cod");
     // Interpreter test = new Interpreter("test.txt");
       test.run();
       
    }
  
}
*/