package main;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String res = "";
        String str = "id_depertment";
        String[] strs = str.split("_");

        //String[] ary = (String[]) Arrays.stream(strs).map(s -> s.substring(0, 1).toUpperCase() + s.substring(1)).toArray();

        for(String s : strs){
            res += s.substring(0, 1).toUpperCase() + s.substring(1);
        }

        s(res);

    }








    public static void s(Object o){
        System.out.println(o);
    }

    // public static void name(ArrayList<Integer> values) {
    public static void ary(int[] values) {
        System.out.println(Arrays.toString(values));
    }

}
