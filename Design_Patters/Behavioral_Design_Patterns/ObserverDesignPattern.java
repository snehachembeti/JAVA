import java.util.ArrayList;
import java.util.List;

public class ObserverDesignPattern {
    public static void main(String[] args) {
        YouTubeChannel channel = new YouTubeChannel("Code With Sneha");

        YouTubeSubscriber userOne = new YouTubeUser("Ravi");
        YouTubeSubscriber userTwo = new YouTubeUser("Anu");

        channel.subscribe(userOne);
        channel.subscribe(userTwo);

        channel.uploadVideo("Observer Pattern in Java");

        channel.unsubscribe(userOne);
        channel.uploadVideo("Strategy Pattern in Java");
    }
}

interface YouTubeSubscriber {
    void update(String channelName, String videoTitle);
}

class YouTubeUser implements YouTubeSubscriber {
    private String name;

    public YouTubeUser(String name) {
        this.name = name;
    }

    @Override
    public void update(String channelName, String videoTitle) {
        System.out.println(name + " received notification: " + channelName + " uploaded " + videoTitle);
    }
}

class YouTubeChannel {
    private String channelName;
    private List<YouTubeSubscriber> subscribers = new ArrayList<>();

    public YouTubeChannel(String channelName) {
        this.channelName = channelName;
    }

    public void subscribe(YouTubeSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(YouTubeSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void uploadVideo(String videoTitle) {
        System.out.println(channelName + " uploaded new video: " + videoTitle);
        notifySubscribers(videoTitle);
    }

    private void notifySubscribers(String videoTitle) {
        for (YouTubeSubscriber subscriber : subscribers) {
            subscriber.update(channelName, videoTitle);
        }
    }
}
