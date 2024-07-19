package G26.Project.Model.P2P;

import java.io.Serializable;


/**
 * The `Chat` class represents a chat conversation between two users in a peer-to-peer (P2P) messaging feature.
 * It encapsulates information about the chat, including the user's name, the last message exchanged, the timestamp
 * of the last message, and the unique identifier of the target user.
 * <p>
 * This class is used to store and manage chat-related data for a P2P messaging application, allowing users to
 * engage in one-on-one conversations.
 * <p>
 * {@code @Author: Jing Li (Original)}
 *           UID : u7533831
 */
public class Chat implements Serializable {
    private String userName; // the friends name
    private String lastMessage; // last message content
    private String timestamp; // system time
    private String targetUserUid;

    public String getUserName() {
        return userName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTargetUserUid() {
        return targetUserUid;
    }

    public void setTargetUserUid(String targetUserUid) {
        this.targetUserUid = targetUserUid;
    }

}