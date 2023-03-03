package other;


import java.io.*;
import java.util.Objects;

public class RewriteFile {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/logic/Desktop/未命名文件夹");
        processFile(file);
    }

    public static void processFile(File file) throws IOException {
        // 如果
        if (file.isDirectory()) {
            for (File childFile :
                    Objects.requireNonNull(file.listFiles())) {
                processFile(childFile);
            }
        } else {
            if (file.getName().endsWith("html")) {
                rewriteFile(file);
            }
        }
    }

    public static void rewriteFile(File file) throws IOException {
        String filePath = file.getAbsolutePath();
        File newFile = new File(filePath + ".html");
        if (file.createNewFile()) {
            System.out.println("创建文件失败：" + filePath);
            return;
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile)));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains("<div class=\"_7Xrmrbox_0\">防止断更 请务必加首发微信：1716143665</div> ")) {
                line = line.replace("<div class=\"_7Xrmrbox_0\">防止断更 请务必加首发微信：1716143665</div> ", "");
            }

            bufferedWriter.write(line);
        }


        bufferedWriter.flush();
        bufferedReader.close();
        bufferedWriter.close();

    }
}