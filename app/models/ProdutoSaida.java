package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;


/**
 * Created by Miguel, Roger, William on 03/05/2017.
 *
 * Clase para criação do banco de dados
 *
 * Tabela ProdutoSaida
 *
 * @Entity - para definir o tipo de clase;
 *
 * @Id - Id da tabela;
 *
 * @ManyToOne - Muitos para Um;
 *
 * @JoinColumn - para fazer as ligações entre tabelas;
 *
 * @Formats.DataTime - Formato de data;
 */
@Entity
public class ProdutoSaida extends Model{

    @Id
    public Integer id;
    public int quantidade;
    public double valor;
    @Formats.DateTime(pattern="dd-MM-yyyy")
    public Date dataSaida;
    @ManyToOne
    @JoinColumn(name="produtos_id")
    public Produtos produtos_id;



}
