package com.project.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.project.util.jwt.RsaUtils;

import java.security.PrivateKey;
import java.security.PublicKey;

import javax.annotation.PostConstruct;

@Component
@ConfigurationProperties(prefix = "spring.jwt.key")
public class KeyConfig {

	private String pub;
	private String pri;

	private PublicKey publicKey;
	private PrivateKey privateKey;

	// 构造方法后执行
	@PostConstruct
	public void loadKey() throws Exception {

		System.out.println("pub = " + pub + " pri=" + pri);
		publicKey = RsaUtils.getPublicKey(pub);
		privateKey = RsaUtils.getPrivateKey(pri);
	}

	public String getPub() {
		return pub;
	}

	public void setPub(String pub) {
		this.pub = pub;
	}

	public String getPri() {
		return pri;
	}

	public void setPri(String pri) {
		this.pri = pri;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}
}
