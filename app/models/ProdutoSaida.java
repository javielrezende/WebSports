package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;

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
    public Integer id;
    public int quantidade;
    public double valor;
    @Formats.DateTime(pattern="dd-MM-yyyy")
    public Date dataSaida;
    @ManyToOne
    @JoinColumn(name="produtos_id")
    public Produtos produtos_id;



}
