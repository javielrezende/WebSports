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
 * Tabela Estado
 */
@Entity
public class Estado extends Model{
    @Id
    public Integer id;
    public String nome;
    public String sigla;
    @ManyToOne
    @JoinColumn(name = "pais_id")
    public Pais pais_id;
}
