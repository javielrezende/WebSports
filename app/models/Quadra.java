package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by Miguel, Roger, William on 03/05/2017.
 *
 * Clase para criação do banco de dados
 *
 * Tabela Quadra
 *
 * Entity - para definir o tipo de clase;
 *
 * Id - Id da tabela;
 *
 * ManyToOne - Muitos para Um;
 *
 * JoinColumn - para fazer as ligações entre tabelas;
 *
 */

@Entity
public class Quadra extends Model {

    @Id
    public Integer id;
    public double valorLocacao;
    @ManyToOne
    @JoinColumn(name="copa_id")
    public Copa copa_id;
    @ManyToOne
    @JoinColumn(name="tipoQuadra_id")
    public TipoQuadra tipoQuadra_id;


}
