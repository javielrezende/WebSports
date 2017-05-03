package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by William on 03/05/2017.
 */
@Entity
public class Funcionario extends Model{
    @Id
    public Long id;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    public Usuario usuario_id;
    @ManyToOne
    @JoinColumn(name = "cargo_id")
    public Cargo cargo_id;
}
