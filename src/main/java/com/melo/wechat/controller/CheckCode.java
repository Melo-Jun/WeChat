package com.melo.wechat.controller;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author Jun
 */
@WebServlet("/CheckCode")
public class CheckCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置尺寸
        int width=100;
        int height=50;
        int x3=20;
        int y3=25;
        //新建一个对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //美化图片
        Graphics g = image.getGraphics();
        g.fillRect(0,0,100-1,50-1);
        g.setColor(Color.BLACK);
        //设置线的坐标
        Random random = new Random();
        //设置字母
        String alp="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        //设置将要存储的验证码字符串
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=4;i++) {
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            int index=random.nextInt(alp.length());
            char c=alp.charAt(index);
            sb.append(c);
            g.drawString(c+"",x3,y3);
            g.drawLine(x1, y1, x2, y2);
            x3+=20;
        }
        //获取验证码
        String checkCode= sb.toString();
        //将验证码存到session
        HttpSession session = request.getSession();
        session.setAttribute("checkCode",checkCode);

        ServletOutputStream outputStream = response.getOutputStream();
        //将图片输出到页面
        ImageIO.write(image,"jpg",outputStream);
    }
}
