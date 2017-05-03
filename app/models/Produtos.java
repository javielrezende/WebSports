package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by William on 29/04/2017.
 */


@Entity
public class Produtos extends Model{

    @Id
    public long id;
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