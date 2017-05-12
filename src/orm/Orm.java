package orm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import model.EmployeeModel;
import model.Model;

abstract class Orm {
    protected String TABLE_NAME; // テーブル名
    protected String TABLE_PK_NAME = "id"; // PRIMARY KEYのカラム名
    protected String[] TABLE_COLUMNS; // テーブルのカラムを登録
    protected Map<String, String> TABLE_RELATED; // 外部キー情報を登録

    protected String sql = ""; // 実行するSQL
    protected ArrayList<String> clauses = new ArrayList<String>(); // 使用する句の登録
    protected ArrayList<Object> clauseValues = new ArrayList<Object>(); // プリペアードステートメントで登録する値

    /***
     * TODO
     *   sql実行メソッド
     *   カラム内に存在するか確認メソッド
     *   リフレクション実行用メソッド
     */

    /***
     * レコードの取得
     *
     * @return
     */
    public ArrayList<Model> select() {
        ArrayList<Model> models = new ArrayList<Model>();
        String sql = "SELECT * FROM employee";

        try {
            PreparedStatement stmt = Dbh.get().prepareStatement(sql);
            //stmt.setString(i + 1, sqlWhereValues.get(i));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                models.add(new EmployeeModel(
                        rs.getInt("id_employee"),
                        rs.getString("nm_employee"),
                        rs.getString("kn_employee"),
                        rs.getString("mail_address"),
                        rs.getString("password"),
                        rs.getInt("id_department"),
                        rs.getInt("age")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 1件取得
     *
     * @return
     */
    public Model find() {
        return null;
    }

    /***
     * idを指定して1件取得
     *
     * @param id
     * @return
     */
    public Model find(int id) {
        System.out.println(this.getClass());
        System.out.println("find(id)");
        return null;
    }

    /***
     * 取得したコードの合計を返す
     *
     * @return
     */
    public int count() {
        return 0;
    }

    /***
     * レコードの挿入
     *
     * @param model
     * @return
     */
    public int insert(Model model) {
        return -1;
    }

    /***
     * レコードの更新
     */
    public void update() {

    }

    /***
     * 更新する値の設定
     *
     * @param column
     * @param value
     * @return
     */
    public Orm value(String column, String value) {
        return this;
    }

    /***
     * 更新する値の設定
     *
     * @param column
     * @param value
     * @return
     */
    public Orm value(String column, int value) {
        return this;
    }

    /***
     * レコードの削除
     */
    public void delete() {

    }

    /***
     * 検索条件の追加
     *
     * @param column
     *            テーブルのカラム名
     * @param operator
     *            演算子
     * @param value
     *            値
     * @return
     */
    public Orm where(String column, String operator, int value) {
        return this;
    }

    /***
     * 検索条件の追加
     *
     * @param column
     *            テーブルのカラム名
     * @param operator
     *            演算子
     * @param value
     *            値
     * @return
     */
    public Orm where(String column, String operator, String value) {
        clauses.add(column);
        clauseValues.add(value);
        return this;
    }

    /***
     * 指定したカラムがNULLかの判定
     *
     * @param column
     * @return
     */
    public Orm isNull(String column) {
        return this;
    }

    /***
     * 指定したカラムがNULLではないかの判定
     *
     * @param column
     * @return
     */
    public Orm isNotNull(String column) {
        return this;
    }

    /***
     * valuesで渡した配列に1つでもマッチするレコードを取得
     *
     * @param column
     *            テーブルのカラム名
     * @param values
     * @return
     */
    public Orm in(String column, int[] values) {
        return this;
    }

    /***
     * valuesで渡した配列に1つでもマッチするレコードを取得
     *
     * @param column
     *            テーブルのカラム名
     * @param values
     * @return
     */
    public Orm in(String column, String[] values) {
        return this;
    }

    /***
     * あいまい検索
     *
     * @param column
     *            テーブルのカラム名
     * @param values
     * @return
     */
    public Orm like(String column, String values) {
        return this;
    }

    /***
     * 指定した範囲内に含まれているレコードを全県取得
     *
     * @param column
     *            テーブルのカラム名
     * @param start
     *            開始値
     * @param end
     *            終了値
     * @return
     */
    public Orm between(String column, String start, String end) {
        return this;
    }

    /***
     * 指定した範囲内に含まれているレコードを全県取得
     *
     * @param column
     *            テーブルのカラム名
     * @param start
     *            開始値
     * @param end
     *            終了値
     * @return
     */
    public Orm between(String column, int start, int end) {
        return this;
    }

    /***
     * 並び替えを行う
     *
     * @param column
     *            テーブルのカラム名
     * @return
     */
    public Orm orderBy(String column) {
        return this;
    }

    /***
     * 昇順で並び替える
     *
     * @return
     */
    public Orm asc() {
        return this;
    }

    /***
     * 降順で並び替える
     *
     * @return
     */
    public Orm desc() {
        return this;
    }

    /***
     * レコードの最大取得件数を指定
     *
     * @param limit
     * @return
     */
    public Orm limit(int limit) {
        return this;
    }

    /***
     * レコードの取得開始位置を指定し、最大取得件数を指定
     *
     * @param limit
     * @return
     */
    public Orm limit(int limit, int offset) {
        return this;
    }

    /***
     * レコードの取得開始位置を指定
     *
     * @param offset
     * @return
     */
    public Orm offset(int offset) {
        return this;
    }

}
