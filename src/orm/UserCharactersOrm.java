package orm;

import java.util.HashMap;
import java.util.Map;

import model.Model;
import model.UserCharacterModel;

public class UserCharactersOrm extends Orm{
    protected static String TABLE_NAME = "UserCharacters"; // テーブル名
    // テーブルのカラムを登録
    protected static Map<String, Class<?>> TABLE_COLUMNS = new HashMap<String, Class<?>>() {
        {
            put("id", int.class);
            put("user_id", int.class);
            put("name", String.class);
            put("kana", String.class);
            put("age", String.class);
            put("height", String.class);
            put("gender", String.class);
            put("description", String.class);
            put("icon_path", String.class);
            put("current_character_flg", int.class);
        }
    };
    // 外部キー情報を登録
    protected static Map<String, String[]> TABLE_RELATED = new HashMap<String, String[]>() {
        {
            String[] rel = {"Users", "UserModel"};
            put("user_id", rel);
        }
    };
    
    public UserCharactersOrm() {
        super(TABLE_NAME, TABLE_PK_NAME, TABLE_COLUMNS, TABLE_RELATED);
    }
    
}
