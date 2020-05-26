package engine;

import compiler.ConvertSrcToBytecodes;
import loader.LoadBytecodes;

import java.io.File;
import java.math.BigInteger;


public class Engine {
    private LoadBytecodes loadBytecodes;

    public Engine() {
        String codefilename = "";
        try {
            System.out.println("编译启动");
            codefilename = srcTocode();
            System.out.println("编译完成");
        } catch (Exception e) {
            System.out.println("ERROR编译失败!!");
        }

        try {
            System.out.println("开始装载");
            loadBytecodes = new LoadBytecodes(codefilename);
        } catch (Exception e) {
            System.out.println("ERROR装载失败!!");
        }

        try {
            System.out.println("指令执行\n");
            System.out.println("sp   运行时数据区");
            String command = "";
            while (!command.equals("HALT")) {
                command = this.loadBytecodes.getPCommand();
                parseCommand(command);
                System.out.println(this.loadBytecodes.sp + " --- " + this.loadBytecodes.stack.toString());
            }
            String result = this.loadBytecodes.stack.toString();
            System.out.println("执行结束\n" + result);
        } catch (Exception e) {
            System.out.println("ERROR指令执行失败!!");
        }
    }


    /**
     * 启动编译器
     *
     * @author AmberZDH
     */
    public String srcTocode() {
        File file = new File("./");
        File[] files_list = file.listFiles();
        String srcfilename = "";

        //寻找字节码文件
        for (int i = 0; i < files_list.length; i++) {
            if (files_list[i].isFile()) {
                if (files_list[i].getName().contains(".txt")) {
                    srcfilename = files_list[i].getPath();
                }
            }
        }
        ConvertSrcToBytecodes convertSrcToBytecodes = new ConvertSrcToBytecodes(srcfilename);
        convertSrcToBytecodes.convert();
        return srcfilename.replace(".txt", ".code");
    }

    /**
     * 执行指令
     *
     * @author AmberZDH
     */
    public void parseCommand(String line) {
        line=line.toString();
        int length = line.length();
        String[] arr = {"", ""};
        int p = 0;//指针 0为命令，1为数据，指arr

        for (int i = 0; i < length; i++) {
            if (line.charAt(i) == ' ') {
                p = p + 1;
                continue;
            }
            if (p==1 && line.charAt(i) == '0' || line.charAt(i) == '1'
                    || line.charAt(i) == '2' || line.charAt(i) == '3'
                    || line.charAt(i) == '4' || line.charAt(i) == '5'
                    || line.charAt(i) == '6' || line.charAt(i) == '7'
                    || line.charAt(i) == '8' || line.charAt(i) == '9'
                    ) {
                arr[p] += line.charAt(i);
            }else {
                arr[p] += line.charAt(i);
            }
        }
        String command = arr[0];
        String value = arr[1];

        //数据16转10
        if (!value.equals("")) {
            BigInteger bigint = new BigInteger(value, 16);
            int numb = bigint.intValue();
            value = String.valueOf(numb);
        }

        String top_value = "";
        String next_top_value = "";

        switch (command) {
            //PUSH
            case "PUSH":
                this.loadBytecodes.stack.push(value);
                this.loadBytecodes.sp += 1;
                this.loadBytecodes.pc += 1;
                break;
            //pop
            case "POP":
                top_value = this.loadBytecodes.stack.pop();
                this.loadBytecodes.sp -= 1;
                this.loadBytecodes.pc += 1;
                break;
            //DUP
            case "DUP":
                top_value = this.loadBytecodes.stack.get(this.loadBytecodes.sp);
                this.loadBytecodes.stack.push(top_value);
                this.loadBytecodes.sp += 1;
                this.loadBytecodes.pc += 1;
                break;
            //SWAP
            case "SWAP":
                top_value = this.loadBytecodes.stack.pop();
                next_top_value = this.loadBytecodes.stack.pop();
                this.loadBytecodes.stack.push(top_value);
                this.loadBytecodes.stack.push(next_top_value);
                this.loadBytecodes.pc += 1;
                break;
            //ADD
            case "ADD":
                top_value = this.loadBytecodes.stack.pop();
                next_top_value = this.loadBytecodes.stack.pop();
                int sum = Integer.parseInt(top_value) + Integer.parseInt(next_top_value);
                this.loadBytecodes.stack.push(String.valueOf(sum));
                this.loadBytecodes.sp -= 1;
                this.loadBytecodes.pc += 1;
                break;
            //SUB
            case "SUB":
                top_value = this.loadBytecodes.stack.pop();
                next_top_value = this.loadBytecodes.stack.pop();
                int sub = Integer.parseInt(next_top_value) - Integer.parseInt(top_value);
                this.loadBytecodes.stack.push(String.valueOf(sub));
                this.loadBytecodes.sp -= 1;
                this.loadBytecodes.pc += 1;
                break;
            //IFEQ
            case "IFEQ":
                top_value = this.loadBytecodes.stack.pop();
                this.loadBytecodes.sp -= 1;
                if (Integer.parseInt(top_value) == 0) {
                    this.loadBytecodes.pc += Integer.parseInt(value);
                    this.loadBytecodes.pc += 1;
                } else {
                    this.loadBytecodes.pc += 1;
                }
                break;
            //IFNE
            case "IFNE":
                top_value = this.loadBytecodes.stack.pop();
                this.loadBytecodes.sp -= 1;
                if (Integer.parseInt(top_value) != 0) {
                    this.loadBytecodes.pc += Integer.parseInt(value);
                    this.loadBytecodes.pc += 1;
                } else {
                    this.loadBytecodes.pc += 1;
                }
                break;
            //NOP
            case "NOP":
                this.loadBytecodes.pc += 1;
                break;


        }
    }

}
