package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Miguel, Roger, William on 02/05/2017.
 *
 * Model criada para o TipoQuadra.
 */
@Entity
public class TipoQuadra extends Model{

    @Id
    public Integer id;
    public String descricao;
}
