package com.melo.wechat.controller;

import com.melo.wechat.controller.abs.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Description: 文件下载
 * @author: Jun
 * @date: 19:42 2021/5/25
 */
@WebServlet("/download")
public class DownloadServlet extends BaseServlet {

    /**
     * @Description: 下载文件
     * @param request
     * @param response
     * @date: 11:48 2021/5/25
     * @return: void
     */
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //解决乱码问题
        String path = request.getParameter("path");
        String fileName = request.getParameter("fileName");

        response.addHeader("Content-Type","application/octet-stream");
        response.addHeader("Content-Disposition","attachment;filename="+fileName);
        //通过文件输入流读取文件
        InputStream in=getServletContext().getResourceAsStream(path+fileName);
        OutputStream out=response.getOutputStream();
        byte[] bytes=new byte[1024];
        int len=0;
        while ((len=in.read(bytes))!=-1){
            out.write(bytes,0,len);
        }

    }
}
