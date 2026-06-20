// Design a text editor with commands:

// Copy
// Paste
// Undo
// Redo

//copies entire text on the editor  

import java.util.*;
import java.lang.*;
import java.io.*;


interface Command{
    void execute();
}

interface UndoableCommand extends Command{
    void undo();
}
interface RedoableCommand extends Command{
    void redo();
}


record Operation(int start, int end){}
class TextEditor{
    public StringBuilder text;
    public String copyBuffer;
    Deque<Operation> undoOperations;
    Deque<String> redoOperations;
    
    public TextEditor(){
        this.text = new StringBuilder();
        this.copyBuffer = new String();
        undoOperations = new ArrayDeque<>();
        redoOperations = new ArrayDeque<>();
    }

    
    public void read(){
        System.out.println("==============EDITOR===================");
        System.out.println(this.text);
        System.out.println("=======================================");
    }
    public void write(String text){
        this.text.append(text);
        this.copyBuffer = new String();
    }
}

class CopyCommand implements Command{
    TextEditor editor;
    CopyCommand(TextEditor editor){
        this.editor = editor;
    }
    @Override
    public void execute(){
        editor.copyBuffer = editor.text.toString();
        System.out.println("--Text from the editor copied--");
    }
}
class PasteCommand implements UndoableCommand,RedoableCommand{
    TextEditor editor;
    PasteCommand(TextEditor editor){
        this.editor = editor;
    }
    @Override
    public void execute(){
        if(editor.copyBuffer.length()==0)return;
        editor.undoOperations.push(new Operation(editor.text.length(),editor.text.length()+editor.copyBuffer.length()));
        editor.text.append(editor.copyBuffer);
        System.out.println("--Text pasted on the editor--");
    }
    @Override 
    public void undo(){
        if(editor.undoOperations.size()==0)return ;
        Operation operation = editor.undoOperations.pop();
        String temp = editor.text.substring(operation.start(),operation.end());
        editor.text.delete(operation.start(), operation.end());
       
        editor.redoOperations.push(temp);
        System.out.println("--Text pasted undo from the editor--");
    }
    @Override 
    public void redo(){
        if(editor.redoOperations.size()==0)return ;
        String temp = editor.redoOperations.pop();
        editor.undoOperations.push(new Operation(editor.text.length(),editor.text.length()+temp.length()));
        editor.text.append(temp);
        System.out.println("--Text pasted redo to the editor--");
    }
}



class Remote{
    Command command;
    public void setCommand(Command command){
        this.command = command;
    }
    public void pressButton(){
        command.execute();
    }
    public void pressRedoButton(){
        if(command instanceof RedoableCommand rc){
            rc.redo();
        }
    }
    public void pressundoButton(){
        if(command instanceof UndoableCommand uc){
            uc.undo();
        }
    }
}

public class TextEditorProblem
{
	public static void main (String[] args) throws java.lang.Exception
	{
		TextEditor editor = new TextEditor();
        Command paste = new PasteCommand(editor);
        Command copy = new CopyCommand(editor);
   
        
        editor.write("Police");
        
        Remote remote = new Remote();
        remote.setCommand(copy);
        remote.pressButton();
        remote.setCommand(paste);
        remote.pressButton();
        
        editor.read();
        
       
        remote.pressundoButton();
         
        editor.read();
        
        remote.setCommand(copy);
        remote.pressButton();
        remote.setCommand(paste);
        remote.pressButton();
        
        editor.read();
	}
}
