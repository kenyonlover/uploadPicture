package com.wu.demo.fileupload.demo.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件上传工具包
 */
public class FileUtils {

    /**
     *
     * @param file 文件
     * @param path 文件存放路径
     * @param fileName 源文件名
     * @return
     */
    public static boolean upload(MultipartFile file, String path, String fileName){


        // 生成新的文件名
        String realPath = path + "/" + FileNameUtils.getFileName(fileName);

        //使用原文件名
        //String realPath = path + "/" + fileName;

        File dest = new File(realPath);        

        //判断文件父目录是否存在
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }

        try {
            //保存文件
            file.transferTo(dest);
            System.out.println("getAbsolutePath()："+dest.getAbsolutePath());
            System.out.println("getCanonicalPath()："+dest.getCanonicalPath());
            System.out.println("getName()："+dest.getName());
            System.out.println("getPath()："+dest.getPath());
            System.out.println("getTotalSpace()："+dest.getTotalSpace());
            System.out.println("getUsableSpace()："+dest.getUsableSpace());
            System.out.println("getAbsoluteFile()："+dest.getAbsoluteFile());
            System.out.println("getCanonicalFile()："+dest.getCanonicalFile());
            return true;
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

    }


}
