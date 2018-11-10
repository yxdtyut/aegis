package com.hualife.aegis;

import com.hualife.merlin.boot.container.ContainerWebAppBootStrap;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
public class AegisApplication {
	public static void main(String[] args) {
        ContainerWebAppBootStrap.startup(args);
	}
}
