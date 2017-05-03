package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by William on 02/05/2017.
 */
@Entity
public class TipoQuadra extends Model{

    @Id
    public Long id;
    public String descricao;
}
