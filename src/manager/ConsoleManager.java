package manager;
import java.util.Scanner;

public class ConsoleManager{
    /**
     * コマンドプロンプト上で、ユーザに文字列を入力してもらい、その値を取得します
     *
     * @return    入力した文字列
     */
    @SuppressWarnings("resource")
    public static String inputString() {
        Scanner scanner = null;
        String result = "";

        while(true){
            try{
                scanner = new Scanner(System.in);
                result = scanner.nextLine();

                break;
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * 標準入力で数値のみを受け取り、intを返す
     * scanner.nextIntのラッパー
     *
     * @return 入力された数値
     */
    @SuppressWarnings("resource")
    public static int inputInt(){
        java.util.Scanner scanner = null;
        int num = 0;

        while(true){
            try{
                scanner = new java.util.Scanner(System.in);
                num = scanner.nextInt();

                break;
            }catch(Exception e){
                System.out.println("数字を入力してください");
            }
        }
        return num;
    }

    /**
     * 文字列をコンソールに出力
     *
     * @param str 出力対象の文字列
     */
    public static void output(String str) {
        System.out.print(str);
    }

}
