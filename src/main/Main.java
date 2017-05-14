package main;

import java.util.Arrays;

import com.sun.org.apache.bcel.internal.generic.NEW;

import model.EmployeeModel;
import model.UserCharacterModel;
import orm.EmployeeOrm;
import orm.UserCharactersOrm;

public class Main {

    public static void main(String[] args) {
//        UserCharacterModel employee = (UserCharacterModel) new UserCharactersOrm().find();
//        s( employee );

        //new UserCharactersOrm().leftJoin("user_id", "Users", 1).select();
        // new UserCharactersOrm().leftJoin(column, table, id)

        UserCharacterModel chara = (UserCharacterModel) new UserCharactersOrm().find();
        s(chara.getId());
        s(chara.getName());
        s(chara.getAge());
    }






    public static void s(Object o){
        System.out.println(o);
    }

    // public static void name(ArrayList<Integer> values) {
    public static void ary(int[] values) {
        System.out.println(Arrays.toString(values));
    }

}


