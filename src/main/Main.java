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
                .where("name", "=", "a_さぶ")
                .find();
        s(chara.getId());
        s(chara.getName());
    }






    public static void s(Object o){
        System.out.println(o);
    }
}


