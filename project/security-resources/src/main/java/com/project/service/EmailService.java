package com.project.service;

import com.project.entity.SysUser;
import com.project.entity.UserRole;
import com.project.mapper.UserMapper;
import com.project.mapper.UserRoleMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author Lzy
 * @version 创建时间：2020年8月9日 下午4:36:23
 * @ClassName 类名称
 * @Description 类描述
 */
@Service
public class EmailService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    public JavaMailSender javaMailSender;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private static String success = "OK";
    private static String filed = "FILED";

    @Value("${spring.mail.username}")
    private String form;

    /**
     * 验证注册用户
     *
     * @param
     * @return
     */
    public String verifyCode(String username, String password, String mail, String fromClientCode) {

        if (!verifyEmailExists(mail)) {

            return "邮箱已存在";
        }
        String fromRedisCode = redisTemplate.opsForValue().get(mail);
        if (!fromClientCode.equals(fromRedisCode)) {

            return "验证码无效";
        }

        String existsUsername = userMapper.selectUserExistsByUsername(username);
        if (StringUtils.isNotBlank(existsUsername)) {

            return "用户名已存在";
        }

        SysUser user = new SysUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(mail);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setState(1);
        userMapper.insert(user);
        Long userId = userMapper.selectUserId(username);

        UserRole userRole = new UserRole();
        userRole.setUpdateTime(new Date());
        userRole.setCreateTime(new Date());
        userRole.setState(1);
        userRole.setUserId(userId);
        userRole.setRoleId(1l);

        userRoleMapper.insert(userRole);
        return success;
    }

    public String getCode(String mail) {

        try {
            if (verifyEmailExists(mail)) {

                sendMessage(mail);
            }

            return filed;
        } catch (RuntimeException e) {

            return "操作失败  邮箱不存在";
        }

    }

    public String sendMessageBy(String mail) {

        // 1. 创建一封邮件
        Properties props = new Properties(); // 用于连接邮件服务器的参数配置（发送邮件时才需要用到）
        Session session = Session.getInstance(props); // 根据参数配置，创建会话对象（为了发送邮件准备的）
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(mail, "账号管家", "utf-8"));
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(mail, "", "UTF-8"));

            // 追加用户
            // message.addRecipients();
            // Cc: 抄送（可选）
            // message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(mail,
            // "USER_EE", "UTF-8"));
            // Bcc: 密送（可选）
            // message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress(mail,
            // "USER_FF", "UTF-8"))

            // 4. Subject: 邮件主题
            message.setSubject("主题：验证码", "UTF-8");
            // 5. Content: 邮件正文（可以使用html标签）
            String random = randomCode();

            message.setContent(random, "text/html;charset=UTF-8");
            // 6. 设置显示的发件时间
            message.setSentDate(new Date());

            // 7. 保存前面的设置
            message.saveChanges();
            // 8. 将该邮件保存到本地
            OutputStream out = new FileOutputStream("myEmail.eml");
            message.writeTo(out);
            out.flush();
            out.close();

            return random;
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String randomCode() {

        Integer number = (int) (Math.random() * (9999 - 1000 + 1) + 1000);
        return number.toString();
    }

    /**
     * 验证邮箱是否存在
     *
     * @param mail
     * @return
     */
    public boolean verifyEmailExists(String mail) {

        try {
            String userName = userMapper.selectUserNameByEmail(mail);
            if (StringUtils.isNotBlank(userName)) {

                return false;
            }

            return true;

        } catch (Exception e) {

            return false;
        }

    }

    public String submitForget(String mail, String fromClientCode) {


        if (verifyEmailExists(mail)) {

            return "邮箱不存在";
        }
        String fromRedisCode = redisTemplate.opsForValue().get(mail);
        if (!fromClientCode.equals(fromRedisCode)) {

            return "验证码无效";
        }

        SysUser user = userMapper.selectUserByEmail(mail);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(form);
        message.setTo(mail);
        message.setSubject("主题：账号密码信息");
        message.setText("您的账号为:" + user.getUsername() + " 您的密码为:" + user.getPassword() + " 请妥善保管您的账号与密码  避免带来损失");

        javaMailSender.send(message);

        return success;
    }

    public String getCodeByForget(String mail) {

        try {

            if (!verifyEmailExists(mail)) {

                return sendMessage(mail);
            }

            return filed;
        } catch (RuntimeException e) {

            return "操作失败  邮箱不存在";
        }
    }

    public String sendMessage(String mail) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(form);
        message.setTo(mail);
        message.setSubject("主题：验证码");

        String code = randomCode();
        message.setText("验证码为:  " + code + "    五分钟之内有效");
        Long expire = redisTemplate.getExpire(mail);
        System.out.println(System.currentTimeMillis());
        if (expire >= 240) {

            return "请不要重复提交";
        }
        System.out.println(System.currentTimeMillis());
        javaMailSender.send(message);
        System.out.println(System.currentTimeMillis());
        redisTemplate.opsForValue().set(mail, code, 300, TimeUnit.SECONDS);
        System.out.println(System.currentTimeMillis());
        return success;
    }
}
