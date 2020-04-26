package test;

public class StringTest {
    public static void main(String[] args) {
        String str = "123125126";
//        String a = str.concat("456");
        String a = str.replace("1234", "ab");
        System.out.println(a);
    }
}
