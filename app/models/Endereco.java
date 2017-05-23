package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

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

    public void setNumero(String numero) {

        this.numero = Integer.parseInt(numero);
    }
    public static Finder<Integer,Endereco> find = new Finder<>(Endereco.class);

    public void setCidade_id(String cidade_id) {
        this.cidade_id = Cidade.find.byId(Integer.parseInt(cidade_id));
    }

    // Retorna true se esta lista estiver vazia e false se contiver algum objeto armazenado
    public boolean isEmpty(){
        List<Endereco> listEndereco = Endereco.find.all();
        return listEndereco.isEmpty();
    }

}
