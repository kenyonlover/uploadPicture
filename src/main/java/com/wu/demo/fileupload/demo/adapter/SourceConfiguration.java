package com.wu.demo.fileupload.demo.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 作者：administrator
 * 时间：2019年3月16日 下午9:18:33
 * 说明：配置图片访问地址
 */

@Configuration
public class SourceConfiguration extends WebMvcConfigurerAdapter {
	
	@Value("${web.upload-path}")
    private String path;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//和页面有关的静态目录都放在项目的static目录下
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/picture");
		//上传的图片放在真实路径下，通过127.0.0.1:8102/picture/xxxxx.jpg访问        path为文件的真是存放路径，通过配置文件读取
		registry.addResourceHandler("/imgup/picture/**").addResourceLocations("file:"+path);
		super.addResourceHandlers(registry);
	}
}
