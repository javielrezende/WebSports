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
 * Created by Miguel, Roger, William on 03/05/2017.
 *
 * Clase para criação do banco de dados
 *
 * Tabela Reserva
 *
 * Entity - para definir o tipo de clase;
 *
 * Id - Id da tabela;
 *
 * ManyToOne - Muitos para Um;
 *
 * JoinColumn - para fazer as ligações entre tabelas;
 *
 * Formats.DataTime - Formato de data;
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
