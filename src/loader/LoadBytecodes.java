package loader;

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class LoadBytecodes {
    public Stack<String> stack = new Stack<>();
    private ArrayList<String> list = new ArrayList<>();
    public int sp = -1;
    public int pc = 0;
    Utils utils = new Utils();

    public LoadBytecodes(String codefilename) {
        try {
            FileInputStream fis = new FileInputStream(codefilename);
            InputStreamReader isr = new InputStreamReader(fis, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                line = utils.binstrToString(line);
                list.add(line);
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPCommand() {
        return list.get(this.pc);
    }


    public int getListsize() {
        return this.list.size();
    }

}
