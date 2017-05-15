package model;

public class PostModel extends Model {
    private int id;
    private int userCharacterId;
    private int parentPostId;
    private String body;
    private String photoPath;

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
    public int getParentPostId() {
        return parentPostId;
    }
    public void setParentPostId(int parentPostId) {
        this.parentPostId = parentPostId;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getPhotoPath() {
        return photoPath;
    }
    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
