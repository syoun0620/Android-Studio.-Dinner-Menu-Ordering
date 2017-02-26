package com.example.davidsyoun.fixedpricemeal;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityAppetizer extends AppCompatActivity {

    private ListView lv;
    private CustomAdaptor customAdapter;
    private ArrayList<ImageModel> imageModelArrayList;

    private int[] myImageList = new int[]{R.mipmap.snail, R.mipmap.rattlesnake, R.mipmap.beetle,
            R.mipmap.snail,R.mipmap.snail};

    private String[] myImageNameList = new String[]{"Bacon Wrapped Snails", "Fried Beetles",
            "Saute Rattlesnake","Hot and Spicy Chicken Feet","Stuffed Blobfish"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appetizer);

        lv = (ListView) findViewById(R.id.listView);

        imageModelArrayList = populateList();
        Log.d("hjhjh",imageModelArrayList.size()+"");
        customAdapter = new CustomAdaptor(this,imageModelArrayList);
        lv.setAdapter(customAdapter);

        // set up event listening for clicks on the list
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
                handleClick(index);  //index is index # of item in listview (position)
            }
        });
    }

    /*
     * Handles a click on the list item at the given 0-based index.
     * Speaks the given insult aloud using text-to-speech.
     */
    private void handleClick(int index) {
        String textAppetizer = myImageNameList[index];
        Toast.makeText(getApplicationContext(), "You have chosen: " + textAppetizer, Toast.LENGTH_SHORT).show();
        Intent intentAppetizer = new Intent(this, MainActivity.class);
        intentAppetizer.putExtra("appetizer", textAppetizer);
        setResult(Activity.RESULT_OK, intentAppetizer);
        finish();
    }

    private ArrayList<ImageModel> populateList(){

        ArrayList<ImageModel> list = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            ImageModel imageModel = new ImageModel();
            imageModel.setName(myImageNameList[i]);
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }
        return list;
    }
}


