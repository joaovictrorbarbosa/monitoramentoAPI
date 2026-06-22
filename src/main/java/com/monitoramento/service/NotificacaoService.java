package com.monitoramento.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService{
    private final JavaMailSender mailSender;

    public NotificacaoService(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    public void enviarAlertaQueda(String urlApi, String motivoErro){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("watchdog@sistema.com");
        message.setTo("joaovictorbarbosa2@gmail.com");
        message.setSubject("[ALERTA] API Fora do Ar!");
        message.setText("Atenção, Administrador, \n\nA API monitorizada no endereço: " + urlApi + " está fora do ar. \n" +
            "Motivo do erro: " + motivoErro + "\n\nPor favor, verifique o status da API imediatamente.");
      
        mailSender.send(message);
        System.out.println("Alerta de queda enviado para o administrador.");
    }
}