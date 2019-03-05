package danielfnz.com.br.ecommerce.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import danielfnz.com.br.ecommerce.R;

public class CompraEfetuadaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra_efetuada);
    }

    public void voltaHome(View view) {
        finish();
    }
}
