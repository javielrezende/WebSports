package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by William on 03/05/2017.
 */
@Entity
public class Cidade extends Model{
    @Id
    public Long id;
    public String nome;
    @ManyToOne
    @JoinColumn(name = "estado_id")
    public Estado estado_id;

}
