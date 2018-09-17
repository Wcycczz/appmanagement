package com.ruiliang.management.service.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.jfinal.kit.PropKit;
import com.ruiliang.management.exception.SmsSendFailureException;
import com.ruiliang.management.service.SmsService;


@Service("tencentSmsService")
public class TencentCloudSmsServiceImpl implements SmsService {

	@Override
	public void send(int type, String dest, String content) throws SmsSendFailureException {

		try {
			PropKit.use("application.properties");
			SmsSingleSender singleSender = new SmsSingleSender(Integer.parseInt(PropKit.get("tencloud.sms.appid")),
					PropKit.get("tencloud.sms.appkey"));
			SmsSingleSenderResult singleSenderResult = null;
			int tmplId = 177653;
			ArrayList<String> params = new ArrayList<>();
			params.add(content);

			singleSenderResult = singleSender.sendWithParam("86", dest, tmplId, params, "", "", "");
		} catch (Exception e) {
			throw new SmsSendFailureException("短信发送出错: " + e.toString());
		}
	}

}
