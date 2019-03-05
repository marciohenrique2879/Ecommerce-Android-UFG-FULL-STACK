package danielfnz.com.br.ecommerce.model;

import java.util.UUID;

/**
 * Created by Alunoinf_2 on 02/02/2019.
 */

public class Produto {

    private String id;
    private String nome;
    private double preco;
    private int quantidade;
    private int imagem;
    private String descricao;
    private String codigoBarras;

    public Produto() {

    }

    public Produto(String id, String nome, double preco, int quantidade, int imagem, String descricao, String codigoBarras) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.imagem = imagem;
        this.descricao = descricao;
        this.codigoBarras = codigoBarras;
    }

    public Produto(String nome, double preco, int quantidade, int imagem, String descricao, String codigoBarras) {
        this.codigoBarras = codigoBarras;
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.imagem = imagem;
        this.descricao = descricao;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", imagem=" + imagem +
                ", descricao='" + descricao + '\'' +
                ", codigoBarras='" + codigoBarras + '\'' +
                '}';
    }
}
