package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by William on 02/05/2017.
 */
@Entity
public class TipoPagamento extends Model{
    @Id
    public Integer id;
    public String tipo;
}
