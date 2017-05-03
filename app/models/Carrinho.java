package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by William on 03/05/2017.
 */
@Entity
public class Carrinho extends Model{
    @ManyToOne
    @JoinColumn(name = "protutos_id")
    public Produtos produtos_id;
    @ManyToOne
    @JoinColumn(name = "venda_id")
    public Venda venda_id;

    public int quantidade;


}
