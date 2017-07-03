package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Miguel, Roger, William on 03/05/2017.
 *
 * Clase para criação do banco de dados
 *
 * Tabela Cargo
 *
 * Entity - para definir o tipo de clase;
 *
 * Id - Id da tabela;
 *
 */

@Entity
public class Cargo extends Model{

    @Id
    public Integer id;
    public String nome;
    public Double salario;

    public static Finder<Integer,Cargo> find = new Finder<>(Cargo.class);
}
