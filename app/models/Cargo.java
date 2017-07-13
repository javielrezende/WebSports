package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Miguel, Roger, William *
 * Model do cargo onde cria a tabela de cargos.
 */
@Entity
public class Cargo extends Model{

    @Id
    public Integer id;
    public String nome;
    public Double salario;

    public static Finder<Integer,Cargo> find = new Finder<>(Cargo.class);
}
