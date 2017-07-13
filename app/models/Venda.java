package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author Miguel, Roger, William
 *
 * Model criada para tabela Venda.
 */
@Entity
public class Venda extends Model{

    @Id
    public Integer id;
    @Formats.DateTime(pattern="dd-MM-yyyy")
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
