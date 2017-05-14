package orm;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import com.sun.crypto.provider.RSACipher;
import com.sun.javafx.sg.prism.NGShape.Mode;
import com.sun.media.sound.ModelSource;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import jdk.nashorn.internal.objects.annotations.Where;
import model.EmployeeModel;
import model.Model;
import sun.reflect.Reflection;

public abstract class Orm {
    protected static String TABLE_NAME; // テーブル名
    protected static String TABLE_PK_NAME = "id"; // PRIMARY KEYのカラム名
    protected static Map<String, Class<?>> TABLE_COLUMNS; // テーブルのカラムを登録
    protected static Map<String, String[]> TABLE_RELATED; // 外部キー情報を登録

    protected String sql = ""; // 実行するSQL
    // protected ArrayList<String> clauses = new ArrayList<String>(); //
    // 使用する句の登録
    protected String clause = " WHERE "; // 使用する句の登録
    protected ArrayList<Object> clauseValues = new ArrayList<Object>(); // プリペアードステートメントで登録する値
    protected int limit = -1; // レコードの取得件数
    protected int offset = -1; // レコード取得開始位置

    Orm(String tableName, String tablePkName, Map<String, Class<?>> tableColumns, Map<String, String[]> tableRelated) {
        TABLE_NAME = tableName;
        TABLE_PK_NAME = tablePkName;
        TABLE_COLUMNS = tableColumns;
        TABLE_RELATED = tableRelated;
    }

    /***
     * TODO sql実行メソッド カラム内に存在するか確認メソッド リフレクション実行用メソッド
     */

    /***
     * レコードの取得
     *
     * @return
     */
    public ArrayList<Model> select() {
        sql = "SELECT * FROM " + TABLE_NAME + " ";

        ArrayList<Model> models = fetchModels();

        return models;
    }

    /***
     * 1件取得
     *
     * @return
     */
    public Model find() {
        // TODO: limit 1 を差し込む

        // TODO: カラムを*じゃなくまともな感じにする。
        // 可能であれば登場順に a, b, cとかでASしてやる
        // ex. SELECT `a`.`id` AS `a_id`, ... FROM `UserCharacters` AS `a`
        // SQL生成
        // 取得するカラムの決定
        // 句とかの結合
        // COUNT関数だったらおとなしく実行
        // JOINとか考えねば
        // LIKEでいいのでは
        sql = "SELECT * FROM " + TABLE_NAME + " ";

        ArrayList<Model> models = fetchModels();

        return models.get(0);
    }

    /***
     * idを指定して1件取得
     *
     * @param id
     * @return
     */
    public Model find(int id) {
        if(clauseValues.size() > 0){
            clause += "AND id = ? ";
        }else{
            clause += " id = ? ";
        }
        clauseValues.add(id);
        
        sql = "SELECT * FROM " + TABLE_NAME;

        ArrayList<Model> models = fetchModels();

        return models.get(0);
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
    public Orm where(String column, String operator, Object value) {
        // TODO: throwする
        // TODO: intとString以外は弾く
        if (isValidColumn(column) == false)
            return this;
        clause += column + " " + operator + " ? ";
        clauseValues.add(value);
        return this;
    }

    /***
     * OR条件の追加
     * 
     * @return
     */
    public Orm or() {
        clause += " OR ";
        return this;
    }

    /***
     * AND条件の追加
     * 
     * @return
     */
    public Orm and() {
        clause += " AND ";
        return this;
    }

    /***
     * 指定したカラムがNULLかの判定
     *
     * @param column
     * @return
     */
    public Orm isNull(String column) {
        if(isValidColumn(column) == false) return this;
        clause += " IS NULL " + column + " ";
        return this;
    }

    /***
     * 指定したカラムがNULLではないかの判定
     *
     * @param column
     * @return
     */
    public Orm isNotNull(String column) {
        if(isValidColumn(column) == false) return this;
        clause += " IS NOT NULL " + column + " ";
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
    public Orm in(String column, Object[] values) {
        // TODO:throw
        if(isValidColumn(column) == false) return this;
        clause += " " + column +  " IN (";
        for (int i = 0; i < values.length; i++) {
            if(i != 0) clause+=", ";
            clause += "?";
            clauseValues.add(values[i]);
        }
        clause += ") ";
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
    public Orm like(String column, String value) {
        // TODO:throw
        if(isValidColumn(column) == false) return this;
        clause += column + " LIKE ? ";
        clauseValues.add(value);
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
    public Orm between(String column, Object start, Object end) {
        if(isValidColumn(column) == false) return this;
        clause += " BETWEEN ? AND ? ";
        clauseValues.add(start);
        clauseValues.add(end);
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
        this.limit = limit;
        return this;
    }

    /***
     * レコードの取得開始位置を指定し、最大取得件数を指定
     *
     * @param limit
     * @return
     */
    public Orm limit(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
        return this;
    }

    /***
     * レコードの取得開始位置を指定
     *
     * @param offset
     * @return
     */
    public Orm offset(int offset) {
        this.offset = offset;
        return this;
    }

    /**
     * LEFT JOIN を行う
     * 
     * @param column
     * @param table
     * @param id
     * @return
     */
    public Orm leftJoin(String column, String table, int id) {
        // XXX: どうやって親テーブルに張り付いている小テーブルと認識させるかが問題. lambda
        // XXX: select()もしくはfind()で実行できるようにする必要
        return this;
    }

    /***
     * テーブルネームからModelのクラス名を生成
     * 
     * @return
     */
    private String genModelClassNameFromTableName() {
        String tname = TABLE_NAME.substring(0, TABLE_NAME.length() - 1);
        return "model." + tname.substring(0, 1).toUpperCase() + tname.substring(1) + "Model";
    }

    private String convSnakeCaseToUpperCase(String str) {
        String res = "";
        for (String s : str.split("_")) {
            res += s.substring(0, 1).toUpperCase() + s.substring(1);
        }
        return res;
    }

    private ArrayList<Model> fetchModels() {
        ArrayList<Model> models = new ArrayList<Model>();

        try {
            if (clauseValues.size() > 0) {
                sql += clause;
            }
            
            // プリペアドステートメントの実行
            PreparedStatement stmt = Dbh.get().prepareStatement(sql);

            // 変数の格納
            if (clauseValues.size() > 0) {
                stmt = rpegisterClause(stmt);
            }

            // SQLの実行
            ResultSet rs = stmt.executeQuery();

            // TODO: ここらへんから取得したレコードをモデルに格納する処理。メソッド分ける
            String modelName = genModelClassNameFromTableName();
            Class<?> clazz = Class.forName(modelName);
            while (rs.next()) {
                Object model = clazz.newInstance();

                for (Map.Entry<String, Class<?>> e : TABLE_COLUMNS.entrySet()) {
                    Method method = null;

                    switch (e.getValue().toString()) {
                    case "class java.lang.String":
                        method = clazz.getDeclaredMethod("set" + convSnakeCaseToUpperCase(e.getKey()), e.getValue());
                        method.invoke(model, rs.getString(e.getKey()));
                        break;
                    default:
                      //XXX: intの型名が取得できないため、defaultでキャッチ
                        method = clazz.getDeclaredMethod("set" + convSnakeCaseToUpperCase(e.getKey()), e.getValue());
                        method.invoke(model, rs.getInt(e.getKey()));
                        break;
                    }
                }

                // TODO: ここでJOINされたテーブルの結合を行う

                models.add((Model) model);
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException
                | SecurityException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return models;
    }

    protected PreparedStatement rpegisterClause(PreparedStatement stmt) {
        try {
            for (int i = 0; i < clauseValues.size(); i++) {
                switch (clauseValues.get(i).getClass().toString()) {
                case "class java.lang.String":
                    stmt.setString(i + 1, (String) clauseValues.get(i));
                    break;
                default:
                    //XXX: intの型名が取得できないため、defaultでキャッチ
                    stmt.setInt(i + 1, (int) clauseValues.get(i));
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;

    }

    protected boolean isValidColumn(String column) {
        return TABLE_COLUMNS.entrySet().stream().anyMatch(e -> e.getKey() == column);
    }
}
