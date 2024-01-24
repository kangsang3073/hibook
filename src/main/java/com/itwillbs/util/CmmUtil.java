package com.itwillbs.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http. HttpServletResponse;

public class CmmUtil {
	
	public static void jsonProcess(HttpServletResponse response, String jsonData) {
		// ajax 사용시 json 리턴하는 함수
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (writer != null)
            writer.close();
    }

}
