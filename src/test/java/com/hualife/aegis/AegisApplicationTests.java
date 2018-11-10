/*
package com.hualife.aegis;

import com.hualife.aegis.entity.User;
import com.hualife.aegis.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AegisApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void contextLoads() {
		List<User> userList = userMapper.selectList(null);
		userList.stream().forEach((x) -> System.out.println(x.toString()));
	}

}
*/
