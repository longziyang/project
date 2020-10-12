package com.project.web.sign;

import com.alibaba.fastjson.JSON;
import com.project.common.LoginUserInfo;
import com.project.config.SignedConfig;
import com.project.entity.SysUser;
import com.project.service.EmailService;
import com.project.service.VerifyTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lzy
 * @version 创建时间：2020年8月1日 上午9:18:06
 * @ClassName 不经过spring security过滤器链
 * @Description 类描述
 */
@Controller
@RequestMapping("/sign")
@Slf4j
public class LoginController {

    @Autowired
    private EmailService service;
    @Autowired
    private VerifyTokenService verifyTokenService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${server.port}")
    private String port;

    @RequestMapping("/verify/token")
    @ResponseBody
    public String verifyToken(String token) {

        log.debug("verifyToken");
        String result = verifyTokenService.verify(token);
        return result;
    }

    @RequestMapping("/index")
    public String home(Model model) {

        SysUser user = LoginUserInfo.getUser();
        String username = user.getUsername();
        String key = SignedConfig.todayKey;
        Object object = redisTemplate.opsForHash().get(key, username);

        // 今天第一次登录
        if (object == null) {

            String val = JSON.toJSONString(user);
            model.addAttribute("sign", true);
            redisTemplate.opsForHash().put(key, username, val);
        }


        return "sign/home";
    }

    @RequestMapping("/")
    public String index() {

        return "sign/home";
    }

    @GetMapping("/403")
    public String accessDenied() {

        System.out.println("403页面");
        return "exception/403";
    }


    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String login() {

        System.out.println("port: " + port);
        return "sign/login";
    }

    @RequestMapping("/loginError")
    public String loginError() {

        return "sign/filed";
    }

    @RequestMapping("/forget")
    public String forget() {
        return "sign/forget";
    }

    @RequestMapping("/register")
    public String register() {
        return "sign/register";
    }

    @RequestMapping("/getCode")
    @ResponseBody
    public String getCode(@RequestParam("mail") String mail) {

        return service.getCode(mail);
    }

    @RequestMapping("/getCodeByForget")
    @ResponseBody
    public String getCodeByForget(@RequestParam("mail") String mail) {

        return service.getCodeByForget(mail);
    }

    @RequestMapping(value = "/verifyCode")
    @ResponseBody
    public String verifyCode(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("mail") String mail, @RequestParam("code") String code) {

        return service.verifyCode(username, password, mail, code);
    }

    @RequestMapping(value = "/submitForget")
    @ResponseBody
    public String submitForget(@RequestParam("mail") String mail, @RequestParam("code") String code) {

        return service.submitForget(mail, code);
    }

}
