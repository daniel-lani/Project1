package com.revature.services;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.ReimbursementService;

/*public class ViewService {
	public static String showImage(HttpServletRequest request, HttpServletResponse responce) {
	  StringBuffer url = request.getRequestURL();
	  int id = Integer.parseInt(url.substring(url.indexOf("?")+3));
	  byte[] content = new byte[1024];
	  ReimbursementService.getReimbursmentById(id).getphoto().read(content);
	  String conString = new String(content);
      responce.setContentType(request.getServletContext().getMimeType(conString));
      responce.setContentLength(content.length);
      try {
		responce.getOutputStream().write(content);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}*/
