package orm;

import java.util.HashMap;
import java.util.Map;

public class TagsOrm extends Orm {
    public final static String TABLE_NAME = "Tags"; // テーブル名
    // テーブルのカラムを登録
    public final static Map<String, Class<?>> TABLE_COLUMNS = new HashMap<String, Class<?>>() {
        {
            put("id", int.class);
            put("name", String.class);
        }
    };

    public TagsOrm() {
        super(TABLE_NAME, TABLE_PK_NAME, TABLE_COLUMNS, TABLE_RELATED);
    }
}
