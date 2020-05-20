package convertSrcToBytecodes;

import java.io.*;

public class ConvertSrcToBytecodes {

    private String srcfilepath;

    public ConvertSrcToBytecodes(String srcfilepath) {
        this.srcfilepath = srcfilepath;
    }

    private BufferedReader readSrc() {
        try {
            FileReader reader = new FileReader(this.srcfilepath);
            return new BufferedReader(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String converBtype(String str) {
        char[] strlist = str.toCharArray();
        String line = "";
        for (int i = 0; i < strlist.length; i++) {
            String new_line = Integer.toBinaryString(strlist[i]);
//            if (new_line.length() != 8) {
//                for (int j = 0; j <= (8 - new_line.length()); j++) {
//                    new_line = "0" + new_line;
//                }
//            }
            line = line + new_line + " ";
        }

        return line;
    }

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

   
    public void convert() {
        BufferedReader br = readSrc();
        String line = "";
        File file = new File(this.srcfilepath.replace(".txt", ".woc"));
        try {
            file.createNewFile();


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

}
