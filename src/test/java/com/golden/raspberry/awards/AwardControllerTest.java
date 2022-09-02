package com.golden.raspberry.awards;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AwardControllerTest {
	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@LocalServerPort
	private int port;

	@Test
	public void testRetrieveMinMaxIntervalBetweenTwoAwards() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/award/getMinMaxInvervalWinner"), HttpMethod.GET, entity, String.class);
		System.out.println("RESPONSE "+response);
		String expected = "{\"min\":[{\"producer\":\"Buzz Feitshans\",\"interval\":3,\"previousWin\":\"1985\",\"followingWin\":\"1988\"},{\"producer\":\"Joel Silver\",\"interval\":6,\"previousWin\":\"1996\",\"followingWin\":\"2002\"}],\"max\":[{\"producer\":\"Buzz Feitshans\",\"interval\":9,\"previousWin\":\"1985\",\"followingWin\":\"1994\"},{\"producer\":\"Joel Silver\",\"interval\":13,\"previousWin\":\"1989\",\"followingWin\":\"2002\"}]}";
		System.out.println("EXPECTED "+expected);
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	@Test
	public void testStatusReturnMethod() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity(createURLWithPort("/award/getMinMaxInvervalWinner"),
				String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
}
