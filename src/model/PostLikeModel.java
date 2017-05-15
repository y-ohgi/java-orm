package model;

public class PostLikeModel extends Model {
    private int id;
    private int postId;
    private int repliePostId;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPostId() {
        return postId;
    }
    public void setPostId(int postId) {
        this.postId = postId;
    }
    public int getRepliePostId() {
        return repliePostId;
    }
    public void setRepliePostId(int repliePostId) {
        this.repliePostId = repliePostId;
    }

}
