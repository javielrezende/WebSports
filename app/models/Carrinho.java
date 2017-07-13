package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Miguel, Roger, William *
 * Model que cria a tabela carrinho.
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
