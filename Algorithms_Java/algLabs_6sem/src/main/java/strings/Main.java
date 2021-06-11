package strings;

public class Main {
    public static void main(String[] args) {
        String pat = "pat";
        String txt = "find pat in str with pat and patpat";
        KMP kmp = new KMP();
        kmp.searchAll(pat, txt);
        System.out.println(kmp.getCounter());
    }
}
