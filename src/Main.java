import java.io.*;

public class Main {
    public static void main(String[] args) {
        File file = new File("");
        String filePath = null;
        try {
            filePath = file.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String inputFile = filePath+"/src/in.txt";

        //从文件中读取数独内容
        BufferedInputStream bis = null;
        BufferedReader in = null;
        StringBuilder builder = new StringBuilder();
        try {
            bis = new BufferedInputStream(new FileInputStream(new File(inputFile)));
            in = new BufferedReader(new InputStreamReader(bis, "UTF-8"));

            while (in.ready()) {
                builder.append(in.readLine());
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //求解
        Model model = new Model(builder.toString());
        new Sudoku().deal(model);
    }
}
