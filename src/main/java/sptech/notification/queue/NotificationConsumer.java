package sptech.notification.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NotificationConsumer {



    private final SimpMessagingTemplate messagingTemplate;

    public NotificationConsumer(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @SqsListener(value = "${aws.sqs.queue.name}")
    public void consumer(String messageJson) throws JsonProcessingException {
        try {
            log.info("##############################################");
            log.info("Recebendo mensagem para SQS: {}", messageJson);
            log.info("##############################################");
            ObjectMapper mapper = new ObjectMapper();
            MessageEmpresa message = mapper.readValue(messageJson, MessageEmpresa.class);

            String destino = "/topic/" + message.empresaId() + "/notifications";
            messagingTemplate.convertAndSend(destino, message.mensagem());

            log.info("Mensagem enviada via WS");

        } catch (Exception e) {
            System.err.println("Erro ao processar mensagem: " + e.getMessage());
            throw e;
        }
    }
}
