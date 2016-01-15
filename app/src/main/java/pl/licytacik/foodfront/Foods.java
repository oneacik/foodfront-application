package pl.licytacik.foodfront;

/**
 * Created by oneat on 11/11/15.
 */

class Food{
    int drawable=0;
    String name="";
    int id=0;
    boolean clicked=true;

    Food(int drawable, String name){
        this.drawable=drawable;
        this.name=name;
    }

    Food(int drawable, int id){
        this.drawable=drawable;
        this.id=id;

    }

    public void click(){
        clicked=!clicked;
    }

}

public class Foods {

    public boolean maplist=true;
    public boolean filtername=true;

    private static Foods f=null;

    public class tuple{

        tuple(Object first,Object second){
            this.first=first;
            this.second=second;
        }

        public Object first;
        public Object second;
    }

    Food[] foods=new Food[]{

            new Food(R.drawable.food_pizza,0),
            new Food(R.drawable.food_burger,0),
            new Food(R.drawable.food_noodle,0),
            new Food(R.drawable.food_beer,0),
            new Food(R.drawable.food_kebab,0),
            new Food(R.drawable.food_fries,0),
            new Food(R.drawable.food_ice,0),
            new Food(R.drawable.food_drink,0)

    };

    Food[] getFoods(){

        return foods;

    }


    public static Foods getInstance(){
        if(f==null){
            f=new Foods();
        }
        return f;
    }

    

}
