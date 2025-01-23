package com.winja.Captcha.servlet;

import com.winja.Captcha.Util.CaptchaStringGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/captcha")
public class CaptchaGenerator extends HttpServlet {

    private static final String FILE_TYPE = "jpeg";
    private static final Logger log = LoggerFactory.getLogger(CaptchaGenerator.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        resp.setHeader("Pragma", "no-cache");
        resp.setDateHeader("Max-Age", 0);

        String captcha= CaptchaStringGen.getCaptcha(6);
        int width = 160, height = 35;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.OPAQUE);
        Graphics graphics = bufferedImage.createGraphics();
        graphics.setFont(new Font("Arial", Font.BOLD, 20));
        graphics.setColor(new Color(169, 169, 169));
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(new Color(255, 255, 255));
        graphics.drawString(captcha, 20, 25);

        HttpSession session = req.getSession(true);
        session.setAttribute("captcha", captcha);

        OutputStream outputStream = resp.getOutputStream();
        ImageIO.write(bufferedImage, FILE_TYPE, outputStream);
        outputStream.close();
//        try {
//
//            int width=100;      int height=40;
//
//            Color bg = new Color(0,255,255);
//            Color fg = new Color(0,100,0);
//
//            Font font = new Font("Arial", Font.BOLD, 20);
//            BufferedImage cpimg =new BufferedImage(width,height,BufferedImage.OPAQUE);
//            Graphics g = cpimg.createGraphics();
//
//            g.setFont(font);
//            g.setColor(bg);
//            g.fillRect(0, 0, width, height);
//            g.setColor(fg);
//            g.drawString(captcha,10,25);
//
//            HttpSession session = req.getSession(true);
//            session.setAttribute("Captcha", captcha);
//
//            OutputStream outputStream = resp.getOutputStream();
//
//            ImageIO.write(cpimg, FILE_TYPE, outputStream);
//            outputStream.close();

//        } catch (Exception e) {
//            log.error("Error generating the captcha",e);
//        }
    }



}
