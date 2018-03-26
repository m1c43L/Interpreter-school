package interpreter;
import interpreter.bytecode.ByteCode;
import java.io.*;


/**
 * Loads the bytecodes from the file into the Virtual Machine.
 * Once loaded, get string from the CodeTable to construct an instance of
 * bytecode.
 * 
 * @author Michael
 */
public class ByteCodeLoader {
    
    private BufferedReader codeFile; 
            
    public ByteCodeLoader(String programFile) throws IOException{
        codeFile = new BufferedReader(new FileReader(programFile));
    }
    
    public Program loadCodes(){
      Program byteCodeProgram = new Program();
      
        try {
            while(codeFile.ready()){
                String code = codeFile.readLine(); 
                String codeClass = code.split("\\s+")[1];
                
                ByteCode byteCode = (ByteCode) 
                        Class.forName(CodeTable.get(codeClass)).newInstance();
                
                //initialize bytecode with [] strings preceding the 
                //byte code (eg. "1. LIT 2") ars will be [0] = 2
                //since there only "2" after the LIT bytecode.
                byteCode.init(code.substring(code.
                        lastIndexOf(codeClass) + codeClass.length()).
                        split("\\s+"));
                
            }
        } catch (Exception e) {}
        
        return byteCodeProgram;
    }
}
