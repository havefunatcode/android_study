package com.example.customlistview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView list;

    String[] titles ;

    Integer[] images = {
            R.drawable.movie1,
            R.drawable.movie2,
            R.drawable.movie3,
            R.drawable.movie4,
            R.drawable.movie5,
            R.drawable.movie6,
            R.drawable.movie7,
            R.drawable.movie7,
            R.drawable.movie7,
            R.drawable.movie7};               //image의 id저장

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titles = getResources().getStringArray(R.array.titles);
        setContentView(R.layout.activity_main);
        CustomList adapter = new CustomList(MainActivity.this);
        list = findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), titles[+position], Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class CustomList extends ArrayAdapter<String>    {
        private final Activity context;
        String[] titles = getResources().getStringArray(R.array.titles);

        public CustomList(Activity context)    {
            super(context, R.layout.listitem, MainActivity.this.titles); //context, 형식, 데이터
            this.context = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            //데이터를 출력해야 할 위치(position)
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.listitem, null, true);     //새로 만든 뷰, 붙이는 위치는 널, 액티비티의 최상단에 객체로 등록

            ImageView imageView = rowView.findViewById(R.id.image);
            TextView title = rowView.findViewById(R.id.title);
            TextView rating = rowView.findViewById(R.id.rating);
            TextView genre = rowView.findViewById(R.id.genre);
            TextView year = rowView.findViewById(R.id.releaseYear);

            title.setText(titles[position]);
            imageView.setImageResource(images[position]);
            rating.setText("9.0" + position);
            genre.setText("DRAMA");
            year.setText(1930 + position + " ");
            return rowView;
        }
    }
}
