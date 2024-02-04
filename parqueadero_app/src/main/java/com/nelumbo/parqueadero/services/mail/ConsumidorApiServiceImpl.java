package com.nelumbo.parqueadero.services.mail;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nelumbo.parqueadero.services.mail.model.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class ConsumidorApiServiceImpl implements ConsumidorApiService{
    @Value("${api.mail.url}")
    private String apiUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String enviarMail(Mail mail) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = mailToJson(mail);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        String respuesta = restTemplate.postForObject(apiUrl, requestEntity, String.class);

        return respuesta;
    }

    private String mailToJson(Mail mail){
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{}";
        try {
            json = objectMapper.writeValueAsString(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }
}
