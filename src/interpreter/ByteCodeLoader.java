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
                String codeClass = code.split("\\s+")[0];
                //System.out.println(codeClass);
                ByteCode byteCode = (ByteCode) 
                        Class.forName("interpreter.bytecode." + 
                                CodeTable.get(codeClass)).newInstance();

                byteCode.init(code.split("\\s+"));
                byteCodeProgram.add(byteCode);
            }
            
        } catch (Exception e) {}
        
        byteCodeProgram.resolveAddress();
        
        return byteCodeProgram;
    }
}
