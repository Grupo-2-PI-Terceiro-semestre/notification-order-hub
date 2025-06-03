package sptech.notification.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sptech.notification.dto.MenssagemEmpresa;
import sptech.notification.entity.AcaoNotificacao;
import sptech.notification.entity.Empresa;
import sptech.notification.repository.AcaoNotificacaoRepository;

import java.util.List;

import static org.mockito.Mockito.*;

class SchedulerTest {

    private Notification notification;
    private AcaoNotificacaoRepository repository;
    private Scheduler scheduler;

    @BeforeEach
    void setUp() {
        notification = mock(Notification.class);
        repository = mock(AcaoNotificacaoRepository.class);
        scheduler = new Scheduler(notification, repository);
    }

    @Test
    void deveEnviarNotificacoesENaoReenviar() {
        // Arrange
        Empresa empresa = new Empresa();
        empresa.setIdEmpresa(1);

        AcaoNotificacao notificacao = new AcaoNotificacao();
        notificacao.setEmpresa(empresa);
        notificacao.setMensagem("Mensagem teste");
        notificacao.setEnviada(false);

        when(repository.buscarListaNoficacaoNaoEnviadaPorEmpresaId())
                .thenReturn(List.of(notificacao));

        // Act
        scheduler.enviarNotificacoes();

        // Assert
        verify(notification, times(1)).sendNotification(new MenssagemEmpresa("1", "Mensagem teste"));
        verify(repository, times(1)).save(notificacao);
        assert(notificacao.isEnviada());
    }
}
