package orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbh {
    private static Connection con;

    /**
     * データベースハンドラ取得用メソッド
     *
     * @return
     */
    public static Connection get() {
        if(con == null){
            try {
                con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/narikiru", "root", "");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return con;
    }

    /**
     * データベースハンドラ切断用メソッド
     */
    public static void close(){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
