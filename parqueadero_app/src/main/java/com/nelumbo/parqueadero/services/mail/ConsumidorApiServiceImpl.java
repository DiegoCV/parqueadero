package com.nelumbo.parqueadero.services.mail;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nelumbo.parqueadero.services.mail.model.Mail;
import com.nelumbo.parqueadero.services.mail.model.MailReal;
import com.nelumbo.parqueadero.services.parqueadero.ParqueaderoService;
import com.nelumbo.parqueadero.services.parqueadero.model.Parqueadero;
import com.nelumbo.parqueadero.services.vehiculo.VehiculoService;
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

    @Autowired
    private ParqueaderoService parqueaderoService;

    @Autowired
    private VehiculoService vehiculoService;

    @Override
    public String enviarMail(Mail mail) {
        Parqueadero parqueadero = parqueaderoService.findById(mail.getParqueaderoId());
        vehiculoService.findVehiculoByPlacaInsideParqueadero(mail.getPlaca(), mail.getParqueaderoId());
        MailReal mailReal = MailReal.builder().email(mail.getEmail()).placa(mail.getPlaca()).mensaje(mail.getMensaje())
                .parqueaderoNombre(parqueadero.getNombre()).build();
        return enviarEmailReal(mailReal);
    }

    private String enviarEmailReal(MailReal mail) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = mailToJson(mail);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        return restTemplate.postForObject(apiUrl, requestEntity, String.class);
    }

    private String mailToJson(MailReal mail){
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
