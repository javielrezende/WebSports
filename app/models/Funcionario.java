package models;

import be.objectify.deadbolt.java.models.Permission;
import be.objectify.deadbolt.java.models.Role;
import be.objectify.deadbolt.java.models.Subject;
import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Funcionario extends Model implements Subject {
    @Id
    public Integer id;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    public Usuario usuario_id;
    @ManyToOne
    @JoinColumn(name = "cargo_id")
    public Cargo cargo_id;

    @ManyToMany
    public List<SecurityRole> roles;

    @ManyToMany
    public List<UserPermission> permissions;

    public static Finder<Integer, Funcionario> find = new Finder<>(Funcionario.class);

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = Usuario.find.byId(usuario_id);
    }

    public void setCargo_id(int cargo_id) {
        this.cargo_id = Cargo.find.byId(cargo_id);
    }

    // Retorna true se esta lista estiver vazia e false se contiver algum objeto armazenado
    public boolean isEmpty() {
        List<Funcionario> listFuncionario = Funcionario.find.all();
        return listFuncionario.isEmpty();
    }

    public static Funcionario findByEmail(String email) {
        return find.fetch("usuario_id")
                .fetch("usuario_id.endereco_id").
                        where()
                .eq("usuario_id.email",
                        email)
                .findUnique();
    }

    @Override
    public List<? extends Role> getRoles() {
        return roles;
    }

    @Override
    public List<? extends Permission> getPermissions() {
        return permissions;
    }

    @Override
    public String getIdentifier() {
        return usuario_id.nome;
    }
}
