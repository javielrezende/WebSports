package models.model;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;


/**
 * Created by William on 01/05/2017.
 */
@Entity
public class ProdutoSaida extends Model{

    @Id
    public long id;
    public int quantidade;
    public double valor;
    public Date dataSaida;
    @ManyToOne
    @JoinColumn(name="produtos_id")
    public Produtos produtos_id;
    


}
