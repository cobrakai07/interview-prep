import java.util.*;
import java.lang.*;
import java.io.*;

interface Command{
    void execute();
}


class Tv{
    public void turnOn(){
        System.out.println("TV turned on");
    }
    public void turnOff(){
        System.out.println("TV turned off");
    }
}

class OnCommand implements Command{
    private Tv tv;
    public OnCommand(Tv tv){
        this.tv = tv;
    }
    
    @Override 
    public void execute(){
        tv.turnOn();
    }
}
class OffCommand implements Command{
    private Tv tv;
    public OffCommand(Tv tv){
        this.tv = tv;
    }
    
    @Override 
    public void execute(){
        tv.turnOff();
    }
}

class Remote{
    private Command command;
 
    
    public void setCommand(Command command){
        this.command = command;
    }
    
    public void pressButton(){
        command.execute();
    }
}

public class CommandPattern
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Tv tv = new Tv();
        
        Command onCommand = new OnCommand(tv);
		Command offCommand = new OffCommand(tv);
		
		Remote remote = new Remote();
		remote.setCommand(onCommand);
		remote.pressButton();
		remote.setCommand(offCommand);
		remote.pressButton();
	}
}
