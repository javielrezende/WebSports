package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by William on 02/05/2017.
 */

@Entity
public class Quadra extends Model {

    @Id
    public Long id;
    public double valorLocacao;
    @ManyToOne
    @JoinColumn(name="copa_id")
    public Copa copa_id;

    public TipoQuadra tipoQuadra_id;


}
