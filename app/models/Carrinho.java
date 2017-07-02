package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by Miguel, Roger, William on 03/05/2017.
 *
 * Clase para criação do banco de dados
 *
 * Tabela Carrinho
 */
@Entity
public class Carrinho extends Model{

    @ManyToOne
    @JoinColumn(name = "produtos_id")
    public Produtos produtos_id;
    @ManyToOne
    @JoinColumn(name = "venda_id")
    public Venda venda_id;

    public int quantidade;


}
