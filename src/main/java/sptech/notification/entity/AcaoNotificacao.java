package sptech.notification.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Table(name = "ACAO_NOTIFICACAO")
public class AcaoNotificacao {
    @Id
    @GeneratedValue
    private Long id;

    private String mensagem;

    private boolean lida = false;

    private boolean enviada = false;

    @ManyToOne
    @JoinColumn(name = "fk_empresa")
    private Empresa empresa;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataEnvio;
}
