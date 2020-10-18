package com.mystery.controller;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import com.utils.BaseController;

@Controller
@RequestMapping("/file")
public class FileController extends BaseController {

	@RequestMapping("/upload")
	public String uploading(MultipartFile upfile, HttpServletRequest hsr) throws Exception {

		String path = hsr.getServletContext().getRealPath("");
		File dir = new File(path + "//upload");
		dir.mkdirs();
		upfile.transferTo(new File(path + "//upload" + "//" + upfile.getOriginalFilename()));

		return "/demo/success";
	}

}