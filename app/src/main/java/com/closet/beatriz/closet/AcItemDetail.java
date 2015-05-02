package com.closet.beatriz.closet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class AcItemDetail extends Activity {

    Item i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_item_detail);

        //category = getIntent().getExtras("cat");
        i = (Item) getIntent().getSerializableExtra("item");
        show(i);


        ImageButton img = (ImageButton) findViewById(R.id.imgItem);
        img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(AcItemDetail.this, "img grande",
                        Toast.LENGTH_SHORT).show();

                Intent intentPhoto = new Intent(AcItemDetail.this, AcItemPhoto.class);
                //intent.putExtra("Category", cat);
                startActivity(intentPhoto);
            }
        });
    }

    private void show(Item i) {

        TextView txtDesc = (TextView) findViewById(R.id.txtDesc2);
        TextView txtCat = (TextView) findViewById(R.id.txtCat2);
        TextView txtColor = (TextView) findViewById(R.id.txtColor2);
        TextView txtSize = (TextView) findViewById(R.id.txtSize2);
        TextView txtPrice = (TextView) findViewById(R.id.txtPrice2);
        TextView txtWhen = (TextView) findViewById(R.id.txtWhen2);
        TextView txtWhere = (TextView) findViewById(R.id.txtWhere2);

        txtDesc.setText(i.getDescription());
        txtCat.setText(i.getCategory());
        //txtColor;
        txtSize.setText(i.getSize());
        txtPrice.setText(String.valueOf(i.getPrize()) + " €");
        txtWhen.setText(i.getS_date());
        txtWhere.setText(i.getShop());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ac_item_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case (R.id.action_back):

                Toast.makeText(this, "close", Toast.LENGTH_LONG).show();
                this.finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
