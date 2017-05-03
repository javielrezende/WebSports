package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by William on 03/05/2017.
 */
@Entity
public class Endereco extends Model{
    @Id
    public Long id;
    public String rua;
    public int numero;
    public String complemento;
    public Cidade cidade_id;
    public String cep;


}
