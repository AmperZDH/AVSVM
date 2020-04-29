package loader;

public class Utils {
    /**
     * 二进制转字符
     *
     * @param word
     * @return
     */
    private char binstrTochar(String word) {
        char[] temp = word.toCharArray();
        int[] result = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            result[i] = temp[i] - 48;
        }

        int sum = 0;
        for (int i = 0; i < result.length; i++) {
            sum += result[result.length - 1 - i] << i;
        }
        return (char) sum;
    }



    /**
     * 二进制转String
     *
     * @param line
     * @return
     */
    public String binstrToString(String line) {
        String[] line_list = line.split(" ");
        String new_line = "";
        for (int i = 0; i < line_list.length; i++) {
            new_line += binstrTochar(line_list[i]);
        }
        return new_line;
    }


}
