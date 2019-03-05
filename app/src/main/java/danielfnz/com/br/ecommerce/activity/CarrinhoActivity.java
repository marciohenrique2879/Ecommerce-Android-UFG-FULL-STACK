package danielfnz.com.br.ecommerce.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import danielfnz.com.br.ecommerce.R;
import danielfnz.com.br.ecommerce.adapter.CarrinhoAdapter;
import danielfnz.com.br.ecommerce.model.ItemCarrinho;
import danielfnz.com.br.ecommerce.util.Util;

import static danielfnz.com.br.ecommerce.util.Util.CIFRA_DINHEIRO;

public class CarrinhoActivity extends AppCompatActivity {

    TextView qtdItens,
            valorTotal;

    List<ItemCarrinho> itensList;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        qtdItens = (TextView) findViewById(R.id.textItensCarrinho);
        valorTotal = (TextView) findViewById(R.id.textTotalCarrinho);
        itensList = new ArrayList<>();
        itensList.addAll(carregarItens());
        recyclerView = (RecyclerView) findViewById(R.id.recycleViewCarrinho);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, 1));
        adapter = new CarrinhoAdapter(this, itensList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void concluirCompra(View view) {
        sharedPreferences.edit().clear().apply();
        startActivity(new Intent(this, CompraEfetuadaActivity.class));
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();
        itensList = new ArrayList<>();
        itensList.addAll(carregarItens());
        adapter.notifyDataSetChanged();

        if(itensList != null) {
            double vTotal = 0;
            for(ItemCarrinho item: itensList) {
                vTotal = vTotal + item.getValortotal();
            }
            qtdItens.setText(itensList.size() + " itens");
            valorTotal.setText(new StringBuilder("Total: ").append(CIFRA_DINHEIRO).append(Util.formataVirgula(vTotal)).toString());
        }
    }

    private List<ItemCarrinho> carregarItens() {
        sharedPreferences = getSharedPreferences("carrinho_preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        List<ItemCarrinho> tempList = gson.fromJson(sharedPreferences.getString("carrinho_list", null), new TypeToken<ArrayList<ItemCarrinho>>() {}.getType());
        if (tempList == null) {
            return new ArrayList<>();
        } else {
            return tempList;
        }
    }



}