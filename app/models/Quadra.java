package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Miguel, Roger, William *
 * Model criada para tabela Quadra.
 */

@Entity
public class Quadra extends Model {

    @Id
    public Integer id;
    public double valorLocacao;
    @ManyToOne
    @JoinColumn(name="copa_id")
    public Copa copa_id;
    @ManyToOne
    @JoinColumn(name="tipoQuadra_id")
    public TipoQuadra tipoQuadra_id;


}
