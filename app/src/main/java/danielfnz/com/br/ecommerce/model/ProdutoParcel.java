package danielfnz.com.br.ecommerce.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProdutoParcel implements Parcelable {

    private String id;
    private String nome;
    private double preco;
    private int quantidade;
    private int imagem;
    private String descricao;

    public ProdutoParcel(String id, String nome, double preco, int quantidade, int imagem, String descricao) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.imagem = imagem;
        this.descricao = descricao;
    }

    public ProdutoParcel(Parcel in) {
        id = in.readString();
        nome = in.readString();
        preco = in.readDouble();
        quantidade = in.readInt();
        imagem = in.readInt();
        descricao = in.readString();
    }

    public static final Creator<ProdutoParcel> CREATOR = new Creator<ProdutoParcel>() {
        @Override
        public ProdutoParcel createFromParcel(Parcel in) {
            return new ProdutoParcel(in);
        }

        @Override
        public ProdutoParcel[] newArray(int size) {
            return new ProdutoParcel[size];
        }
    };

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

    public void setPreco(double preco) {
        this.preco = preco;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nome);
        dest.writeDouble(preco);
        dest.writeInt(quantidade);
        dest.writeInt(imagem);
        dest.writeString(descricao);
    }
}
