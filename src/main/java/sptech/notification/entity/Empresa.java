package sptech.notification.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "EMPRESA")
@Builder
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmpresa;

    private String nomeEmpresa;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.PERSIST)
    private List<AcaoNotificacao> acoesNotificacao = new ArrayList<>();
}
