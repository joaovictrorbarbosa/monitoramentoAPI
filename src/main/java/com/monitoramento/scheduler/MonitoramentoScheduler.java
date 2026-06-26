package com.monitoramento.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.monitoramento.service.NotificacaoService;

@Component
public class MonitoramentoScheduler{
    private final NotificacaoService notificacaoService;
    private final RestClient restClient;

    public MonitoramentoScheduler(NotificacaoService notificacaoService){
        this.notificacaoService = notificacaoService;

        //configurando o restclient com timout de 3 segundos
        this.restClient = RestClient.builder()
            .requestFactory(new org.springframework.http.client.SimpleClientHttpRequestFactory() {{
                setConnectTimeout(3000);
                setReadTimeout(3000);
            }})
            .build();
    }

    //roda a cada 3000 milissegundos (3 segundos)
    @Scheduled(fixedDelay = 3000)
    public void monitorarApis(){
        String [] urlsParaMonitorar = {
            "https://api.github.com",
            "https://api.url-que-nao-existe.com", //api nao existente para teste
            "http://localhost:8080/consultas" //minha api rodando na maquina
        };
    
        System.out.println("Iniciando ronda de monitorização das APIS...");

        for (String url : urlsParaMonitorar){
            try{
                restClient.get()
                        .uri(url)
                        .retrieve()
                        .toBodilessEntity();
                    
            System.out.println("OK " + url + "Está ONLINE.");        
            
            } catch (Exception e){
                System.out.println("ERROR!! " + url + "Está OFFLINE! Motivo: " + e.getMessage());
            
                //Dispara o email de alerta imediatamente
                notificacaoService.enviarAlertaQueda(url, e.getMessage());
            
            }
        }
    }

    
}
