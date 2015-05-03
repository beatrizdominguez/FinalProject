package com.closet.beatriz.closet;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;


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
        }

        return super.onOptionsItemSelected(item);
    }

    private Bitmap StringToBitmap(String image) {

        byte[] decodedString = null;
        decodedString = Base64.decode(image, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return bitmap;
    }
}
