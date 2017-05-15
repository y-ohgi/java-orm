package orm;

import java.util.HashMap;
import java.util.Map;

public class PostsOrm extends Orm{
    protected static String TABLE_NAME = "Posts"; // テーブル名
    // テーブルのカラムを登録
    protected static Map<String, Class<?>> TABLE_COLUMNS = new HashMap<String, Class<?>>() {
        {
            put("id", int.class);
            put("user_character_id", int.class);
            put("parent_post_id", int.class);
            put("body", String.class);
            put("photo_path", String.class);
        }
    };

    public PostsOrm() {
        super(TABLE_NAME, TABLE_PK_NAME, TABLE_COLUMNS, TABLE_RELATED);
    }

}
