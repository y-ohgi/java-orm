package orm;

import java.util.HashMap;
import java.util.Map;

public class EmployeeOrm extends Orm{
    protected String TABLE_NAME = "employee"; // テーブル名
    // テーブルのカラムを登録
    protected String[] TABLE_COLUMNS = {
            "id_employee",
            "nm_employee",
            "kn_employee",
            "mail_address",
            "password",
            "id_department",
            "age"
    };
    // 外部キー情報を登録
    protected Map<String, String> TABLE_RELATED = new HashMap<String, String>() {
        {
            put("id_depertment", "depertment");
        }
    };
}
