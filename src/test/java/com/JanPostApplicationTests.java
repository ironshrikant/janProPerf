package com;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JanPostApplicationTests {

	@Test
	void contextLoads(){
		try {
			int x = 4;
			int y = 0;
			int z = x / y;
		}catch(Exception e) {
			System.out.println("hi, i am from exception");
			System.out.println(e);
			e.printStackTrace();

		}

	}

}
