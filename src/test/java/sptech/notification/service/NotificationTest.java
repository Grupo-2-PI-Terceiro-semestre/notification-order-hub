package sptech.notification.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import sptech.notification.dto.MenssagemEmpresa;

import static org.mockito.Mockito.*;

class NotificationTest {

    private SimpMessagingTemplate messagingTemplate;
    private Notification notification;

    @BeforeEach
    void setUp() {
        messagingTemplate = mock(SimpMessagingTemplate.class);
        notification = new Notification(messagingTemplate);
    }

    @Test
    void deveEnviarMensagemParaDestinoCorreto() {
        // Arrange
        MenssagemEmpresa mensagem = new MenssagemEmpresa("123", "Olá, empresa!");

        // Act
        notification.sendNotification(mensagem);

        // Assert
        verify(messagingTemplate, times(1))
                .convertAndSend("/topic/123/notifications", "Olá, empresa!");
    }
}