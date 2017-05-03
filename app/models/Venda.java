package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;

/**
 * Created by William on 03/05/2017.
 */
@Entity
public class Venda extends Model{

    @Id
    public Long id;
    public Date dataCompra;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    public Usuario usuario_id;
    @ManyToOne
    @JoinColumn(name = "pagamento_id")
    public Pagamento pagamento_id;
    public double valorTotal;
    @ManyToOne
    @JoinColumn(name = "copa_id")
    public Copa copa_id;
}
