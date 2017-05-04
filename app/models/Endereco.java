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
public class Endereco extends Model{
    @Id
    public Integer id;
    public String rua;
    public int numero;
    public String complemento;
    @ManyToOne
    @JoinColumn(name = "cidade_id")
    public Cidade cidade_id;
    public String cep;


}
