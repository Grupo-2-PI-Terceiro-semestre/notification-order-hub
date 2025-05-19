package sptech.notification.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import sptech.notification.queue.MenssagemEmpresa;


@Service
public class Notification {

    private final SimpMessagingTemplate messagingTemplate;

    public Notification(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendNotification(MenssagemEmpresa message) {
        String destino = "/topic/" + message.empresaId() + "/notifications";
        messagingTemplate.convertAndSend(destino, message.mensagem());
    }

}
