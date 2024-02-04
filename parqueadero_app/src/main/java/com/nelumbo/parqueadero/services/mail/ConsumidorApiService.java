package com.nelumbo.parqueadero.services.mail;

import com.nelumbo.parqueadero.services.mail.model.Mail;

public interface ConsumidorApiService {
    String enviarMail(Mail mail);
}
