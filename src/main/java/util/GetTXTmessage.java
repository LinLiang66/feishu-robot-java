/**
 *
 * @ 这个目录是获取文件里面的内容的
 *
 *
 */
package util;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;




/**
 * @author wlk
 * @description 上传文件 --->从固定路径获取里面的内容
 * @date 0:02 2023/3/31
 * @return
 */
public class GetTXTmessage {
    public static void readFile(String strFile) {
        File f = new File("G:/ccc.txt");

        try {
            InputStream is = new FileInputStream(f);
            int iAvail = is.available();
            byte[] bytes = new byte[iAvail];
            is.read(bytes);
            System.out.println("文件内容:\n" + new String(bytes));

            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     * @description 上传文件--->并动态获取获取里面的内容
     * @author wlk
     * @date 0:02 2023/3/31
     */
    public void uploadFile(@RequestParam("uploadFile") MultipartFile multipartFile) {

        String type = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        //文件保存路径
        String filePath = "F:\\filepath";
        String fileName = String.valueOf(System.currentTimeMillis()) + type;
        File file = new File(filePath + File.separator + fileName);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            IOUtils.copy(multipartFile.getInputStream(), fileOutputStream);
            System.out.println("===========file upload success=======");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //读取文件
        try {
            System.out.println("输出" + file);
            InputStream is = new FileInputStream(file);
            int iAvail = is.available();
            byte[] bytes = new byte[iAvail];
            is.read(bytes);
            System.out.println("文件内容:\n" + new String(bytes));

            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}



