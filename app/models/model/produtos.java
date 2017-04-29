package models.model;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by William on 29/04/2017.
 */


@Entity
public class produtos extends Model{

    @Id
    public long id;
    public String nome;
    public double valorCompra;
    public double precoUnitario;
    public int quantidade;
    public int copa_id;
    public int categoria;

}
