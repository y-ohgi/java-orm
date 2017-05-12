package main;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {



        /*
        Map<String, String[]> TABLE_RELATED = new HashMap<String, String[]>() {
            {
                String[] rel = {"depertment", "DepertmentModel"};
                put("id_depertment", rel);
            }
        };

        s(TABLE_RELATED.get("id_depertment")[1]);

        /*
        //s(String.class.getName());
        Object typeS = String.class;
        Class<?> typeI = int.class;


        try {
            Class type = Class.forName("int");
            Class<?> clazz = Class.forName("orm.EmployeeOrm");
            Method method = clazz.getDeclaredMethod("find", type);

            s( method.invoke(clazz.newInstance(), 1) );

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        /**/
    }








    public static void s(Object o){
        System.out.println(o);
    }

    // public static void name(ArrayList<Integer> values) {
    public static void ary(int[] values) {
        System.out.println(Arrays.toString(values));
    }

}


