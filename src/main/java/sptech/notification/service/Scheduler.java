package sptech.notification.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sptech.notification.entity.AcaoNotificacao;
import sptech.notification.queue.MenssagemEmpresa;
import sptech.notification.repository.AcaoNotificacaoRepository;

import java.util.List;

@Slf4j
@Service
public class Scheduler {

    private final Notification notification;
    private final AcaoNotificacaoRepository acaoNotificacaoRepository;

    public Scheduler(Notification notification, AcaoNotificacaoRepository acaoNotificacaoRepository) {
        this.notification = notification;
        this.acaoNotificacaoRepository = acaoNotificacaoRepository;
    }

    @Scheduled(fixedRate = 1000)
    public void enviarNotificacoes() {
        List<AcaoNotificacao> notificacoesNaoEnviadas = acaoNotificacaoRepository.buscarListaNoficacaoNaoEnviadaPorEmpresaId();
        for (AcaoNotificacao acao : notificacoesNaoEnviadas) {
            notification.sendNotification(
                    new MenssagemEmpresa(
                            acao.getEmpresa().getIdEmpresa().toString(),
                            acao.getMensagem())
            );
            marcarNotificacaoComoEnviada(acao);
        }
    }

    private void marcarNotificacaoComoEnviada(AcaoNotificacao acao) {
        acao.setEnviada(true);
        acaoNotificacaoRepository.save(acao);
    }
}