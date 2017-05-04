package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;

/**
 * Created by William on 02/05/2017.
 */
@Entity
public class Pagamento extends Model{
    @Id
    public Long id;
    public double valor;
    @Formats.DateTime(pattern="dd-MM-yyyy")
    public Date dataPagamento;
    @ManyToOne
    @JoinColumn(name = "tipoPagamento_id")
    public TipoPagamento tipoPagamento_id;
}
