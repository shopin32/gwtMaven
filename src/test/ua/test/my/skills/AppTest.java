package ua.test.my.skills;

import static org.junit.Assert.*;

import org.junit.Test;

import ua.test.my.skills.shared.Contact;

public class AppTest {

	@Test
	public void test() {
		System.out.println(new Contact("asdasd","qweqe","adsasd","adsasd").getId());
		System.out.println(new Contact("asdasd","qweqe","adsasd","adsasd").getId());
	}

}
