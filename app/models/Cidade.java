package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Miguel, Roger, William *
 * Model que cria a tabela Cidade
 */
@Entity
public class Cidade extends Model{
    @Id
    public Integer id;
    public String nome;
    @ManyToOne
    @JoinColumn(name = "estado_id")
    public Estado estado_id;
    public static Finder<Integer,Cidade> find = new Finder<>(Cidade.class);
}
