package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.libs.Time;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Calendar;

/**
 * @author Miguel, Roger, William *
 * Model criada para tabela Reserva.
 */
@Entity
public class Reserva extends Model {
    @Id
    public Integer id;
    @Formats.DateTime(pattern="dd-MM-yyyy")
    public Calendar dataReserva;
    @Formats.DateTime(pattern="dd-MM-yyyy")
    public Calendar dataEntrada;

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
