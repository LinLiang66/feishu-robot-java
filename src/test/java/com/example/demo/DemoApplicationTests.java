package com.example.demo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;


@SpringBootTest


class DemoApplicationTests {
	@Autowired
	JavaMailSenderImpl javaMailSender;

	@Test
	public void ces1() {


	}

	@Test
	public void ces2() throws  Exception{
		System.out.println("你好世界!");
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setSubject("通知今晚去-开会");
		helper.setText("<b style='color:red'>今晚七点半</b>");
		helper.setTo("1530447082@qq.com");
		helper.setFrom("1191041701@qq.com");

		javaMailSender.send(mimeMessage);
	}




}






