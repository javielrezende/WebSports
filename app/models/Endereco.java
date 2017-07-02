package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Miguel, Roger, William on 03/05/2017.
 *
 * Clase para criação do banco de dados
 *
 * Tabela Endereco
 *
 * @Entity - para definir o tipo de clase;
 *
 * @Id - Id da tabela;
 *
 * @ManyToOne - Muitos para Um;
 *
 * @JoinColumn - para fazer as ligações entre tabelas;
 */
@Entity
public class Endereco extends Model{
    @Id
    public Integer id;

    public String rua;
    public int numero;
    public String bairro;
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
