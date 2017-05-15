package orm;

import java.util.HashMap;
import java.util.Map;

public class PostRepliesOrm extends Orm{
    protected static String TABLE_NAME = "PostReplies"; // テーブル名
    // テーブルのカラムを登録
    protected static Map<String, Class<?>> TABLE_COLUMNS = new HashMap<String, Class<?>>() {
        {
            put("id", int.class);
            put("post_id", int.class);
            put("replie_post_id", int.class);
        }
    };

    public PostRepliesOrm() {
        super(TABLE_NAME, TABLE_PK_NAME, TABLE_COLUMNS, TABLE_RELATED);
    }

}
