package orm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import model.Model;

public abstract class Orm {
    protected static String TABLE_NAME; // テーブル名
    protected static String TABLE_PK_NAME = "id"; // PRIMARY KEYのカラム名
    protected static Map<String, Class<?>> TABLE_COLUMNS; // テーブルのカラムを登録
    protected static Map<String, String[]> TABLE_RELATED; // 外部キー情報を登録

    protected String sql = ""; // 実行するSQL
    protected String targetColumnsSql = ""; // 取得対象のカラム
    protected ArrayList<String> targetColumns = new ArrayList<String>();
    protected String clauseSql = " WHERE "; // 使用する句の登録
    protected ArrayList<Object> clauseValues = new ArrayList<Object>(); // プリペアードステートメントで登録する値
    protected String joinSql = ""; // JOIN句の格納
    protected String joinTable = ""; // JOIN対象のテーブル名

    protected int limit = -1; // レコードの取得件数
    protected int offset = -1; // レコード取得開始位置

    Orm(String tableName, String tablePkName, Map<String, Class<?>> tableColumns, Map<String, String[]> tableRelated) {
        TABLE_NAME = tableName;
        TABLE_PK_NAME = tablePkName;
        TABLE_COLUMNS = tableColumns;
        TABLE_RELATED = tableRelated;
    }

    /***
     * TABLE_COLUMNSの取得
     *
     * @return
     */
    public static Map<String, Class<?>> getTableColumns(){
        return TABLE_COLUMNS;
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
        //TODO: sql生成用メソッド作る
        sql = "SELECT " +genTargetColumnsSqlFromCurrentTable()+ " FROM " + TABLE_NAME + " ";
        if(joinSql != "") sql += joinSql;

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
            clauseSql += "AND id = ? ";
        }else{
            clauseSql += " id = ? ";
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
        clauseSql += column + " " + operator + " ? ";
        clauseValues.add(value);
        return this;
    }

    /***
     * OR条件の追加
     *
     * @return
     */
    public Orm or() {
        clauseSql += " OR ";
        return this;
    }

    /***
     * AND条件の追加
     *
     * @return
     */
    public Orm and() {
        clauseSql += " AND ";
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
        clauseSql += " IS NULL " + column + " ";
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
        clauseSql += " IS NOT NULL " + column + " ";
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
        clauseSql += " " + column +  " IN (";
        for (int i = 0; i < values.length; i++) {
            if(i != 0) clauseSql+=", ";
            clauseSql += "?";
            clauseValues.add(values[i]);
        }
        clauseSql += ") ";
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
        clauseSql += column + " LIKE ? ";
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
        clauseSql += " BETWEEN ? AND ? ";
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
     * @return
     */
    public Orm leftJoin(String column, String table) {
        //TODO: throwする
        if (isValidColumn(column) == false)
            return this;
        joinSql = " LEFT JOIN `"+table+"` ON `"+TABLE_NAME+"`.`"+column+"` = `"+table+"`.`id` ";
        joinTable = table;

        joinColumnBy(table);

        return this;
    }

    /**
     * RIGHT JOIN を行う
     *
     * @param column
     * @param table
     * @return
     */
    public Orm rightJoin(String column, String table) {
        //TODO: throwする
        //TODO: 別テーブルの isValidColum
//        if (isValidColumn(column) == false)
//            return this;
        joinSql = " RIGHT JOIN `"+table+"` ON `"+TABLE_NAME+"`.`id` = `"+table+"`.`"+column+"` ";
        joinTable = table;

        joinColumnBy(table);

        return this;
    }


    /***
     * 指定したテーブルのカラムを取得可能にする
     *
     * @param table
     */
    private void joinColumnBy(String table){
        System.out.println("---------------");
        try {
            Class<?> clazz = Class.forName("orm."+table+"Orm");
            Map<String, Class<?>> columns = (Map<String, Class<?>>) clazz.getDeclaredField("TABLE_COLUMNS").get(clazz);

            //TODO: firstflgはなおす or steram化
            boolean firstflg = true;
            for(Map.Entry<String, Class<?>> col : columns.entrySet()){
                targetColumnsSql += " `"+table+"`.`"+col.getKey()+"` AS '"+table+"."+col.getKey()+"', ";
            }

        } catch (ClassNotFoundException  | SecurityException | IllegalAccessException | IllegalArgumentException  | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


    /***
     * テーブルネームからModelのクラス名を生成
     *
     * @return
     */
    private String genModelClassNameFromTableName(String tableName) {
        String tname = tableName.substring(0, tableName.length() - 1);
        return "model." + tname.substring(0, 1).toUpperCase() + tname.substring(1) + "Model";
    }

    private String convSnakeCaseToUpperCase(String str) {
        String res = "";
        for (String s : str.split("_")) {
            res += s.substring(0, 1).toUpperCase() + s.substring(1);
        }
        return res;
    }

    /***
     * SQLの実行とModelの取得
     *
     * @return
     */
    private ArrayList<Model> fetchModels() {
        ArrayList<Model> models = new ArrayList<Model>();

        try {
            if (clauseValues.size() > 0) {
                sql += clauseSql;
            }

            // プリペアドステートメントの実行
            PreparedStatement stmt = Dbh.get().prepareStatement(sql);

            // 変数の格納
            if (clauseValues.size() > 0) {
                stmt = rpegisterClause(stmt);
            }

            // SQLの実行
            ResultSet rs = stmt.executeQuery();

            ArrayList<Integer> createdIds = new ArrayList<Integer>(); // 作成済みのレコードのidを格納
            // TODO: ここらへんから取得したレコードをモデルに格納する処理。メソッド分ける
            String modelName = genModelClassNameFromTableName(TABLE_NAME);
            while (rs.next()) {
                Class<?> clazz = Class.forName(modelName);
                Object model = null;
                if(!createdIds.contains(rs.getInt(TABLE_NAME+".id"))){
                    model = clazz.newInstance();
                    for (Map.Entry<String, Class<?>> e : TABLE_COLUMNS.entrySet()) {
                        Method method = null;

                        switch (e.getValue().toString()) {
                        case "class java.lang.String":
                            method = clazz.getDeclaredMethod("set" + convSnakeCaseToUpperCase(e.getKey()), e.getValue());
                            method.invoke(model, rs.getString(TABLE_NAME+"."+e.getKey()));
                            break;
                        default:
                          //XXX: intの型名が取得できないため、defaultでキャッチ
                            method = clazz.getDeclaredMethod("set" + convSnakeCaseToUpperCase(e.getKey()), e.getValue());
                            method.invoke(model, rs.getInt(TABLE_NAME+"."+e.getKey()));
                            break;
                        }
                    }
                    models.add((Model) model);
                }

                if(joinTable != ""){
                    model = models.stream().filter(m -> {
                        try {
                            return m.getId() == rs.getInt(TABLE_NAME+".id");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        return false;
                    }).findFirst();
                    model = ((Optional)model).orElse(null);

                    // TODO: ここでJOINされたテーブルの結合を行う
                    clazz = Class.forName("orm."+joinTable+"Orm");
                    Map<String, Class<?>> columns = (Map<String, Class<?>>) clazz.getDeclaredField("TABLE_COLUMNS").get(clazz);


                    // TABLE_NAMEとTABLE_COLUMNSをもとに rs.getXxx()を行う
                    clazz = Class.forName(genModelClassNameFromTableName(joinTable));
                    Object subModel = clazz.newInstance();
                    for (Map.Entry<String, Class<?>> e : columns.entrySet()) {
                        Method method = null;

                        switch (e.getValue().toString()) {
                        case "class java.lang.String":
                            method = clazz.getDeclaredMethod("set" + convSnakeCaseToUpperCase(e.getKey()), e.getValue());
                            method.invoke(subModel, rs.getString(joinTable+"."+e.getKey()));
                            break;
                        default:
                          //XXX: intの型名が取得できないため、defaultでキャッチ
                            method = clazz.getDeclaredMethod("set" + convSnakeCaseToUpperCase(e.getKey()), e.getValue());
                            method.invoke(subModel, rs.getInt(joinTable+"."+e.getKey()));
                            break;
                        }
                    }

                    // modelへ格納
                    ((Model) model).setModel(subModel);
                }

            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException
                | SecurityException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        return models;
    }

    /***
     * SQLの?へ値の登録
     *
     * @param stmt
     * @return
     */
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

    /**
     * TABLE_COLUMNSとtargetColumnsを元に 取得するカラムを指定するための文字列を生成する
     *
     * @return
     */
    protected String genTargetColumnsSqlFromCurrentTable(){
        String columnsSql = " " + targetColumnsSql + " ";
        try {
            //TODO: firstflgはなおす
            boolean firstflg = true;
            for(Map.Entry<String, Class<?>> col : TABLE_COLUMNS.entrySet()){
                if(firstflg){
                    firstflg = false;
                }else{
                    columnsSql += " , ";
                }
                columnsSql += " `"+TABLE_NAME+"`.`"+col.getKey()+"` AS '"+TABLE_NAME+"."+col.getKey()+"' ";
            }

        } catch (SecurityException | IllegalArgumentException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }

        return columnsSql;
    }

    protected boolean isValidColumn(String column) {
        return TABLE_COLUMNS.entrySet().stream().anyMatch(e -> e.getKey() == column);
    }
}
