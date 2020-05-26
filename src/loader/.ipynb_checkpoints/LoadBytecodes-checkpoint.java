package loader;

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class LoadBytecodes {
    public Stack<String> stack = new Stack<>();
    private ArrayList<String> list = new ArrayList<>();
    public int sp = -1;
    public int pc = 0;


    /**
     * 构造装载器
     *
     * @param codefilename
     *
     * @author AmberZDH
     */
    public LoadBytecodes(String codefilename) {
        try {
            DataInputStream re=new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream(codefilename)));
//            FileInputStream fis = new FileInputStream(codefilename);
//            InputStreamReader isr = new InputStreamReader(fis, "utf-8");//读入文件
//            BufferedReader br = new BufferedReader(fis);
            String line;

            while ((line = re.readLine()) != null) {
                line=line.replaceAll("\u0000","");
                list.add(line);
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取pc所指的command
     *
     * @author AmberZDH
     */
    public String getPCommand() {
        return list.get(this.pc);
    }

    /**
     * 获取操作列表
     *
     * @author AmberZDH
     */
    public int getListsize() {
        return this.list.size();
    }

    public static void main(String args[]) {

        LoadBytecodes load = new LoadBytecodes("test.code");
    }
}
