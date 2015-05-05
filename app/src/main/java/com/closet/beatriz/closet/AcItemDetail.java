package com.closet.beatriz.closet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;


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

                Toast.makeText(AcItemDetail.this, "img grande", Toast.LENGTH_SHORT).show();

                Intent intentPhoto = new Intent(AcItemDetail.this, AcItemPhoto.class);
                Bundle mBundle = new Bundle();
                //intentVer.putExtras("cat",category);
                mBundle.putSerializable("item", (Serializable) i);
                intentPhoto.putExtras(mBundle);
                startActivity(intentPhoto);
            }
        });
    }

    private void show(Item i) {

        ImageButton image = (ImageButton) findViewById(R.id.imgItem);
        TextView txtDesc = (TextView) findViewById(R.id.txtDesc2);
        TextView txtCat = (TextView) findViewById(R.id.txtCat2);
        TextView txtColor = (TextView) findViewById(R.id.txtColor2);
        TextView txtSize = (TextView) findViewById(R.id.txtSize2);
        TextView txtPrice = (TextView) findViewById(R.id.txtPrice2);
        TextView txtWhen = (TextView) findViewById(R.id.txtWhen2);
        TextView txtWhere = (TextView) findViewById(R.id.txtWhere2);

        Bitmap btm = StringToBitmap(i.getImage());
        image.setImageBitmap(btm);
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

            case (R.id.action_share):

              //  Toast.makeText(this, "share", Toast.LENGTH_LONG).show();

                Bitmap bitmap = StringToBitmap(i.getImage());

                shareImage(this, bitmap, "");

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private Bitmap StringToBitmap(String image) {

        byte[] decodedString = null;
        decodedString = Base64.decode(image, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return bitmap;
    }

    public static void shareImage(Context context, Bitmap bitmap, String text) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/*");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        File f = new File(Environment.getExternalStorageDirectory() + File.separator + "image.jpg");
        try {
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));
        Log.e("TAG", String.valueOf(Uri.fromFile(f)));
        //share.setPackage("com.whatsapp");
        context.startActivity(Intent.createChooser(share, "Share!"));
    }

}
