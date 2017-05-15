package model;

import java.util.ArrayList;

public class Model {
    protected int id; // PK
    // JOINされた際に格納される他テーブルのモデル
    protected ArrayList<Object> models = new ArrayList<Object>();


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setModel(Object model) {
        this.models.add(model);
    }

    public ArrayList<Object> getModels(){
        return this.models;
    }


    // JOINされた際に格納される他テーブルのモデル
    /*
    protected Map<String, Model> models = new HashMap<String, Model>();

    public void setModel(String modelName, Model model){
        this.models.put(modelName, model);
    }

    public Map<String, Model> getModels(){
        return models;
    }
    /**/
}
