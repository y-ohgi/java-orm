package model;

public class CharacterFollowModel extends Model {
    private int id;
    private int user_id;
    private int tag_id;
    private String name;
    private String kana;
    private String age;
    private String height;
    private String gender;
    private String description;
    private String iconPath;
    private int currentCharacterFlg;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public int getTag_id() {
        return tag_id;
    }
    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
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
    public String getHeight() {
        return height;
    }
    public void setHeight(String height) {
        this.height = height;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
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
