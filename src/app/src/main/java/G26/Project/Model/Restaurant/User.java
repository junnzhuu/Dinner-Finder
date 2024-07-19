package G26.Project.Model.Restaurant;

import java.util.ArrayList;
import java.util.List;

import G26.Project.Model.P2P.Message;

/**
 * The `User` class represents a user in the system.
 * <p>
 * {@code @Author: Jing Li (Original)}
 *           UID : u7533831
 * {@code @Contributor: Jiawei Ye & Jun Zhu : add 'displayName' and 'profileAvatar' field}
 */
public class User {
    private String uid;
    private String email;
    private String displayName;
    private String profileAvatar = "https://firebasestorage.googleapis.com/v0/b/gourmet-restaurant-search.appspot.com/o/user_profile%2Fdefault.jpeg?alt=media&token=4ec75769-2255-4ddb-ab2e-ffed85ef6834&_gl=1*lkgre3*_ga*MTc0NTc4OTM2NS4xNjk2NDc4MDMz*_ga_CW55HF8NVT*MTY5NzI5NDEzNC4xNC4xLjE2OTcyOTQ5MDcuNjAuMC4w";
    private List<Message> messages;

    public User() {}

    /*
     * Setters and getters are use for firebase to construct objects
     */
    public String getProfileAvatar() {
        return this.profileAvatar;
    }

    public void setProfileAvatar(String url) {
        this.profileAvatar = url;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String url) {
        this.displayName = url;
    }

    public User(String uid, String email) {
        this.uid = uid;
        this.email = email;
        this.messages = new ArrayList<>();
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }
    public List<Message> getMessages() {
        return messages;
    }

}
