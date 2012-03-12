package com.chips;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.chips.adapters.ExpandableFoodListAdapter;
import com.chips.dataclient.MealClient;
import com.chips.dataclientobservers.ExpandableMealClientObserver;
import com.chips.homebar.HomeBar;
import com.chips.homebar.HomeBarAction;
import com.chips.user.PersistentUser;

public class CustomizeActivity extends DataClientActivity implements HomeBar {
    private static final String BASE_URL 
        = "http://cs110chips.phpfogapp.com/index.php/mobile/";
    private static final String MEAL_LIST_FOOD_URL 
        = BASE_URL + "get_meal_with_id";
    private static final String ADD_FOOD_TO_MEAL_URL
        = BASE_URL + "add_food_to_inventory/";
    private static final String QUANTITY_UPDATE_URL 
          = BASE_URL + "set_quantity_of_food_in_inventory";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeBarAction.inflateHomeBarView(this, R.layout.customize_meal);
        
        selectedMeal = getIntent().getExtras().getInt("selectedMeal");
        Log.d("Selected meal: ", selectedMeal + "");
        
        mealClient = new MealClient();
        ExpandableMealClientObserver expandableMealClientObserver
            = new ExpandableMealClientObserver(this, mealClient);
        
        addClientObserverPair(mealClient, expandableMealClientObserver);
        
        ExpandableListView customizeView 
            = (ExpandableListView) findViewById(R.id.customizeListView);
        expandableMealClientObserver.setListViewLayout(
            customizeView, 
            new ExpandableFoodListAdapter(this, mealClient.getFirstMealFoods(), 
                    customizeView, QUANTITY_UPDATE_URL)
        );
       
        loadSelectedMealFromWebsite();
        
        setupIntents();
    }
    
    private void loadSelectedMealFromWebsite() {
        ArrayList<String> arguments = new ArrayList<String>();
        arguments.add(PersistentUser.getSessionID());
        arguments.add(selectedMeal + "");
        
        mealClient.setURL(MEAL_LIST_FOOD_URL, arguments);
        mealClient.logURL();
        mealClient.asynchronousLoadClientData();
    }
    
    private void setupIntents() {
        Bundle b = new Bundle();
        b.putString(AddFoodActivity.BUNDLE_ADD_KEY, 
                ADD_FOOD_TO_MEAL_URL);

        addFoodToMealIntent 
            = new Intent(this, AddFoodActivity.class);
        addFoodToMealIntent.putExtras(b);
    }
    
    public void addFoodToInventoryClicked(View view) {
        startActivity(addFoodToMealIntent);
    }
    
    public void goHomeClicked(View view) {
        HomeBarAction.goHomeClicked(this, view);
    }
    
    public void addFavoriteClicked(View view) {
        HomeBarAction.addFavoriteClicked(this, view);
    }
    
    private Intent addFoodToMealIntent;
    private int selectedMeal;
    private MealClient mealClient;
}
