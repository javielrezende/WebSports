package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
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

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Cidade getCidade_id() {
        return cidade_id;
    }

    public void setCidade_id(Cidade cidade_id) {
        this.cidade_id = cidade_id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
