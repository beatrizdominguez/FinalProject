package com.closet.beatriz.closet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class AcItemPhoto extends Activity {

    Item i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_item_photo);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ac_item_photo, menu);


        //category = getIntent().getExtras("cat");
        i = (Item) getIntent().getSerializableExtra("item");
        show(i);

        return true;
    }

    private void show(Item i) {

        ImageView image = (ImageView) findViewById(R.id.imageView);

        Bitmap btm = StringToBitmap(i.getImage());
        image.setImageBitmap(btm);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case (R.id.action_back):

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
