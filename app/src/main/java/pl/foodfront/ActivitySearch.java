package pl.foodfront;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


public class ActivitySearch extends AppCompatActivity {
    class foods extends BaseAdapter{

        Food[] main;
        Activity a;


        public foods(Food[] t,Activity a){
            main=t;
            this.a=a;

        }


        @Override
        public int getCount() {
            return main.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final View v=a.getLayoutInflater().inflate(R.layout.grid_food_element,null);
            final ImageView im=(ImageView)v.findViewById(R.id.image);
            if(!main[position].clicked){
                im.setBackgroundColor(0xFFFFFFFF);
            }else{
                im.setBackgroundResource(R.drawable.button);
            }
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vv) {
                    main[position].click();

                    if(!main[position].clicked){
                        im.setBackgroundColor(0xFFFFFFFF);
                    }else{
                        im.setBackgroundResource(R.drawable.button);
                    }

                }
            });

            im.setImageDrawable(a.getResources().getDrawable((int) main[position].drawable));
            return v;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_search);
        foods f=new foods(Foods.getInstance().getFoods(),this);
        ((GridView)findViewById(R.id.foods)).setAdapter(f);

        findViewById(R.id.filtername).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Foods f = Foods.getInstance();
                f.filtername = !f.filtername;
                reAssign();
            }
        });

        findViewById(R.id.maplist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Foods f=Foods.getInstance();
                f.maplist=!f.maplist;
                reAssign();
            }
        });

    }

    public void reAssign(){
        Foods f=Foods.getInstance();
        findViewById(R.id.filtername).setBackgroundResource(f.filtername ? R.drawable.button_food : R.drawable.button_name);
        findViewById(R.id.maplist).setBackgroundResource(f.maplist?R.drawable.button_map:R.drawable.button_list);
        findViewById(R.id.mappart).setVisibility(f.maplist ? View.VISIBLE : View.INVISIBLE);
        findViewById(R.id.listpart).setVisibility(f.maplist ? View.INVISIBLE : View.VISIBLE);
        findViewById(R.id.filterpart).setVisibility(f.filtername ? View.VISIBLE : View.INVISIBLE);
        findViewById(R.id.namepart).setVisibility(f.filtername?View.INVISIBLE:View.VISIBLE);



    }


}
