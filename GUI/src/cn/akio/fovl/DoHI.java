package cn.akio.fovl;

import java.io.*;

/**
 * 用于读出写入HI.txt文件中的最高分
 *
 * @author Akio
 * @Create 2021/7/31 17:04
 */
public class DoHI {
    public DoHI(){
        try {
            File file = new File("./src/cn/akio/fovl/statics/HI.txt");
            if (!file.exists()){
                file.createNewFile();
                PrintWriter pw = new PrintWriter(file, "UTF-8");
                pw.println("0");
                pw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void setHI(int score){
        try (
                FileOutputStream fos = new FileOutputStream("./src/cn/akio/fovl/statics/HI.txt");
                OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
                BufferedWriter bw = new BufferedWriter(osw);
                PrintWriter pw = new PrintWriter(bw, true);
                ) {
            pw.println(score+"");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getHI(){
        int HI = 0;
        try (
                FileInputStream fis = new FileInputStream("./src/cn/akio/fovl/statics/HI.txt");
                InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                ) {
            HI = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return HI;
    }
}
