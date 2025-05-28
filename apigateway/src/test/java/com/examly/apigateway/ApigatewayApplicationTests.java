package com.examly.apigateway;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.Assertions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ApigatewayApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	private String admintoken;
	private String loanmanagertoken;
	private String studenttoken;

	private ObjectMapper objectMapper = new ObjectMapper(); // Initialize ObjectMapper

	private HttpHeaders createHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

	@Test
    @Order(1)
    void backend_testRegisterAdmin() throws Exception {
        String requestBody = "{\"userId\": 1,\"email\": \"demoadmin@gmail.com\", \"password\": \"admin@1234\", \"username\": \"admin123\", \"userRole\": \"Admin\", \"mobileNumber\": \"1234567890\"}";
        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/register",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @Order(3)
    void backend_testRegisterLoanManager() {
        String requestBody = "{\"userId\": 2,\"email\": \"demoloanmanager@gmail.com\", \"password\": \"manager@1234\", \"username\": \"policy123\", \"userRole\": \"LoanManager\", \"mobileNumber\": \"1234567890\"}";
        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/register",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @Order(5)
    void backend_testRegisterStudent() {
        String requestBody = "{\"userId\": 3,\"email\": \"student@gmail.com\", \"password\": \"student@1234\", \"username\": \"customer123\", \"userRole\": \"Student\", \"mobileNumber\": \"1234567890\"}";

        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/register",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @Order(2)
    void backend_testLoginAdmin() throws Exception, JsonProcessingException {
        String requestBody = "{\"email\": \"demoadmin@gmail.com\", \"password\": \"admin@1234\"}";

        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/login",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        JsonNode responseBody = objectMapper.readTree(response.getBody());
        String token = responseBody.get("token").asText();
        admintoken = token;
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(token);
       
    }

    @Test
    @Order(4)
    void backend_testLoginLoanManager() throws Exception, JsonProcessingException {
        String requestBody = "{\"email\": \"demoloanmanager@gmail.com\", \"password\": \"manager@1234\"}";

        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/login",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        JsonNode responseBody = objectMapper.readTree(response.getBody());
        String token = responseBody.get("token").asText();
        loanmanagertoken = token;

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(token);
    }

    @Test
    @Order(6)
    void backend_testLoginStudent() throws Exception, JsonProcessingException {
        String requestBody = "{\"email\": \"student@gmail.com\", \"password\": \"student@1234\"}";

        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/login",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        JsonNode responseBody = objectMapper.readTree(response.getBody());
        String token = responseBody.get("token").asText();
        studenttoken = token;

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(token);
    }
	@Test
	@Order(7)
	void backend_testAddLoanByLoanManager() throws Exception {
		Assertions.assertNotNull(loanmanagertoken, "loanmanagertoken should not be null");

		String requestBody = "{"
				+ "\"loanId\": " + 1 + ","
				+ "\"loanType\": \"Home Loan\","
				+ "\"interestRate\": " + 5.5 + ","
				+ "\"maxAmount\": " + 5000000 + ","
				+ "\"minAmount\": " + 100000 + ","
				+ "\"minTenureMonths\": " + 120 + ","
				+ "\"maxTenureMonths\": " + 360 + ","
				+ "\"description\": \"A long-term loan for purchasing or refinancing a home.\","
				+ "\"status\": \"Active\","
				+ "\"processingFee\": " + 5000 + ","
				+ "\"prepaymentPenalty\": " + 1.5 + ","
				+ "\"gracePeriodMonths\": " + 6 + ","
				+ "\"latePaymentFee\": " + 2000
				+ "}";

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + loanmanagertoken);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/loans", HttpMethod.POST, requestEntity,
				String.class);

		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	@Order(8)
	void backend_testAddLoanByAdmin() throws Exception {
		Assertions.assertNotNull(admintoken, "admintoken should not be null");

		String requestBody = "{"
				+ "\"loanId\": " + 1 + ","
				+ "\"loanType\": \"Home Loan\","
				+ "\"interestRate\": " + 5.5 + ","
				+ "\"maxAmount\": " + 5000000 + ","
				+ "\"minAmount\": " + 100000 + ","
				+ "\"minTenureMonths\": " + 120 + ","
				+ "\"maxTenureMonths\": " + 360 + ","
				+ "\"description\": \"A long-term loan for purchasing or refinancing a home.\","
				+ "\"status\": \"Active\","
				+ "\"processingFee\": " + 5000 + ","
				+ "\"prepaymentPenalty\": " + 1.5 + ","
				+ "\"gracePeriodMonths\": " + 6 + ","
				+ "\"latePaymentFee\": " + 2000
				+ "}";

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + admintoken);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/loans", HttpMethod.POST, requestEntity,
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
	}

	@Test
	@Order(9)
	void backend_testAddLoansByStudent() throws Exception {
		Assertions.assertNotNull(studenttoken, "studenttoken should not be null");

		String requestBody = "{"
				+ "\"loanId\": " + 1 + ","
				+ "\"loanType\": \"Home Loan\","
				+ "\"interestRate\": " + 5.5 + ","
				+ "\"maxAmount\": " + 5000000 + ","
				+ "\"minAmount\": " + 100000 + ","
				+ "\"minTenureMonths\": " + 120 + ","
				+ "\"maxTenureMonths\": " + 360 + ","
				+ "\"description\": \"A long-term loan for purchasing or refinancing a home.\","
				+ "\"status\": \"Active\","
				+ "\"processingFee\": " + 5000 + ","
				+ "\"prepaymentPenalty\": " + 1.5 + ","
				+ "\"gracePeriodMonths\": " + 6 + ","
				+ "\"latePaymentFee\": " + 2000
				+ "}";

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + studenttoken);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/loans", HttpMethod.POST, requestEntity,
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
	}

	@Test
	@Order(10)
	void backend_testGetAllInsurancesByLoanManager() throws Exception {
		Assertions.assertNotNull(loanmanagertoken, "LoanManager token should not be null");

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + loanmanagertoken);

		// Perform GET request to fetch all pets
		ResponseEntity<String> response = restTemplate.exchange("/api/loans", HttpMethod.GET,
				new HttpEntity<>(headers), // Use HttpEntity with headers
				String.class);

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(11)
	void backend_testGetAllLoansByStudent() throws Exception {
		Assertions.assertNotNull(studenttoken, "Student token should not be null");

		// Set up headers with Authorization token
		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + studenttoken);

		// Perform GET request to fetch all pets
		ResponseEntity<String> response = restTemplate.exchange("/api/loans", HttpMethod.GET,
				new HttpEntity<>(headers), // Use HttpEntity with headers
				String.class);

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	} 
	@Test
	@Order(12)
	void backend_testGetAllLoansByAdmin() throws Exception {
		Assertions.assertNotNull(admintoken, "Admin token should not be null");

		// Set up headers with Authorization token
		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + admintoken);

		// Perform GET request to fetch all pets
		ResponseEntity<String> response = restTemplate.exchange("/api/loans", HttpMethod.GET,
				new HttpEntity<>(headers), // Use HttpEntity with headers
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(13)
	void backend_testGetLoansByIdAsLoanManager() throws Exception {
		Assertions.assertNotNull(loanmanagertoken, "loanmanager Token should not be null");

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + loanmanagertoken);

		ResponseEntity<String> response = restTemplate.exchange(
				"/api/loans/" + 1,
				HttpMethod.GET,
				new HttpEntity<>(headers),
				String.class);

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(14)
	void backend_testGetLoansByIdAsStudent() throws Exception {
		Assertions.assertNotNull(studenttoken, "Student token should not be null");

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + studenttoken);

		ResponseEntity<String> response = restTemplate.exchange(
				"/api/loans/" + 1,
				HttpMethod.GET,
				new HttpEntity<>(headers),
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(15)
	void backend_testGetLoansByIdAsAdmin() throws Exception {
		Assertions.assertNotNull(admintoken, "Admin token should not be null");

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + admintoken);

		ResponseEntity<String> response = restTemplate.exchange(
				"/api/loans/" + 1,
				HttpMethod.GET,
				new HttpEntity<>(headers),
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(16) // Ensure the order is unique and appropriate
	void backend_testAddLoanApplicationByStudent() throws Exception {
		Assertions.assertNotNull(studenttoken, "Student Token should not be null");

		String requestBody = "{"
				+ "\"loanApplicationId\": " + 1 + ","
				+ "\"user\": {"
				+ "\"userId\": " + 1
				+ "},"
				+ "\"loan\": {"
				+ "\"loanId\": " + 1
				+ "},"
				+ "\"loanAmount\": " + 200000 + ","
				+ "\"applicationDate\": \"2024-09-17\","
				+ "\"loanPurpose\": \"Home Renovation\","
				+ "\"status\": \"Pending\","
				+ "\"incomeProof\": \"http://example.com/income-proof.pdf\""
				+ "}";

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + studenttoken);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/loanapplications", HttpMethod.POST,
				requestEntity,
				String.class);

		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	@Order(17) // Ensure the order is unique and appropriate
	void backend_testAddLoanApplicationByAdmin() throws Exception {
		Assertions.assertNotNull(admintoken, "Admin Token should not be null");

		String requestBody = "{"
				+ "\"loanApplicationId\": " + 1 + ","
				+ "\"user\": {"
				+ "\"userId\": " + 1
				+ "},"
				+ "\"loan\": {"
				+ "\"loanId\": " + 1
				+ "},"
				+ "\"loanAmount\": " + 200000 + ","
				+ "\"applicationDate\": \"2024-09-17\","
				+ "\"loanPurpose\": \"Home Renovation\","
				+ "\"status\": \"Pending\","
				+ "\"incomeProof\": \"http://example.com/income-proof.pdf\""
				+ "}";

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + admintoken);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/loanapplications", HttpMethod.POST,
				requestEntity,
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
	}

	@Test
	@Order(18) // Ensure the order is unique and appropriate
	void backend_testAddLoanApplicationByLoanManager() throws Exception {
		Assertions.assertNotNull(loanmanagertoken, "LoanManager Token should not be null");

		String requestBody = "{"
				+ "\"loanApplicationId\": " + 1 + ","
				+ "\"user\": {"
				+ "\"userId\": " + 1
				+ "},"
				+ "\"loan\": {"
				+ "\"loanId\": " + 1
				+ "},"
				+ "\"loanAmount\": " + 200000 + ","
				+ "\"applicationDate\": \"2024-09-17\","
				+ "\"loanPurpose\": \"Home Renovation\","
				+ "\"status\": \"Pending\","
				+ "\"incomeProof\": \"http://example.com/income-proof.pdf\""
				+ "}";

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + loanmanagertoken);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/loanapplications", HttpMethod.POST,
				requestEntity,
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
	}

	@Test
	@Order(19)
	void backend_testGetLoanApplicationByUserIdAsLoanManager() throws Exception {
		Assertions.assertNotNull(loanmanagertoken, "LoanManager token should not be null");

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + loanmanagertoken);

		ResponseEntity<String> response = restTemplate.exchange(
				"/api/loanapplications/user/" + 3, // Adjust the userId as necessary for your test
				HttpMethod.GET,
				new HttpEntity<>(headers),
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(20)
	void backend_testGetLoanApplicationByUserIdAsStudent() throws Exception {
		Assertions.assertNotNull(studenttoken, "Student token should not be null");

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + studenttoken);

		ResponseEntity<String> response = restTemplate.exchange(
				"/api/loanapplications/user/" + 3, // Adjust the userId as necessary for your test
				HttpMethod.GET,
				new HttpEntity<>(headers),
				String.class);

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(21)
	void backend_testGetLoanApplicationByUserIdAsAdmin() throws Exception {
		Assertions.assertNotNull(admintoken, "Admoin token should not be null");

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + studenttoken);

		ResponseEntity<String> response = restTemplate.exchange(
				"/api/loanapplications/user/" + 3, // Adjust the userId as necessary for your test
				HttpMethod.GET,
				new HttpEntity<>(headers),
				String.class);

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(22) // Ensure the order is unique and appropriate
	void backend_testAddFeedbackByStudent() throws Exception {
		Assertions.assertNotNull(studenttoken, "Student token should not be null");

		String requestBody = "{"
				+ "\"feedbackId\": 1,"
				+ "\"feedbackText\": \"Great application, really user-friendly!\","
				+ "\"date\": \"2024-09-15\","
				+ "\"user\": {"
				+ "\"userId\": " + 1
				+ "}"
				+ "}";

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + studenttoken);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.POST, requestEntity,
				String.class);

		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	@Order(23) // Ensure the order is unique and appropriate
	void backend_testAddFeedbackByPolicyManager() throws Exception {
		Assertions.assertNotNull(loanmanagertoken, "PolicyManager should not be null");

		String requestBody = "{"
				+ "\"feedbackId\": 1,"
				+ "\"feedbackText\": \"Great application, really user-friendly!\","
				+ "\"date\": \"2024-09-15\","
				+ "\"user\": {"
				+ "\"userId\": " + 1
				+ "}"
				+ "}";

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + loanmanagertoken);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.POST, requestEntity,
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
	}

	@Test
	@Order(24) // Ensure the order is unique and appropriate
	void backend_testAddFeedbackByAdmin() throws Exception {
		Assertions.assertNotNull(admintoken, "Admin Token should not be null");

		String requestBody = "{"
				+ "\"feedbackId\": 1,"
				+ "\"feedbackText\": \"Great application, really user-friendly!\","
				+ "\"date\": \"2024-09-15\","
				+ "\"user\": {"
				+ "\"userId\": " + 1
				+ "}"
				+ "}";

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + admintoken);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.POST, requestEntity,
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
	}

	@Test
	@Order(25)
	void backend_testGetAllFeedbackByLoanManager() throws Exception {
		Assertions.assertNotNull(loanmanagertoken, "LoanManager token should not be null");

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + loanmanagertoken);

		// Perform GET request to fetch all pets
		ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.GET,
				new HttpEntity<>(headers),
				String.class);

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(26)
	void backend_testGetAllFeedbackByAdmin() throws Exception {
		Assertions.assertNotNull(admintoken, "Admin token should not be null");

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + admintoken);

		// Perform GET request to fetch all pets
		ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.GET,
				new HttpEntity<>(headers),
				String.class);

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(27)
	void backend_testGetAllFeedbackByCustomer() throws Exception {
		Assertions.assertNotNull(studenttoken, "Student token should not be null");

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + studenttoken);

		// Perform GET request to fetch all pets
		ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.GET,
				new HttpEntity<>(headers),
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(28)
	void backend_testGetFeedbackByUserIdAsStudent() throws Exception {
		Assertions.assertNotNull(studenttoken, "Student token should not be null");

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + studenttoken);

		ResponseEntity<String> response = restTemplate.exchange(
				"/api/feedback/user/" + 1, // Adjust the userId as necessary for your test
				HttpMethod.GET,
				new HttpEntity<>(headers),
				String.class);

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(29)
	void backend_testGetFeedbackByUserIdAsLoanManager() throws Exception {
		Assertions.assertNotNull(loanmanagertoken, "LoanManager token should not be null");

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + loanmanagertoken);

		ResponseEntity<String> response = restTemplate.exchange(
				"/api/feedback/user/" + 1, // Adjust the userId as necessary for your test
				HttpMethod.GET,
				new HttpEntity<>(headers),
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(30)
	void backend_testGetFeedbackByUserIdAsAdmin() throws Exception {
		Assertions.assertNotNull(admintoken, "Admin token should not be null");

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + admintoken);

		ResponseEntity<String> response = restTemplate.exchange(
				"/api/feedback/user/" + 1, // Adjust the userId as necessary for your test
				HttpMethod.GET,
				new HttpEntity<>(headers),
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(31) // Ensure the order is unique and appropriate
	void backend_testAddCollegeByAdmin() throws Exception {
		Assertions.assertNotNull(admintoken, "Admin Token should not be null");

		String requestBody = "{"
				+ "\"collegeId\": " + 1 + ","
				+ "\"name\": \"Springfield University\","
				+ "\"address\": \"123 Elm Street, Springfield, IL, 62704\","
				+ "\"contactNumber\": \"+1-555-123-4567\","
				+ "\"email\": \"info@springfield.edu\","
				+ "\"website\": \"http://www.springfield.edu\","
				+ "\"courses\": \"Computer Science, Mechanical Engineering, Business Administration\","
				+ "\"status\": \"Active\""
				+ "}";

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + admintoken);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/colleges", HttpMethod.POST,
				requestEntity,
				String.class);

		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	@Order(32) // Ensure the order is unique and appropriate
	void backend_testAddCollegeByLoanManager() throws Exception {
		Assertions.assertNotNull(loanmanagertoken, "LoanManager Token should not be null");

		String requestBody = "{"
				+ "\"collegeId\": " + 1 + ","
				+ "\"name\": \"Springfield University\","
				+ "\"address\": \"123 Elm Street, Springfield, IL, 62704\","
				+ "\"contactNumber\": \"+1-555-123-4567\","
				+ "\"email\": \"info@springfield.edu\","
				+ "\"website\": \"http://www.springfield.edu\","
				+ "\"courses\": \"Computer Science, Mechanical Engineering, Business Administration\","
				+ "\"status\": \"Active\""
				+ "}";

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + loanmanagertoken);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/colleges", HttpMethod.POST,
				requestEntity,
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
	}

	@Test
	@Order(33) // Ensure the order is unique and appropriate
	void backend_testAddCollegeByStudent() throws Exception {
		Assertions.assertNotNull(studenttoken, "Student Token should not be null");

		String requestBody = "{"
				+ "\"collegeId\": " + 1 + ","
				+ "\"name\": \"Springfield University\","
				+ "\"address\": \"123 Elm Street, Springfield, IL, 62704\","
				+ "\"contactNumber\": \"+1-555-123-4567\","
				+ "\"email\": \"info@springfield.edu\","
				+ "\"website\": \"http://www.springfield.edu\","
				+ "\"courses\": \"Computer Science, Mechanical Engineering, Business Administration\","
				+ "\"status\": \"Active\""
				+ "}";

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + studenttoken);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/colleges", HttpMethod.POST,
				requestEntity,
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
	}

	@Test
	@Order(34)
	void backend_testGetAllCollegeByAdmin() throws Exception {
		Assertions.assertNotNull(admintoken, "Admin token should not be null");

		// Set up headers with Authorization token
		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + admintoken);

		// Perform GET request to fetch all pets
		ResponseEntity<String> response = restTemplate.exchange("/api/colleges", HttpMethod.GET,
				new HttpEntity<>(headers), // Use HttpEntity with headers
				String.class);

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(35)
	void backend_testGetAllCollegeByStudent() throws Exception {
		Assertions.assertNotNull(studenttoken, "Admin token should not be null");

		// Set up headers with Authorization token
		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + studenttoken);

		// Perform GET request to fetch all pets
		ResponseEntity<String> response = restTemplate.exchange("/api/colleges", HttpMethod.GET,
				new HttpEntity<>(headers), // Use HttpEntity with headers
				String.class);

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(36)
	void backend_testGetAllCollegeByLoanManager() throws Exception {
		Assertions.assertNotNull(loanmanagertoken, "Admin token should not be null");

		// Set up headers with Authorization token
		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + loanmanagertoken);

		// Perform GET request to fetch all pets
		ResponseEntity<String> response = restTemplate.exchange("/api/colleges", HttpMethod.GET,
				new HttpEntity<>(headers), // Use HttpEntity with headers
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	
	@Test
	@Order(37)
	void backend_testGetCollegeByIdAsAdmin() throws Exception {
		Assertions.assertNotNull(admintoken, "Admin token should not be null");

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + admintoken);

		ResponseEntity<String> response = restTemplate.exchange(
				"/api/colleges/" + 1,
				HttpMethod.GET,
				new HttpEntity<>(headers),
				String.class);

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(38)
	void backend_testGetCollegeByIdAsStudent() throws Exception {
		Assertions.assertNotNull(studenttoken, "Admin token should not be null");

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + studenttoken);

		ResponseEntity<String> response = restTemplate.exchange(
				"/api/colleges/" + 1,
				HttpMethod.GET,
				new HttpEntity<>(headers),
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(39)
	void backend_testGetCollegeByIdAsLoanManager() throws Exception {
		Assertions.assertNotNull(loanmanagertoken, "Admin token should not be null");

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + loanmanagertoken);

		ResponseEntity<String> response = restTemplate.exchange(
				"/api/colleges/" + 1,
				HttpMethod.GET,
				new HttpEntity<>(headers),
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
    @Order(40)
    void backend_testLoginInValidCredential() throws Exception, JsonProcessingException {
        String requestBody = "{\"email\": \"demoloanmanager@gmail.com\", \"password\": \"manager@123weevfdsgfsvfbffdf3324\"}";

        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/login",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }


}
