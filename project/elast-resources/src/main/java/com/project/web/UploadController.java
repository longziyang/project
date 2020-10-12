package com.project.web;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

	@RequestMapping("/upload")
	@ResponseBody
	public String upload(MultipartFile file, HttpServletRequest request) {

		String path = request.getServletContext().getRealPath("");

		File file2 = new File(path + "\\upload");

		file2.mkdirs();
		File file3 = new File(path + "\\upload" + "\\" + file.getOriginalFilename());
		System.out.println(file3.getPath());

		try {

			file.transferTo(file3);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "ok";
	}

}
