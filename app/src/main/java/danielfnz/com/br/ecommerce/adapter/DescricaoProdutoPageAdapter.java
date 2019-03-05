package danielfnz.com.br.ecommerce.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import danielfnz.com.br.ecommerce.fragment.CompraProdutoDescricaoFragment;
import danielfnz.com.br.ecommerce.fragment.DetalheProdutoDescricaoFragment;
import danielfnz.com.br.ecommerce.model.Produto;
import danielfnz.com.br.ecommerce.model.ProdutoParcel;

public class DescricaoProdutoPageAdapter extends FragmentPagerAdapter {

    int numberOfTabs;
    ProdutoParcel produtoParcel;

    public DescricaoProdutoPageAdapter(FragmentManager fm, int numberOfTabs, ProdutoParcel produtoParcel) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
        this.produtoParcel = produtoParcel;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment page;
        Bundle bundle = new Bundle();
        bundle.putParcelable("produto", produtoParcel);
        switch (i) {
            case 0:
                page = new CompraProdutoDescricaoFragment();
                break;
            case 1:
                page = new DetalheProdutoDescricaoFragment();
                break;
            default:
                page = new CompraProdutoDescricaoFragment();
                break;
        }
        page.setArguments(bundle);
        return page;
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
