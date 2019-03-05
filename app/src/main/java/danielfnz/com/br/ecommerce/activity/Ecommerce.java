package danielfnz.com.br.ecommerce.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import danielfnz.com.br.ecommerce.R;
import danielfnz.com.br.ecommerce.adapter.ProdutoAdapter;
import danielfnz.com.br.ecommerce.model.Produto;
import danielfnz.com.br.ecommerce.model.ProdutoParcel;

public class Ecommerce extends AppCompatActivity {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("produtos");
    ProdutoAdapter adapter;
    ListView lista;
    List<Produto> produtos;

    public static final int REQUEST_CODE = 100;
    public static final int PERMISSION_REQUEST = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecommerce);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarHome);
        toolbar.setTitle("Ecommerce");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar);

        lista = (ListView) findViewById(R.id.lista);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 produtos = new ArrayList<>();

                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Produto p = objSnapshot.getValue(Produto.class);
                    produtos.add(p);
                }

                adapter = new ProdutoAdapter(Ecommerce.this, produtos);
                lista.setAdapter(adapter);

                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(Ecommerce.this, ProductDescription.class);
                        i.putExtra("produto", new ProdutoParcel(produtos.get(position).getId(),
                                produtos.get(position).getNome(), produtos.get(position).getPreco()
                        , produtos.get(position).getQuantidade(), produtos.get(position).getImagem(),
                                produtos.get(position).getDescricao()));
                        startActivity(i);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.leitor_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.abrirLeitor) {
            Intent intent = new Intent(this, ScannerActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        } else if(item.getItemId() == R.id.abrirCarrinho) {
            startActivity(new Intent(this, CarrinhoActivity.class));
        }
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                Barcode barcode = data.getParcelableExtra("barcode");
                for(Produto produto: produtos) {
                    if(produto.getCodigoBarras().equals(barcode.displayValue)) {
                        Intent intent = new Intent(this, ProductDescription.class);
                        intent.putExtra("produto", new ProdutoParcel(produto.getId(),
                                produto.getNome(), produto.getPreco()
                                , produto.getQuantidade(), produto.getImagem(),
                                produto.getDescricao()));
                        return;
                    }
                }

                Toast.makeText(this,"Produto nao encontrado" , Toast.LENGTH_LONG).show();
            }
        }
    }
}
