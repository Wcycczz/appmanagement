package com.ruiliang.management.service;

import com.ruiliang.management.exception.SmsSendFailureException;


public interface SmsService {

	int SMS_TYPE_RESET_PASSWD = 1;

	/**
	 * 发送短信
	 * 
	 * @param type    发送验证码类型 1 密码重置
	 * @param dest    接收短信目的号码
	 * @param content 短信内容
	 * @throws SmsSendFailureException
	 */
	void send(int type, String dest, String content) throws SmsSendFailureException;
}
