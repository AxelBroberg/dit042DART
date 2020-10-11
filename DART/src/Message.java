// We created a class for each message since we thought would make it easier to use

public class Message {


    private String text;
    private String senderID;
    private boolean read;

    Message(String text, String senderID){
        this.text = text;
        this.senderID = senderID;
        this.read = false;
    }

    public boolean isRead() {
        return read;
    }

    public void markRead(){
        this.read = true;
    }

    @Override
    public String toString() {
        markRead();
        return senderID + " : " + text;
    }
}
