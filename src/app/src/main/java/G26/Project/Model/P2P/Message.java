package G26.Project.Model.P2P;

import com.google.firebase.firestore.PropertyName;

import java.io.Serializable;

/**
 * The `Message` class represents a message exchanged between users in a chat. It encapsulates
 * information about the message, including a unique message ID, sender and receiver user IDs, message content,
 * and a timestamp indicating when the message was sent.
 * <p>
 * This class is designed to facilitate the storage and management of messages in a messaging application, allowing
 * messages to be sent, received, and displayed with associated metadata.
 * <p>
 * {@code @Author: Jing Li (Original)}
 *           UID : u7533831
 */
public class Message implements Serializable {
    private String messageId; // Unique ID for the message
    private String senderUid;
    private String receiverUid;
    private String messageContent; // Content of the message
    private long timestamp;

    /**
     * Default constructor required for Firebase serialization.
     */
    public Message() {}

    /**
     * Creates a new message with sender and receiver information, message content, and a timestamp.
     *
     * @param senderUid      The user ID of the message sender.
     * @param receiverUid    The user ID of the message receiver.
     * @param messageContent The content of the message.
     * @param timestamp      The timestamp indicating when the message was sent.
     */
    public Message(String senderUid, String receiverUid, String messageContent, long timestamp) {
        this.senderUid = senderUid;
        this.receiverUid = receiverUid;
        this.messageContent = messageContent;
        this.timestamp = timestamp;
    }

    /**
     * Creates a new message with message content and a timestamp.
     *
     * @param messageContent The content of the message.
     * @param timestamp      The timestamp indicating when the message was sent.
     */
    public Message(String messageContent, long timestamp) {
        this.messageContent = messageContent;
        this.timestamp = timestamp;
    }

    @PropertyName("content")
    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setSenderUid(String senderUid) {
        this.senderUid = senderUid;
    }

    public void setReceiverUid(String receiverUid) {
        this.receiverUid = receiverUid;
    }

    @PropertyName("content")
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getSenderUid() {
        return senderUid;
    }

    public String getReceiverUid() {
        return receiverUid;
    }

    public long getTimestamp() {
        return timestamp;
    }

}
