package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by Miguel, Roger, William on 03/05/2017.
 *
 * Clase para criação do banco de dados
 *
 * Tabela Produtos
 *
 * @Entity - para definir o tipo de clase;
 *
 * @Id - Id da tabela;
 *
 * @ManyToOne - Muitos para Um;
 *
 * @JoinColumn - para fazer as ligações entre tabelas;
 */


@Entity
public class Produtos extends Model{

    @Id
    public Integer id;
    public String nome;
    public double valorCompra;
    public double precoUnitario;
    public int quantidade;
    @ManyToOne
    @JoinColumn(name="copa_id")
    public Copa copa_id;
    @ManyToOne
    @JoinColumn(name="categoria_id")
    public Categoria categoria_id;

}
