package br.edu.ifpb.modelo;

import br.edu.ifpb.modelo.PostoDeCombustivel;
import br.edu.ifpb.modelo.TipoDeCombustivel;
import br.edu.ifpb.modelo.Usuario;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-06T07:44:24")
@StaticMetamodel(Abastecimento.class)
public class Abastecimento_ { 

    public static volatile SingularAttribute<Abastecimento, Double> valorLitro;
    public static volatile SingularAttribute<Abastecimento, LocalDateTime> horario;
    public static volatile SingularAttribute<Abastecimento, Double> qtdeLitros;
    public static volatile SingularAttribute<Abastecimento, PostoDeCombustivel> postoDeCombustivel;
    public static volatile SingularAttribute<Abastecimento, TipoDeCombustivel> tipoDeCombustivel;
    public static volatile SingularAttribute<Abastecimento, Double> valorPago;
    public static volatile SingularAttribute<Abastecimento, Usuario> usuario;
    public static volatile SingularAttribute<Abastecimento, Long> id;
    public static volatile SingularAttribute<Abastecimento, Double> quilometragem;

}