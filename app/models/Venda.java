package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Miguel, Roger, William on 03/05/2017.
 *
 * Clase para criação do banco de dados
 *
 * Tabela Venda
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
