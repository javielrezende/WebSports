package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by Miguel, Roger, William on 03/05/2017.
 *
 * Clase para criação do banco de dados
 *
 * Tabela Cidade
 *
 * Entity - para definir o tipo de clase;
 *
 * Id - Id da tabela;
 *
 * ManyToOne - Muitos para Um;
 *
 * JoinColumn - para fazer as ligações entre tabelas;
 *
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
