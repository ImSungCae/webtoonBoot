package com.webtoonBoot.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class FileDownloadController {
	
	private static final String CURR_IMAGE_REPO_PATH = "/home/ec2-user/springBoot/webtoonfriends_repo/file_repo";
	
	@RequestMapping("/download.do")
	protected void download(@RequestParam("fileName") String fileName,
		                 	@RequestParam("goods_id") String goods_id,
			                 HttpServletResponse response) throws Exception {
		OutputStream out = response.getOutputStream();
		String filePath=CURR_IMAGE_REPO_PATH+"/"+goods_id+"/"+fileName;
		File image=new File(filePath);

		fileName = URLEncoder.encode(fileName,"utf-8");
		FileInputStream in=new FileInputStream(image); 
		response.setHeader("Cache-Control","no-cache");
		response.addHeader("Content-disposition", "attachment; fileName="+fileName);
		byte[] buffer=new byte[1024*8];
		while(true){
			int count=in.read(buffer); 
			if(count==-1)  
				break;
			out.write(buffer,0,count);
		}
		in.close();
		out.close();
	}
	
	@RequestMapping("/thumbnails.do")
	protected void thumbnails(@RequestParam("fileName") String fileName,
                            	@RequestParam("goods_id") String goods_id,
			                 HttpServletResponse response) throws Exception {
		OutputStream out = response.getOutputStream();
		String filePath=CURR_IMAGE_REPO_PATH+"/"+goods_id+"/"+fileName;
		File image=new File(filePath);
		
		if (image.exists()) { 
			Thumbnails.of(image).size(235,235).outputFormat("png").toOutputStream(out);
		}
		
		byte[] buffer = new byte[1024 * 8];
		out.write(buffer);
		out.close();
	}
}
