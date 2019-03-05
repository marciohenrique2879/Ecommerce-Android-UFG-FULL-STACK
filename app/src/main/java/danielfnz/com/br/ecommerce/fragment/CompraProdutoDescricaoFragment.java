package danielfnz.com.br.ecommerce.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import danielfnz.com.br.ecommerce.R;
import danielfnz.com.br.ecommerce.activity.CarrinhoActivity;
import danielfnz.com.br.ecommerce.model.ItemCarrinho;
import danielfnz.com.br.ecommerce.model.ProdutoParcel;
import danielfnz.com.br.ecommerce.util.Util;

import static danielfnz.com.br.ecommerce.util.Util.CIFRA_DINHEIRO;
import static danielfnz.com.br.ecommerce.util.Util.formataVirgula;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompraProdutoDescricaoFragment extends Fragment {


    EditText editQuantidadeDeItens;
    TextView valorTotal;
    ProdutoParcel produtoParcel;

    public CompraProdutoDescricaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_compra_produto_descricao, container, false);

        produtoParcel = getArguments().getParcelable("produto");

        TextView preco = (TextView) view.findViewById(R.id.precoProdutoDetalhe);
        valorTotal = (TextView) view.findViewById(R.id.valorTotalProdutoDetalhe);

        editQuantidadeDeItens = (EditText) view.findViewById(R.id.editQuantidadeDeItens);
        editQuantidadeDeItens.setText("1");

        Button botaoDiminuirQuantidadeItens = (Button) view.findViewById(R.id.botaoDiminuir1Item),
                botaoAumentarQuantidadeItens = (Button) view.findViewById(R.id.botaoAumentar1Item),
                botaoLancarItem = (Button) view.findViewById(R.id.adicionarProdutoNoCarrinho);


        preco.setText(new StringBuilder(CIFRA_DINHEIRO).append(formataVirgula(produtoParcel.getPreco())));
        valorTotal.setText(new StringBuilder(CIFRA_DINHEIRO).append(formataVirgula(produtoParcel.getPreco())));

        botaoDiminuirQuantidadeItens.setOnClickListener(diminuirUmItem());
        botaoAumentarQuantidadeItens.setOnClickListener(aumentarUmItem());
        botaoLancarItem.setOnClickListener(lancarItemNoCarrinho());

        return view;
    }

    private View.OnClickListener lancarItemNoCarrinho() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemCarrinho itemCarrinho = new ItemCarrinho(produtoParcel.getNome(),
                        Integer.parseInt(editQuantidadeDeItens.getText().toString()), produtoParcel.getPreco(),
                        (Util.convertToNumber(valorTotal.getText().toString())), produtoParcel.getImagem());

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("carrinho_preferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String listaExistente = sharedPreferences.getString("carrinho_list", null);
                Type type = new TypeToken<ArrayList<ItemCarrinho>>() {}.getType();
                List<ItemCarrinho> listaItens = new ArrayList<>();
                if(listaExistente != null) {
                    listaItens.addAll((Collection<? extends ItemCarrinho>) gson.fromJson(listaExistente, type));
                }
                Log.i("testando", listaItens.toString());
                listaItens.add(itemCarrinho);
                editor.putString("carrinho_list", gson.toJson(listaItens));
                editor.apply();
                getActivity().startActivity(new Intent(getActivity(), CarrinhoActivity.class));
                getActivity().finish();
            }
        };
    }


    private View.OnClickListener diminuirUmItem() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editQuantidadeDeItens.getText().toString().equals("1")) {
                    editQuantidadeDeItens.setText(String.valueOf(Integer.parseInt(editQuantidadeDeItens.getText().toString()) - 1));
                    recalculaValores();
                }
            }
        };

    }


    private View.OnClickListener aumentarUmItem() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editQuantidadeDeItens.setText(String.valueOf(Integer.parseInt(editQuantidadeDeItens.getText().toString()) + 1));
                recalculaValores();
            }
        };
    }

    private void recalculaValores() {
        valorTotal.setText(new StringBuilder(CIFRA_DINHEIRO).append(formataVirgula(produtoParcel.getPreco() * Integer.parseInt(editQuantidadeDeItens.getText().toString()))).toString());
    }

}
