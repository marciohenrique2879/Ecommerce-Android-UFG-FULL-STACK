package danielfnz.com.br.ecommerce.model;

public class ItemCarrinho {
    private String produtoNome;
    private int quantidade;
    private double precoUnitario;
    private double valortotal;
    private int imagem;

    public ItemCarrinho() {
    }

    public ItemCarrinho(String produtoNome, int quantidade, double precoUnitario, double valortotal, int imagem) {
        this.produtoNome = produtoNome;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.valortotal = valortotal;
        this.imagem = imagem;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public double getValortotal() {
        return valortotal;
    }

    public void setValortotal(double valortotal) {
        this.valortotal = valortotal;
    }


    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    @Override
    public String toString() {
        return "ItemCarrinho{" +
                "produtoNome='" + produtoNome + '\'' +
                ", quantidade=" + quantidade +
                ", precoUnitario=" + precoUnitario +
                ", valortotal=" + valortotal +
                ", imagem=" + imagem +
                '}';
    }
}
