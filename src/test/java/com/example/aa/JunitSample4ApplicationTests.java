package com.example.aa;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JunitSample4ApplicationTests {

	@Autowired
	public TestRestTemplate testRestTemplate;

	@LocalServerPort
	public int port;

	@Test
	public void sample() throws IOException {

		String input = Files.readString(Paths.get("src/main/resources/TXTCenter.txt"));
		String serviceIDbf = input.substring(0, 3);
		String serviceIDaf = input.substring(19);
		input = serviceIDbf + String.format("%-16s", "DF0000DF001000") + serviceIDaf; // 後ろの文字を半角スペースで埋めて１６文字にする

		String kbnbf = input.substring(0, 19);
		String kbnaf = input.substring(20);
		input = kbnbf + "1" + kbnaf;

		String returncdbf = input.substring(0, 560);
		String returncdaf = input.substring(562);
		input = returncdbf + String.format("%2s", "") + returncdaf;

		String sendbf = input.substring(0, 562);
		String sendaf = input.substring(574);
		input = sendbf + "123456654321" + sendaf;

		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter date2 = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
		long time = Long.parseLong(localDateTime.format(date2));

		String result = postTest2(input); // postTest2を呼んだ。 postTest2のリターンを新しい箱を作って変数resultに入れ直す。 ここでレスポンスを受け取る

//		String kbnbf2 = input.substring(0, 19);
//		String kbnaf2 = input.substring(20);
//		String aa = kbnbf2 + "2" + kbnaf2;
//		String returnbf = aa.substring(0, 560);
//		String returnaf = aa.substring(562);
//		aa = returnbf + "00" + returnaf;
//		String sendbf2 = aa.substring(0, 562);
//		String sendaf2 = aa.substring(574);
//		aa = sendbf2 + "654321123456" + sendaf2;
//		String serviceId = aa.substring(3, 19);
//		String serviceIdbf = aa.substring(0, 323);
//		String serviceIdaf = aa.substring(339);
//		aa = serviceIdbf + serviceId + serviceIdaf;
//
//		StringBuilder sb = new StringBuilder();
//
//		String serviceID = aa.substring(3, 19);
//
//		String before = aa.substring(0, 305);
//
//		String yKbn = aa.substring(306, 307);
//		yKbn = "1";
//		String level = aa.substring(307, 308);
//		level = "3";
//		String server = aa.substring(308, 313);
//		server = "QMFH0x";
//		String center = aa.substring(313, 323);
//		String yKbn2 = aa.substring(356, 357);
//		yKbn2 = "2";
//		String level2 = aa.substring(357, 358);
//		level2 = "3";
//		String server2 = aa.substring(358, 363);
//		server2 = "QMFH0x";
//		String center2 = aa.substring(363, 373);
//
//		String after = aa.substring(407);
//
//		sb.append(before);
//		sb.append(yKbn);
//		sb.append(level);
//		sb.append(server);
//		sb.append(center);
//		sb.append(serviceId);
//		sb.append(time);
//		sb.append(yKbn2);
//		sb.append(level2);
//		sb.append(server2);
//		sb.append(center2);
//		sb.append(serviceId);
//		sb.append(time);
//		sb.append(after);
//
//		System.out.println(sb);

//		assertEquals(sb.toString(), result);

		assertEquals(input.substring(3, 19), result.substring(3, 19));
		assertEquals("2", result.substring(19, 20));
		assertEquals("00", result.substring(560, 562));
		assertEquals("654321123456", result.substring(562, 574));
		assertEquals("DF0000DF001000", result.substring(323, 337));
		assertEquals("1", result.substring(305, 306));
		assertEquals("3", result.substring(306, 307));
		assertEquals("QMFH0x", result.substring(307, 313));
		assertEquals("2", result.substring(356, 357));
		assertEquals("3", result.substring(357, 358));
		assertEquals("QMFH0x", result.substring(358, 364));
//		assertEquals(time, result.substring(339, 356));

		long timeaf = Long.parseLong(result.substring(339, 356));
		assertTrue(time < timeaf);

	}

	@Test // サービスIDが003だった場合
	public void sample2() throws IOException {
		String input = Files.readString(Paths.get("src/main/resources/TXTCenter.txt"));
		String serviceIDbf = input.substring(0, 3);
		String serviceIDaf = input.substring(19);
		input = serviceIDbf + String.format("%-16s", "DF0000DF001003") + serviceIDaf; // 後ろの文字を半角スペースで埋めて１６文字にする

		String kbnbf = input.substring(0, 19);
		String kbnaf = input.substring(20);
		input = kbnbf + "1" + kbnaf;

		String returncdbf = input.substring(0, 560);
		String returncdaf = input.substring(562);
		input = returncdbf + String.format("%2s", "") + returncdaf;

		String sendbf = input.substring(0, 562);
		String sendaf = input.substring(574);
		input = sendbf + "123456654321" + sendaf;

		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter date2 = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
		long time = Long.parseLong(localDateTime.format(date2));

		String result = postTest2(input);

		assertEquals(input.substring(3, 19), result.substring(3, 19));
		assertEquals("2", result.substring(19, 20));
		assertEquals("03", result.substring(560, 562));
		assertEquals("654321123456", result.substring(562, 574));
		assertEquals("DF0000DF001003", result.substring(323, 337));
		assertEquals("1", result.substring(305, 306));
		assertEquals("3", result.substring(306, 307));
		assertEquals("QMFH0x", result.substring(307, 313));
		assertEquals("2", result.substring(356, 357));
		assertEquals("3", result.substring(357, 358));
		assertEquals("QMFH0x", result.substring(358, 364));

		long timeaf = Long.parseLong(result.substring(339, 356));
		assertTrue(time < timeaf);

	}

	public String postTest2(String ab) { // ab→rsb.toString()が返ってきた
		String url = "http://localhost:" + port; // result→リターンされたreturncdsb
		String result = testRestTemplate.postForObject(url, ab, String.class); // testRestTemplateクラスのpostForOnjectメソッド(引数--url,クラス)
		return result;
	}

	@Test
	public void postTest() {
		String url = "http://localhost:" + port + "/hello";
		String result = testRestTemplate.postForObject(url, "ohashi", String.class);
		assertEquals("Hello ohashi", result);

	}

}
