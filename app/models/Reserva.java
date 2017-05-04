package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by William on 02/05/2017.
 */
@Entity
public class Reserva extends Model{
    @Id
    public Long id;
    @Formats.DateTime(pattern="dd-MM-yyyy")
    public Date dataReserva;
    @Formats.DateTime(pattern="dd-MM-yyyy")
    public Date dataEntrada;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    public Usuario usuario_id;
    @ManyToOne
    @JoinColumn(name = "quadra_id")
    public Quadra quadra_id;
    public Time qtdHoras;
    @ManyToOne
    @JoinColumn(name = "pagamento_id")
    public Pagamento pagamento_id;
}
