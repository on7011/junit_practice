package com.example.aa;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class JunitSample4Application { // サーバー側

	public static void main(String[] args) throws IOException {
		SpringApplication.run(JunitSample4Application.class, args);

	}

	@PostMapping("hello")
	public static String st(@RequestBody String sst) {

		return "Hello " + sst;

	}

	@PostMapping
	public String nt(@RequestBody String newText) {

		String output = newText; // リクエストでtextが渡ってきた textが入る入れ物を作ってあげる

		System.out.println(output);

		if (output.substring(14, 17).equals("000")) {
			String returncdbf = output.substring(0, 560);
			String returncdaf = output.substring(562);
			output = returncdbf + "00" + returncdaf;
		} else {
			String returncdbf = output.substring(0, 560);
			String returncdaf = output.substring(562);
			output = returncdbf + "03" + returncdaf;

		}

		String kbnbf = output.substring(0, 19); // 渡ってきたリクエストを書き換える
		String kbnaf = output.substring(20);
		output = kbnbf + "2" + kbnaf;

		String sendBeforre = output.substring(562, 568);
		String sendAfter = output.substring(568, 574);
		output = output.substring(0, 562) + sendAfter + sendBeforre + output.substring(574);

		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter date2 = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
		String stringDate2 = localDateTime.format(date2);

		String serviceId = output.substring(3, 19);

		String before = output.substring(0, 305);

		String yKbn = output.substring(305, 306);
		yKbn = "1";
		String level = output.substring(306, 307);
		level = "3";
		String server = output.substring(307);
		server = String.format("%-16s", "QMFH0x");
		String yKbn2 = output.substring(356, 357);
		yKbn2 = "2";
		String level2 = output.substring(357, 358);
		level2 = "3";
		String server2 = output.substring(358);
		server2 = String.format("%-16s", "QMFH0x");

		String after = output.substring(407);

		StringBuilder sb = new StringBuilder();

		sb.append(before).append(yKbn).append(level).append(server).append(serviceId).append(stringDate2).append(yKbn2)
				.append(level2).append(server2).append(serviceId).append(stringDate2).append(after); // stringbuilderはメソッドチェーンにすることができる
																										// チェーンの前が全てstringbuilder

		System.out.println(sb.toString());

		return sb.toString(); // リターンでレスポンス //stringになおす

	}

}
