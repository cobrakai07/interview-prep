import java.util.ArrayList;
import java.util.List;


interface Subject{
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void sendNotification(String message);
}

interface Observer{
    void recieveNotification(String message);
}
class NotificationSubscriber implements Observer{
    String name;
    NotificationSubscriber(String name){
        this.name = name;
    }
    @Override
    public void recieveNotification(String message){
        System.out.println(this.name+" recieved notification: "+ message);
    }
}
class EmailSubscriber implements Observer{
    String name;
    EmailSubscriber(String name){
        this.name = name;
    }
    @Override
    public void recieveNotification(String message){
        System.out.println(this.name+" recieved email: "+ message);
    }
}
class YouTubeChannel implements Subject{
    List<Observer> subscribers;
    public YouTubeChannel(){
        subscribers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer){
        subscribers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer){
        subscribers.remove(observer);
    }

    @Override
    public void sendNotification(String message){
        for(Observer subscriber : subscribers){
            subscriber.recieveNotification(message);
        }
    }


}
public class ObserverPattern {
    public static void main(String[] args) {
        Subject youTubeChannel = new YouTubeChannel();
        Observer john = new EmailSubscriber("John");
        Observer alex = new NotificationSubscriber("Alex");
        youTubeChannel.addObserver(alex);
        youTubeChannel.addObserver(john);
        youTubeChannel.sendNotification("AWS hacks");
        youTubeChannel.removeObserver(alex);
        youTubeChannel.sendNotification("Azure hacks");
    }
}
