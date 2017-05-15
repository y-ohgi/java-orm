package orm;

import java.util.HashMap;
import java.util.Map;

public class CharacterFollowsOrm extends Orm{
    protected static String TABLE_NAME = "CharacterFollows"; // テーブル名
    // テーブルのカラムを登録
    protected static Map<String, Class<?>> TABLE_COLUMNS = new HashMap<String, Class<?>>() {
        {
            put("id", int.class);
            put("user_id", int.class);
            put("follow_user_id", int.class);
        }
    };

    public CharacterFollowsOrm() {
        super(TABLE_NAME, TABLE_PK_NAME, TABLE_COLUMNS, TABLE_RELATED);
    }

}
