package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by William on 03/05/2017.
 */
@Entity
public class Cargo extends Model{

    @Id
    public Integer id;
    public String nome;
    public Double salario;
}
