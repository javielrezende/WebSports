package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by William on 03/05/2017.
 */
@Entity
public class Pais extends Model{
    @Id
    public Long id;
    public String nome;
}
