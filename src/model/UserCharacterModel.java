package model;

public class UserCharacterModel extends Model {
    private int id;
    private int userId;
    private String name;
    private String kana;
    private String age;
    private String gender;
    private String height;
    private String description;
    private String iconPath;
    private int currentCharacterFlg;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getKana() {
        return kana;
    }
    public void setKana(String kana) {
        this.kana = kana;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getHeight() {
        return height;
    }
    public void setHeight(String height) {
        this.height = height;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getIconPath() {
        return iconPath;
    }
    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }
    public int getCurrentCharacterFlg() {
        return currentCharacterFlg;
    }
    public void setCurrentCharacterFlg(int currentCharacterFlg) {
        this.currentCharacterFlg = currentCharacterFlg;
    }
    
}
