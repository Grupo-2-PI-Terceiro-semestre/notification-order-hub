package sptech.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.notification.entity.AcaoNotificacao;

import java.util.List;

public interface AcaoNotificacaoRepository extends JpaRepository<AcaoNotificacao, Long> {

    @Query("SELECT a FROM AcaoNotificacao a WHERE a.enviada = false")
    List<AcaoNotificacao> buscarListaNoficacaoNaoEnviadaPorEmpresaId();
}
