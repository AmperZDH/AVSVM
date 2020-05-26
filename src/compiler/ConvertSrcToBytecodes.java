package compiler;

import java.io.*;

public class ConvertSrcToBytecodes {

    private String srcfilepath;

    public ConvertSrcToBytecodes(String srcfilepath) {
        this.srcfilepath = srcfilepath;
    }

    /**
     * 读取源文件
     *
     * @return BufferedReader
     */
    private BufferedReader readSrc() {
        try {
            FileReader reader = new FileReader(this.srcfilepath);
            return new BufferedReader(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 将命令和参数转换为字节码
     *
     * @param str
     * @return byte
     * @author AmberZDH
     */
    private String regCommand(String str) {
        int length = str.length();
        String command = "";
        String value = "";
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) == ' ') {
                continue;
            } else if (str.charAt(i) == '0' || str.charAt(i) == '1'
                    || str.charAt(i) == '2' || str.charAt(i) == '3'
                    || str.charAt(i) == '4' || str.charAt(i) == '5'
                    || str.charAt(i) == '6' || str.charAt(i) == '7'
                    || str.charAt(i) == '8' || str.charAt(i) == '9') {
                value += str.charAt(i);

            } else {
                command += str.charAt(i);
            }
        }

        String val="";

        //补齐 并转为16进制
        if (!value.equals("")) {
            int ten_val = Integer.parseInt(value);
            val = Integer.toHexString(ten_val);
            for(int i =0 ;i<=5-val.length();i++){
                val="0"+val;
            }
        }


        switch (command) {

            case "PUSH":
                return "PUSH" + " " + val;
            case "IFEQ":
                return "IFEQ" + " " + val;
            case "IFNE":
                return "IFNE" + " " + val;
            case "POP":
                return "POP";
            case "DUP":
                return "DUP";
            case "SWAP":
                return "SWAP";
            case "ADD":
                return "ADD";
            case "SUB":
                return "SUB";
            case "NOP":
                return "NOP";
            case "HALT":
                return "HALT";
            default:
                System.out.println("Command ERROR!");
                break;
        }

        return null;

    }

    /**
     * 将文件转换为字节码文件
     *
     * @author AmberZDH
     */
    public void convert() {
        BufferedReader br = readSrc();
        String line = "";
        File file = new File(this.srcfilepath.replace(".txt", ".code"));
        try {
            file.createNewFile();

            //清空原文件
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();

            FileOutputStream fos = new FileOutputStream(file, true);
            DataOutputStream dos=new DataOutputStream(fos);

            while ((line = br.readLine()) != null) {
                line = regCommand(line);
//                fos.write((line + "\n").getBytes("utf-8"));
                dos.writeChars(line + "\n");
                dos.flush();
            }
            dos.close();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String args[]) {
        ConvertSrcToBytecodes con = new ConvertSrcToBytecodes("test.txt");
        con.convert();

    }

}
