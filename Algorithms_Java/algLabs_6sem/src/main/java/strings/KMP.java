package strings;

public class KMP {
    private int counter = 1;

    void searchAll(String pat, String txt)
    {
        int M = pat.length();
        int N = txt.length();
        String str = "";


        int lps[] = new int[M];
        int j = 0;

        computeLPSArray(pat, M, lps);

        int i = 0;
        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                System.out.println(counter + ") Found pattern at index " + (i - j));
                for (int k = 0; k < (i-j); k++){
                    str += ' ';
                }
                str += txt.substring(i-j, i - j + M);
                System.out.println(txt);
                System.out.println(str);
                str = "";
                counter++;
                j = lps[j - 1];
            }

            else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
    }

    void computeLPSArray(String pat, int M, int lps[]) {
        int len = 0;
        int i = 1;
        lps[0] = 0;

        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }

    public int getCounter(){
        return counter;
    }
}

