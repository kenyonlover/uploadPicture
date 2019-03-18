package com.wu.demo.fileupload.demo.controller;

import com.wu.demo.fileupload.demo.util.FileUtils;

import sun.misc.BASE64Encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

@Controller
public class TestController {

    private final ResourceLoader resourceLoader;

    @Autowired
    public TestController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Value("${web.upload-path}")
    private String path;

    /**
     * 跳转到文件上传页面
     * @return
     */
    @RequestMapping("test")
    public String toUpload(){
        return "test";
    }
    
    @RequestMapping("playpic")
    public String playpic(){
        return "playpic";
    }
    
    @RequestMapping("pic")
    public String pic(){
        return "pic";
    }

    /**
     *
     * @param file 要上传的文件
     * @return
     */
    @RequestMapping("fileUpload")
    public String upload(@RequestParam("fileName") MultipartFile file, Map<String, Object> map){

//    	String projectPath = System.getProperty("user.dir");
//    	
//        // 要上传的目标文件存放路径
//        String localPath = "src/main/resources/static/picture";
        
        //String filePath = "C:\\Users\\Administrator\\Downloads\\picture";
        
    	
        
        // 上传成功或者失败的提示
        String msg = "";

        if (FileUtils.upload(file, path, file.getOriginalFilename())){
            // 上传成功，给出页面提示
            msg = "上传成功！";
        }else {
            msg = "上传失败！";

        }

        // 显示图片
        map.put("msg", msg);
        map.put("fileName", file.getOriginalFilename());

        return "forward:/test";
    }

    /**
     * 显示单张图片
     * @return
     */
    @RequestMapping("showPhotos")
    public ResponseEntity showPhotos(){
    	
        try {
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            return ResponseEntity.ok(resourceLoader.getResource("file:" + path));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping(value="preview",produces=MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> preview() throws FileNotFoundException {
    	InputStream inputStream = new FileInputStream(new File("‪C:\\Users\\Administrator\\Desktop\\CDKJ\\fengjing.jpg"));
    	InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
    	HttpHeaders headers = new HttpHeaders();
    	return new ResponseEntity<>(inputStreamResource, headers, HttpStatus.OK);
    }
    
    @RequestMapping("getPhoto")
    public static void getPhoto(HttpServletResponse response) throws Exception {
        File file = new File("D:/fengjing.jpg");
        FileInputStream fis = new FileInputStream(file);

        long size = file.length();
        byte[] temp = new byte[(int) size];
        fis.read(temp, 0, (int) size);
        fis.close();
        byte[] data = temp;
        response.setContentType("image/jpg");
        OutputStream out = response.getOutputStream();
        out.write(data);
        out.flush();
        out.close();

    }
    
    
    @RequestMapping("getData")
    public static String getData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        File file = new File("D:/fengjing.jpg");
        FileInputStream fis = new FileInputStream(file);

        long size = file.length();
        byte[] temp = new byte[(int) size];
        fis.read(temp, 0, (int) size);
        fis.close();
        byte[] data = temp;
        BASE64Encoder encoder = new BASE64Encoder();
        String outstr = encoder.encode(data);
        System.out.println(outstr);
        request.setAttribute("img", outstr);
        return "playpic";
    }

}
