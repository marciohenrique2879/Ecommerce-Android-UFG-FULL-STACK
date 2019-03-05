package danielfnz.com.br.ecommerce.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import danielfnz.com.br.ecommerce.R;
import danielfnz.com.br.ecommerce.model.Produto;
import danielfnz.com.br.ecommerce.model.ProdutoParcel;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetalheProdutoDescricaoFragment extends Fragment {


    public DetalheProdutoDescricaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalhe_produto_descricao, container, false);

        ProdutoParcel produtoParcel = getArguments().getParcelable("produto");

        TextView descricao = (TextView) view.findViewById(R.id.textDescricaoProduto);
        descricao.setText(produtoParcel.getDescricao());

        return view;
    }

}
