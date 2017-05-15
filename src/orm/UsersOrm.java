package orm;

import java.util.HashMap;
import java.util.Map;

public class UsersOrm extends Orm{
    protected static String TABLE_NAME = "Users"; // テーブル名
    // テーブルのカラムを登録
    protected static Map<String, Class<?>> TABLE_COLUMNS = new HashMap<String, Class<?>>() {
        {
            put("id", int.class);
            put("email", String.class);
            put("password", String.class);
            put("token", String.class);
        }
    };

    public UsersOrm() {
        super(TABLE_NAME, TABLE_PK_NAME, TABLE_COLUMNS, TABLE_RELATED);
    }

}
