package sptech.notification.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import sptech.notification.repository.AcaoNotificacaoRepository;
import sptech.notification.service.Notification;
import sptech.notification.service.Scheduler;

@Configuration
public class BeanConfig {

    @Bean
    public Scheduler scheduler(Notification notification, AcaoNotificacaoRepository acaoNotificacaoRepository) {
        return new Scheduler(notification, acaoNotificacaoRepository);
    }

    @Bean
    public Notification notification(SimpMessagingTemplate messagingTemplate) {
        return new Notification(messagingTemplate);
    }
}