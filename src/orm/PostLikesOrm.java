package orm;

import java.util.HashMap;
import java.util.Map;

public class PostLikesOrm extends Orm{
    protected static String TABLE_NAME = "PostLikes"; // テーブル名
    // テーブルのカラムを登録
    protected static Map<String, Class<?>> TABLE_COLUMNS = new HashMap<String, Class<?>>() {
        {
            put("id", int.class);
            put("user_character_id", int.class);
            put("post_id", int.class);
        }
    };

    public PostLikesOrm() {
        super(TABLE_NAME, TABLE_PK_NAME, TABLE_COLUMNS, TABLE_RELATED);
    }

}
