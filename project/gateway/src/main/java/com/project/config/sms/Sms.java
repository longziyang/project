package com.project.config.sms;

public class Sms {

    /**
     * 发送手机短信
     *
     * @param mobile
     * @param content
     * @throws Exception
     */
    public boolean sendSmsMessage(String mobile, String content) throws Exception {
//        if (StringUtils.isBlank(mobile)) {
//            log.error("发送短信时，手机号码为空！");
//            throw new IllegalArgumentException("发送短信时，手机号码为空！");
//        }
//
//        if (StringUtils.isBlank(content)) {
//            log.error("发送短信时，发送内容为空！");
//            throw new IllegalArgumentException("发送短信时，发送内容为空！");
//        }
//
//        String baseUrl = ccfb.getConfiguration().getString("sms.baseurl");
//        String account = ccfb.getConfiguration().getString("sms.account");
//        String password = ccfb.getConfiguration().getString("sms.pass");
         String baseUrl =
         "http://www.jianzhou.sh.cn/JianzhouSMSWSServer/http/sendBatchMessage";
         String account = "sdk_scjt";
         String password = "123456";
//        if (StringUtils.isBlank(baseUrl)) {
//            return false;
//        }
//
//        if (StringUtils.isBlank(account)) {
//            log.error("发送短信时，账号为空！");
//            return false;
//        }
//
//        if (StringUtils.isBlank(password)) {
//            log.error("发送短信时，密码为空！");
//            return false;
//        }

        BusinessService bs = new BusinessService();
        try {
            int ret = bs.sendBatchMessage(account, password, mobile, content);
            if (ret >= 0) {
                log.info("短信发送成功，发送号码：" + mobile + ", 返回值为：" + ret);
                return true;
            } else {
                log.error(ErrorCode.findMessage(ret));
                return false;
            }
        } catch (Exception ex) {
            log.error("短信发送失败，发送号码：" + mobile, ex);
            return false;
        }
    }

    static enum ErrorCode {
        CODE_NAGE_1("余额不足", -1), CODE_NAGE_2("帐号或密码错误", -2), CODE_NAGE_3("连接服务商失败", -3), CODE_NAGE_4("超时", -4), CODE_NAGE_5(
                "其他错误，一般为网络问题，IP受限等", -5), CODE_NAGE_6("短信内容为空", -6), CODE_NAGE_7("目标号码为空", -7), CODE_NAGE_8(
                "用户通道设置不对，需要设置三个通道", -8), CODE_NAGE_9("捕获未知异常", -9), CODE_NAGE_10("超过最大定时时间限制", -10), CODE_NAGE_11(
                "目标号码在黑名单里", -11), CODE_NAGE_12("消息内容包含禁用词语", -12), CODE_NAGE_13("没有权限使用该网关", -13), CODE_NAGE_14(
                "找不到对应的Channel ID", -14), CODE_NAGE_17("没有提交权限，客户端帐号无法使用接口提交", -17), CODE_NAGE_18("提交参数名称不正确或确少参数", -18), CODE_NAGE_19(
                "必须为POST提交", -19), CODE_NAGE_20("超速提交(一般为每秒一次提交)", -20), CODE_NAGE_30("未知错误！", -30);

        private ErrorCode(String detail, int code) {
            this.detail = detail;
            this.code = code;
        }

        private String detail;
        private int code;

        public String getDetail() {
            return detail;
        }

        public int getCode() {
            return code;
        }

        public static String findMessage(int code) {
            String message = "未知的错误！";
            for (ErrorCode item : ErrorCode.values()) {
                if (item.getCode() == code) {
                    return item.getDetail();
                }
            }
            return message;
        }
    }
}
