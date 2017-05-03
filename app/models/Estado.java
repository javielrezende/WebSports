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
public class Estado extends Model{
    @Id
    public Long id;
    public String nome;
    public Character sigla;
    @ManyToOne
    @JoinColumn(name = "pais_id")
    public Pais pais_id;
}
