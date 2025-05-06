package com.example.expt7;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageSlider imageSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageSlider = findViewById(R.id.image_slider);

        ArrayList<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.one, null));
        imageList.add(new SlideModel(R.drawable.two, null));
        imageList.add(new SlideModel(R.drawable.three, null));
        imageList.add(new SlideModel(R.drawable.four, null));
        imageList.add(new SlideModel(R.drawable.five, null));
        imageList.add(new SlideModel(R.drawable.six, null));

        imageSlider.setImageList(imageList);
    }
}
