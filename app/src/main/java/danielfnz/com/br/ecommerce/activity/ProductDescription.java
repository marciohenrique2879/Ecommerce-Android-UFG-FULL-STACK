package danielfnz.com.br.ecommerce.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import danielfnz.com.br.ecommerce.R;
import danielfnz.com.br.ecommerce.adapter.DescricaoProdutoPageAdapter;
import danielfnz.com.br.ecommerce.model.Produto;
import danielfnz.com.br.ecommerce.model.ProdutoParcel;
import danielfnz.com.br.ecommerce.util.Util;

import static danielfnz.com.br.ecommerce.util.Util.CIFRA_DINHEIRO;
import static danielfnz.com.br.ecommerce.util.Util.formataVirgula;

public class ProductDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        ImageView imagem = (ImageView) findViewById(R.id.imgProdutoDetalhe);
        TextView preco = (TextView) findViewById(R.id.precoProdutoDetalhe),
        quantidade = (TextView) findViewById(R.id.quantidadeProdutoDetalhe),
        nome = (TextView) findViewById(R.id.nomeProdutoDescricao);

        ProdutoParcel produtoParcel = getIntent().getParcelableExtra("produto");

        imagem.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Util.dpToPx(this, 120)));
        imagem.setImageResource(produtoParcel.getImagem());
        preco.setText(new StringBuilder(CIFRA_DINHEIRO).append(formataVirgula(produtoParcel.getPreco())));
        quantidade.setText(String.valueOf(produtoParcel.getQuantidade()));
        nome.setText(produtoParcel.getNome());

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));
        tabLayout.addTab(tabLayout.newTab().setText("COMPRAR"));
        tabLayout.addTab(tabLayout.newTab().setText("DESCRICAO"));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        DescricaoProdutoPageAdapter pageAdapter = new DescricaoProdutoPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), produtoParcel);
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

}
