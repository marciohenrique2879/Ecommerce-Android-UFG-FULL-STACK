package danielfnz.com.br.ecommerce.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import danielfnz.com.br.ecommerce.R;
import danielfnz.com.br.ecommerce.model.ItemCarrinho;
import danielfnz.com.br.ecommerce.model.Produto;
import danielfnz.com.br.ecommerce.util.Util;

import static danielfnz.com.br.ecommerce.util.Util.CIFRA_DINHEIRO;

public class CarrinhoAdapter extends RecyclerView.Adapter<CarrinhoAdapter.ViewHolder> {

    private Context context;
    private List<ItemCarrinho> carrinhoList;

    public CarrinhoAdapter(Activity context, List<ItemCarrinho> carrinhoList) {
        this.context = context;
        this.carrinhoList = carrinhoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carrinho, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemCarrinho itemCarrinho = carrinhoList.get(position);
        holder.imagem.setLayoutParams(new ConstraintLayout.LayoutParams(Util.dpToPx(context, 60),Util.dpToPx(context, 60)));
        holder.imagem.setImageResource(itemCarrinho.getImagem());
        holder.nome.setText(itemCarrinho.getProdutoNome());
        holder.preco.setText(new StringBuilder("Preco: ").append(CIFRA_DINHEIRO).append(Util.formataVirgula(itemCarrinho.getPrecoUnitario())));
        holder.quantidade.setText(new StringBuilder("Quantidade: ").append(itemCarrinho.getQuantidade()));
        holder.valorTotal.setText(new StringBuilder(CIFRA_DINHEIRO).append(Util.formataVirgula(itemCarrinho.getValortotal())));
    }

    @Override
    public int getItemCount() {
        return carrinhoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imagem;
        public TextView nome,
                preco,
                quantidade,
                valorTotal;

        public ViewHolder(View itemView) {
            super(itemView);
            imagem = (ImageView) itemView.findViewById(R.id.imgProdutoList);
            nome = (TextView) itemView.findViewById(R.id.nomeProdutoList);
            preco = (TextView) itemView.findViewById(R.id.precoProdutoList);
            quantidade = (TextView) itemView.findViewById(R.id.quantidadeProdutoList);
            valorTotal = (TextView) itemView.findViewById(R.id.valorTotalProdutoList);
        }
    }
}
