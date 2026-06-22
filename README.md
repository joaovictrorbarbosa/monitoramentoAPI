# API Monitor - Spring Boot

Uma aplicação de infraestrutura backend desenvolvida em Spring Boot focada em resiliência, monitoramento automático de APIs e alertas em tempo real.

O sistema executa rondas periódicas em endpoints configurados e, caso detecte alguma indisponibilidade (erros de I/O ou status HTTP inválido), dispara imediatamente uma notificação via e-mail para a equipe de administração.

## Tecnologias Utilizadas
* Java 21
* Spring Boot 4.1.0
* Spring Mail (Integração SMTP)
* RestClient (Consumo HTTP resiliente com políticas de Timeout)
* Mailtrap (Ambiente de Sandbox para testes de e-mail)

## Arquitetura e Boas Práticas
* **Automated Scheduling:** Uso da anotação @EnableScheduling para automação de tarefas de monitoramento sem concorrência de threads mal gerenciadas.
* **Network Timeout Protection:** Configuração de timeouts de conexão e leitura (3 segundos) no RestClient para evitar travamento da aplicação por hang de conexões de APIs lentas ou instáveis.
* **Fail-Safe Processing:** Loop de monitoramento protegido por blocos try-catch robustos, garantindo que a queda de uma API alvo nunca comprometa o ciclo de vida do microsserviço de monitoramento.

## Configuração do Ambiente

1. Clone o repositório para o seu ambiente local:
```bash
git clone [https://github.com/SEU_USER_DO_GITHUB/NOME_DO_REPOSITORIO.git](https://github.com/SEU_USER_DO_GITHUB/NOME_DO_REPOSITORIO.git)
