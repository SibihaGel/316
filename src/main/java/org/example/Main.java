package org.example;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Main {
    public Main() throws IOException {
    }

    public static void main(String[] args) {

//// Config ------------------------
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Keep-Alive", "timeout=60");
//        headers.set("Authorization", "Bearer my-token");
//
//
//        // GET ---------------------
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//        String response = restTemplate.getForObject("http://94.198.50.185:7081/api/users", String.class, entity);
//        System.out.println(response);
//
//        //SET _---------------------
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        String jsonBody = "{\"id\":\"3\",\"name\":\"Thomas\",\"lastName\":\"Shelby\",\"age\":\"29\"}";
//
//        HttpEntity<String> requestSet = new HttpEntity<>(jsonBody, headers);
//        String responseBody = requestSet.getBody();
//        System.out.println(responseBody);
//
//        //PUT --------------------------------------
//
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Authorization", "Bearer my-token");
//
//        String jsonBodyPut = "{\"id\":\"3\",\"name\":\"Thomas\",\"lastName\":\"Shelby\",\"age\":\"29\"}";
//
//        HttpEntity<String> requestPut = new HttpEntity<>(jsonBody, headers);
//        ResponseEntity<String> responsePut = restTemplate.exchange("http://94.198.50.185:7081/api/users", requestPut, String.class);
//
//        System.out.println(responsePut);
//
//        headers.set("Authorization", "Bearer my-token");
//        HttpEntity<String> entityGetLast = new HttpEntity<>(headers);
//        String responseGetLast = restTemplate.getForObject("http://94.198.50.185:7081/api/users", String.class, entityGetLast);
//        System.out.println(responseGetLast);


////        RestTemplate restTemplate = new RestTemplate();
////        String url = "http://94.198.50.185:7081/api/users";
////        HttpHeaders headers = new HttpHeaders();
////        headers.set("Authorization", "Bearer my-token");
////        HttpEntity<String> entity = new HttpEntity<>("", headers);
////        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
////        String responseBody = response.getBody();
////        System.out.println(responseBody);
////
////
////        HttpEntity<String> entity1 = new HttpEntity<>("{\"id\": \"3\", \"name\": \"James\", \"lastName\": \"Brown\", \"age\": \"29\"}", headers);
////        ResponseEntity<String> response1 = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
////        String responseBody1 = response1.getBody();
////        System.out.println(responseBody1);
////    }
//
//
////        HttpHeaders headers = new HttpHeaders();
////        headers.setContentType(MediaType.APPLICATION_JSON);
////
////        String requestBodySet = "{\"id\": \"3\", \"name\": \"James\", \"lastName\": \"Brown\", \"age\": \"29\"}";
////        HttpEntity<String> requestEntitySet = new HttpEntity<>(requestBodySet, headers);
////        String resPOST = restTemplate.postForObject(url, requestEntitySet, String.class);
////        System.out.println(resPOST);
////
////        String requestBodyPut = "{\"id\": \"3\", \"name\": \"Thomas\", \"lastName\": \"Shelby\", \"age\": \"29\"}";
////        HttpEntity<String> requestEntityPut = new HttpEntity<>(requestBodyPut, headers);
////        String resPUTT = restTemplate.exchange(url, HttpMethod.POST, requestEntitySet, String.class);
////        System.out.println(resPOST);
//
//
//     !!!!!!!   3 МЕТОДА  HttpURLConnection-----------------!!!!!!!!!!!!!!!!!!!!!!!!!
//
        try {
            URL url = new URL(
                    "http://94.198.50.185:7081/api/users");


            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();

            System.out.println("Response code: " + status);
            System.out.println("Response content: " + content);
        } catch (Exception e) {
            e.printStackTrace();
        }
// SET user -----------------------------------------------

        try {
            URL url = new URL("http://94.198.50.185:7081/api/users");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            // Set request headers
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer myToken");

            // Set request body
            String requestBody = "{\"id\": \"3\", \"name\": \"James\", \"lastName\": \"Brown\", \"age\": \"29\"}";
            con.setDoOutput(true);

            try (OutputStream outputStream = con.getOutputStream()) {
                byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }

            int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();

            System.out.println("Response code: " + status);
            System.out.println("Response content: " + content.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


        // PUT UserById 3
        try {
            URL url = new URL("http://94.198.50.185:7081/api/users");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer myToken");
            con.setRequestMethod("PUT");


            // Set request headers and body here
            String requestBody = "{\"id\": \"2\", \"name\": \"Thomas\", \"lastName\": \"Shelby\", \"age\": \"29\"}";
            con.setDoOutput(true);
            try (OutputStream outputStream = con.getOutputStream()) {
                byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }

            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) {
                BufferedReader errorIn = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String errorInputLine;
                StringBuffer errorContent = new StringBuffer();
                while ((errorInputLine = errorIn.readLine()) != null) {
                    errorContent.append(errorInputLine);
                }
                errorIn.close();
                System.out.println("Ошибка на сервере: " + errorContent);
            } else {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                System.out.println("Ответ сервера: " + content);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при отправке запроса: " + e.getMessage());
        }
    }
}




