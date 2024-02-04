package com.nelumbo.mail.controller;

import com.nelumbo.mail.controller.model.MailRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/mail", produces = MediaType.APPLICATION_JSON_VALUE)
public class MailController {

    private static final Logger logger = LoggerFactory.getLogger(MailController.class);

    @PostMapping
    public String enviarCorreo(@RequestBody MailRequest mailRequest){
        logger.info("mail = " + mailRequest.toString());

        return "Correo Enviado";
    }

}
