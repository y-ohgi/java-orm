package model;

public class PostReplieOrm extends Model {
    private int id;
    private int userCharacterId;
    private int postId;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserCharacterId() {
        return userCharacterId;
    }
    public void setUserCharacterId(int userCharacterId) {
        this.userCharacterId = userCharacterId;
    }
    public int getPostId() {
        return postId;
    }
    public void setPostId(int postId) {
        this.postId = postId;
    }



}
