package main;

import java.util.ArrayList;

import model.Model;
import model.UserCharacterModel;
import orm.UsersOrm;

public class Main {

    public static void main(String[] args) {
//        UserCharacterModel chara = (UserCharacterModel)new UserCharactersOrm()
////                .where("user_id", "=", 1)
////                .and()
////                .where("current_character_flg", "=", 0)
////                .find();
//                .find(8);
//        s(chara.getId());
//        s(chara.getName());
//
//        s(chara.getClass().getSimpleName());

//        ArrayList<Model> charas = (ArrayList<Model>)new UserCharactersOrm()
//                .leftJoin("tag_id", "Tags")
//                .select();

//        s(((UserCharacterModel)charas.get(0)).getName());
//        s(((UserCharacterModel)charas.get(1)).getName());
//        s(((UserCharacterModel)charas.get(2)).getName());
//        UserCharacterModel chara = (UserCharacterModel)charas.get(0);
//        s(chara.getName());
//        s( ((TagModel)chara.getModels().get(0)).getName() );
//
        ArrayList<Model> users = (ArrayList<Model>)new UsersOrm()
                .rightJoin("user_id", "UserCharacters")
                .select();

        ArrayList<Object> models = users.get(0).getModels();

        s( ((UserCharacterModel)models.get(0)).getName() );




    }






    public static void s(Object o){
        System.out.println(o);
    }
}


