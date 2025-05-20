package sptech.notification.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import sptech.notification.queue.MenssagemEmpresa;


@Slf4j
@Service
public class Notification {

    private final SimpMessagingTemplate messagingTemplate;

    public Notification(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendNotification(MenssagemEmpresa message) {
        log.info("########################################");
        log.info("Enviando notificação para a empresa: {}", message.empresaId());
        log.info("########################################");
        String destino = "/topic/" + message.empresaId() + "/notifications";
        messagingTemplate.convertAndSend(destino, message.mensagem());
        log.info("Notificação enviada com sucesso.");
    }

}
