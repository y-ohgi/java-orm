package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.NEW;

import model.EmployeeModel;
import model.UserCharacterModel;
import orm.EmployeeOrm;
import orm.UserCharactersOrm;

public class Main {

    public static void main(String[] args) {
        UserCharacterModel chara = (UserCharacterModel)new UserCharactersOrm()
//                .where("user_id", "=", 1)
//                .and()
//                .where("current_character_flg", "=", 0)
//                .find();
                .find(8);
        s(chara.getId());
        s(chara.getName());
    }






    public static void s(Object o){
        System.out.println(o);
    }
}


