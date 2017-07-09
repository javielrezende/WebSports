package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Funcionario extends Model{
    @Id
    public Integer id;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    public Usuario usuario_id;
    @ManyToOne
    @JoinColumn(name = "cargo_id")
    public Cargo cargo_id;

    public static Finder<Integer,Funcionario> find = new Finder<>(Funcionario.class);

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = Usuario.find.byId(usuario_id);
    }
    public void setCargo_id(int cargo_id) {
        this.cargo_id = Cargo.find.byId(cargo_id);
    }

    // Retorna true se esta lista estiver vazia e false se contiver algum objeto armazenado
    public boolean isEmpty(){
        List<Funcionario> listFuncionario = Funcionario.find.all();
        return listFuncionario.isEmpty();
    }
}
