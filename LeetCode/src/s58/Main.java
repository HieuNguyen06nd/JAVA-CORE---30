package s58;

public class Main {
    public static void main(String[] args) {
        String s = "hello word";

        System.out.println(lengthOfLastWord(s));

    }

    public static int lengthOfLastWord(String s) {

        s =s.trim();
        int n =0;
        for (int i = s.length()-1; i >=0 ; i--) {
            if (s.charAt(i) != ' '){
                n++;
            }else if (n>0){
                break;
            }
        }
        return n;

    }
}
