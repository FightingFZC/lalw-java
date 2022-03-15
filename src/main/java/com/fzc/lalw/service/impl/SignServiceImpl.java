package com.fzc.lalw.service.impl;

import com.fzc.lalw.domain.Admin;
import com.fzc.lalw.domain.SignAble;
import com.fzc.lalw.domain.User;
import com.fzc.lalw.mapper.AdminMapper;
import com.fzc.lalw.mapper.UserMapper;
import com.fzc.lalw.service.SignService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

/**
 * @author PerfectFu
 * @projectName IntelliJ IDEA
 * @date 2022/3/12
 */
@Service
public class SignServiceImpl implements SignService {
    @Resource
    AdminMapper adminMapper;
    @Resource
    UserMapper userMapper;
    @Override
    public SignAble login(Map<String, String> loginInfo) {
        SignAble actor = null;
        String identity = loginInfo.get("identity");
        System.out.println(identity);
        String password = loginInfo.get("password");
        if ("user".equals(identity)) {
            String email = loginInfo.get("account");
            User user = userMapper.getUserByEmail(email);
            if (user.getPassword().equals(password)) {
                actor = user;
            }
        }else if ("admin".equals(identity)) {
            String account = loginInfo.get("account");
            Admin admin = adminMapper.getAdminByAccount(account);
            if (admin.getPassword().equals(password)) {
                actor = admin;
            }
        }

        return actor;
    }

    @Override
    public Boolean register(User user) {
        int num = userMapper.addUser(user);
        return num != 0;
    }

    @Override
    public String createCode(String email) {
        //服务器地址
//        String smtp = "smtp.gmail.com";
        String smtp = "smtp.qq.com";
        //发送邮箱的用户名
//        String username = "fuzhichao.me@gmail.com";
        String username = "2026611738@qq.com";
        //发送邮箱的密码
//        String password = "uhnjexzfhfhuhshk";
        String password = "ofiqpmfpbmpceeia";
        //配置连接服务器
        Properties prop = new Properties();
        prop.put("mail.smtp.host", smtp);
        prop.put("mail.smtp.port", "587"); // 主机端口号
        prop.put("mail.smtp.auth", "true"); // 是否需要用户认证
        prop.put("mail.smtp.starttls.enable", "true"); // 启用TLS加密
        //javax.mail里的类
        Session mailSession = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
//        mailSession.setDebug(true);
        try {
            //构建消息内容对象
            MimeMessage message = new MimeMessage(mailSession);
            // 设置发送方地址:
//            message.setFrom(new InternetAddress("fuzhichao.me@gmail.com"));
            message.setFrom(new InternetAddress("2026611738@qq.com"));
            // 设置接收方地址:
            message.setRecipient(Message.RecipientType.TO,
                    new InternetAddress(email));
            // 设置邮件主题:
            message.setSubject("注册验证码！", "UTF-8");
            // 设置邮件正文:
            Random random = new Random();
            StringBuilder content = new StringBuilder();
            /*生成六位数*/
            for (int i = 0; i < 6; i++) {
                content.append(random.nextInt(10));
            }

            message.setText("验证码为：" + content, "UTF-8");
            // 发送:
            Transport.send(message);
            return content.toString();
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }

    }
}
