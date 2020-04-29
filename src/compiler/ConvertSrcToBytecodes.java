package compiler;

import java.io.*;

public class ConvertSrcToBytecodes {

    private String srcfilepath;
//    private String bytefilepath;

    public ConvertSrcToBytecodes(String srcfilepath) {
        this.srcfilepath = srcfilepath;
//        this.bytefilepath = bytefilepath;
    }

    /**
     * 读取源文件
     *
     * @return BufferedReader
     */
    private BufferedReader readSrc() {
//        File filename = new File(this.srcfilepath);
        try {
            FileReader reader = new FileReader(this.srcfilepath);
            return new BufferedReader(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将String 转换为 字节码
     *
     * @param str
     * @return byte
     */
    private String converBtype(String str) {
        char[] strlist = str.toCharArray();
        String line = "";
        for (int i = 0; i < strlist.length; i++) {

//            line += Integer.toBinaryString(strlist[i]) + " ";
            //补齐8位
            String new_line = Integer.toBinaryString(strlist[i]);
            if (new_line.length() != 8) {
                for (int j = 0; j <= (8 - new_line.length()); j++) {
                    new_line = "0" + new_line;
                }
            }
            line = line + new_line + " ";
        }

        return line;
    }


    /**
     * 将命令和参数转换为字节码
     *
     * @param str
     * @return byte
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


        switch (command) {

            case "PUSH":
            case "IFEQ":
            case "IFNE":
                return command + value;
            case "POP":
            case "DUP":
            case "SWAP":
            case "ADD":
            case "SUB":
            case "NOP":
            case "HALT":
                return command;
            default:
                System.out.println("Command ERROR!");
                break;
        }

        return null;

    }

    /**
     * 将文件转换为字节码文件
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
            while ((line = br.readLine()) != null) {
                line = converBtype(regCommand(line));
                fos.write((line + "\n").getBytes("utf-8"));
                fos.flush();
            }
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String arg[]) {
//        ConvertSrcToBytecodes con = new ConvertSrcToBytecodes("test.txt");
//        con.convert();

    }

}
