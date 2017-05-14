package model;

import java.util.ArrayList;

public class Model {
    // JOINされた際に格納される他テーブルのモデル
    protected ArrayList<Model> models = new ArrayList<Model>();
    
    public void setModel(Model model) {
        this.models.add(model); 
    }
    
    public ArrayList<Model> getModels(){
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
