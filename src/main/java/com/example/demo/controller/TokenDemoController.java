//package com.example.demo.controller;
//
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.example.demo.config.ErrorResolve.FrontResult;
//import com.example.demo.config.ErrorResolve.ResultCodeEnum;
//import com.example.demo.config.ErrorResolve.ResutlMsgEnum;
//import com.example.demo.entity.Files;
//import com.example.demo.mapper.FilesMapper;
//import com.example.demo.service.FilesService;
//import io.swagger.annotations.Api;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.io.IOUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import util.JwtUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.*;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/userg")
//@Slf4j
//@Api(value = "/file", tags = "解析文档成字符串")
//public class TokenDemoController {
//
//    @Autowired
//    FilesService filesService;
//    @Autowired
//    FilesMapper filesMapper;
//
//    /**
//     * @return
//     * @description 获取文件--->内容
//     * @author wlk
//     * @date 0:02 2022/4/1
//     */
//    @PostMapping("/getmessage")
//    public FrontResult uploadFile(@RequestParam("uploadFile") MultipartFile multipartFile) {
//        HashMap<Object, Object> aa = new HashMap<>();
//        System.out.println("ssssssssssssss" + multipartFile);
//        String type = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
//        //文件保存路径
//        String filePath = "G:\\bishe/image";
//        String fileName = String.valueOf(System.currentTimeMillis()) + type;
//        File file = new File(filePath + File.separator + fileName);
//        FileOutputStream fileOutputStream = null;
//        try {
//            fileOutputStream = new FileOutputStream(file);
//            IOUtils.copy(multipartFile.getInputStream(), fileOutputStream);
//            System.out.println("===========file upload success=======");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                //关闭
//                fileOutputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        //读取文件
//        try {
//            System.out.println("输出" + file);
//            InputStream is = new FileInputStream(file);
//            int iAvail = is.available();
//            byte[] bytes = new byte[iAvail];
//            is.read(bytes);
//            System.out.println("文件内容:\n" + new String(bytes));
//            is.close();
//
//            String s = new String(bytes);
//
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
////        String fileContent = "";
////        try {
////            File f = new File(String.valueOf(file));
////            if (f.isFile() && f.exists()) {
////                InputStreamReader read = new InputStreamReader(new FileInputStream(f), "UTF-8");
////                BufferedReader reader = new BufferedReader(read);
////                String line;
////                while ((line = reader.readLine()) != null) {
////                    fileContent += line;
////                }
////                read.close();
////            }
////        } catch (Exception e) {
////            System.out.println("读取文件内容操作出错");
////            e.printStackTrace();
////        }
////        System.out.println("sssssssssss"+fileContent);
//
//
//        return FrontResult.build(ResultCodeEnum.SUCCESS.getCode(),
//                ResutlMsgEnum.EXECUTE_SUCCESS.getMsg(), aa);
//    }
//
//
//}
