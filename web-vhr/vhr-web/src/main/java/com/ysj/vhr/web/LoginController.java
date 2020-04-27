package com.ysj.vhr.web;


import com.ysj.vhr.config.VerificationCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class LoginController {
    @GetMapping("login")
    public ResponseEntity<String> login(){
        return ResponseEntity.ok("尚未登录，请登录");
    }

    /**
     * 验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取验证码
        VerificationCode code = new VerificationCode();
        BufferedImage image = code.getImage();
        String text = code.getText();
        HttpSession session = request.getSession();
        session.setAttribute("verify_code",text);
        //写出到页面
        VerificationCode.output(image,response.getOutputStream());
    }
}
