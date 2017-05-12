package orm;

import java.util.HashMap;
import java.util.Map;

public class EmployeeOrm extends Orm{
    protected String TABLE_NAME = "employee"; // テーブル名
    // テーブルのカラムを登録
    protected Map<String, Class<?>> TABLE_COLUMNS = new HashMap<String, Class<?>>() {
        {
            put("id_depertment", int.class);
            put("nm_employee", String.class);
            put("kn_employee", String.class);
            put("mail_address", String.class);
            put("password", String.class);
            put("id_department", int.class);
            put("age", int.class);
        }
    };


    // 外部キー情報を登録
    protected Map<String, String[]> TABLE_RELATED = new HashMap<String, String[]>() {
        {
            String[] rel = {"depertment", "DepertmentModel"};
            put("id_depertment", rel);
        }
    };
}
