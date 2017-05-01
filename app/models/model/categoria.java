package models.model;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by William on 29/04/2017.
 */

@Entity
public class categoria extends Model {

    @Id
    public long id;
    public String descricao;
}
