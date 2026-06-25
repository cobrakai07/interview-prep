class DoubleLockSinglton{

    private static volatile SingleSimple instance;

    private DoubleLockSinglton(){}

    public static SingleSimple getInstance(){
        if(instance==null){
            synchronized(SingleSimple.class){
                if(instance==null){
                instance = new SingleSimple();
                return instance;
                }
           
            }
            
        }
        return instance;
    }

}

public class SingletonPattern{
    public static void main(String[] args){

    }
}