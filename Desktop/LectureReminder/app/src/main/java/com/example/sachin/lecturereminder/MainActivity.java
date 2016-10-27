package com.example.sachin.lecturereminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sachin.lecturereminder.dbModel.classData;
import com.example.sachin.lecturereminder.dbModel.userData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity  {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;
    private int tabPosition,modifyButtonPosition;
    private ArrayList<ArrayList<classData>> fragmentData;
    private ArrayList<classData> pastData,todaysData,futureData;
    private ProgressDialog dialog;
    private static final int INITIAL_TAB_POSITION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**Check if user registered or not*/
        SharedPreferences preferences = getSharedPreferences("userData",MODE_PRIVATE);
        boolean isUserExist = preferences.getBoolean("userExist",false);
        if(!isUserExist) {
            if(savedInstanceState == null){
                Intent intent = new Intent(this,RegistrationActivity.class);
                startActivity(intent);
            }
        }

        /**set up toolbar*/
        final Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home Screen");

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);

        /**initial tab position*/
        tabPosition = INITIAL_TAB_POSITION;
        /**set up UI*/
        setUpUi(tabPosition);
    }

    /**Home screen UI Set up*/
    private void setUpUi(int position){

        /**set up Navigation drawer*/
        NavigationDrawerFragment fragment = new NavigationDrawerFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.drawer_fragment_layout,fragment);
        fragmentTransaction.addToBackStack("DrawerLayout");
        fragmentTransaction.commit();

        pastData = new ArrayList<classData>();
        todaysData = new ArrayList<classData>();
        futureData = new ArrayList<classData>();

        getDataFromDatabase();

        fragmentData = new ArrayList<ArrayList<classData>>();
        fragmentData.add(pastData);
        fragmentData.add(todaysData);
        fragmentData.add(futureData);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        ArrayList<String> tabs = new ArrayList<String>();
        tabs.add("PAST CLASSES");
        tabs.add("TODAY CLASSES");
        tabs.add("FUTURE CLASSES");
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),tabs);
        viewPager.setAdapter(adapter);
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        viewPager.setCurrentItem(position);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabPosition = tab.getPosition();
                //Toast.makeText(MainActivity.this,""+tabPosition,Toast.LENGTH_SHORT).show();
                super.onTabSelected(tab);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_screen_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.insert:
                Intent intent = new Intent(MainActivity.this,AddClassActivity.class);
                intent.putExtra("operation","add");
                startActivity(intent);
                break;
            case R.id.add_user:
                Intent registrationIntent = new Intent(MainActivity.this,RegistrationActivity.class);
                startActivity(registrationIntent);
                break;
            case R.id.change_user:
                changeUser();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    /** getting email from user and chane account */
    public void changeUser() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.change_user_layout, null);
        final EditText userName = (EditText) alertLayout.findViewById(R.id.username);

        Log.i("MainActivity","in change user");
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Login");
        alert.setView(alertLayout);
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });
        alert.setPositiveButton("Login", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String email = userName.getText().toString();
                changeUserData(email);
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    public void changeUserData(String email){
        DatabaseAccess access = DatabaseAccess.getDatabaseAccess(MainActivity.this);
        boolean isRegistered = access.checkUserAlreadyRegiseterd(email);
        if(!isRegistered){
            Toast.makeText(MainActivity.this,"This email is not registered",Toast.LENGTH_SHORT).show();
        }else {
            List<userData> users = access.getAllUserData(email);
            SharedPreferences sharedPref = getSharedPreferences("userData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("name",users.get(0).getName());
            editor.putString("className",users.get(0).getClassName());
            editor.putString("email",users.get(0).getEmail());
            editor.putString("mobile",users.get(0).getMobile());
            editor.putString("bloodGroup",users.get(0).getBloodGroup());
            editor.commit();
            setUpUi(INITIAL_TAB_POSITION);
        }
    }

    /**get data from database*/
    public void getDataFromDatabase(){
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setTitle("Please wait....");
        dialog.setMessage("Loading Data..");
        dialog.setIndeterminate(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        DatabaseAccess databaseAccess = DatabaseAccess.getDatabaseAccess(MainActivity.this);
        SharedPreferences preferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        String email = preferences.getString("email","sachinyedle@gmail.com");
        long id = databaseAccess.getIdWithEmail(email);

        List<classData> alldata = databaseAccess.getAllData(id);
        /*Collections.sort(alldata, new Comparator<classData>() {
            public int compare(classData o1, classData o2) {
                if (o1.getDateTime() == null || o2.getDateTime() == null)
                    return 0;
                return o1.getDateTime().compareTo(o2.getDateTime());
            }
        });*/
        Date newDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.US);

        for (int i =0;i < alldata.size();i++){
            String currentDateFormat = simpleDateFormat.format(newDate);
            String unknownDateFormat = simpleDateFormat.format(alldata.get(i).getDateTime());

            Date currentDate = null;
            Date unknownDate = null;
            try {
                currentDate = simpleDateFormat.parse(currentDateFormat);
                unknownDate = simpleDateFormat.parse(unknownDateFormat);
            } catch (ParseException e) {
                Log.e("Date pasing Error",e.getLocalizedMessage(),e);
            }
            if(currentDate.after(unknownDate)){
                pastData.add(alldata.get(i));
            }else if(currentDate.before(unknownDate)){
                futureData.add(alldata.get(i));
            }else if(currentDate.equals(unknownDate)){
                boolean isPast = compareTime(alldata.get(i).getDateTime());
                if(isPast){
                    pastData.add(alldata.get(i));
                }
                else {
                    todaysData.add(alldata.get(i));
                }
            }
        }
        reversePastData(pastData);
        dialog.dismiss();
    }

    public boolean compareTime(Date dateTime){
        boolean isPast = false;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.US);

        long milliDate = dateTime.getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(milliDate);
        String dateAndTime = format.format(cal.getTime());
        String formatedtime = dateAndTime.substring(11,16);
        Log.i("Time",formatedtime);

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date dateCompare = null;
        Date currentTime = null;
        try {
            dateCompare = dateFormat.parse(formatedtime);
            currentTime = dateFormat.parse(dateFormat.format(new Date()));
        } catch (ParseException e) {
            Log.e("Date pasing eror",e.getLocalizedMessage(),e);
        }
        if(currentTime.after(dateCompare))
        {
            isPast = true;
        }
        return isPast;
    }

    /**revers ascending order data for past classes tab*/
    public void reversePastData(ArrayList<classData> classDatas){
        ArrayList<classData> pastDataReverse = new ArrayList<classData>();
        for(int i = (classDatas.size() - 1);i >= 0; i--){
            pastDataReverse.add(classDatas.get(i));
        }
        pastData = pastDataReverse;
    }

    public ArrayList<classData> getDataAt(int position){
        return fragmentData.get(position);
    }

    /**handling modify button clicks*/
    public void onModifyButtonClicked(View view,int recyclerViewPosition){
        showModifyPopup(view);
        modifyButtonPosition = recyclerViewPosition;
    }

    /**display pop up options when modify button clicked*/
    private void showModifyPopup(View v) {
        PopupMenu popup = new PopupMenu(MainActivity.this,v);
        popup.getMenuInflater().inflate(R.menu.button_popup_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.edit:
                        if(tabPosition == 0){
                            Toast.makeText(MainActivity.this, "Sorry You can not edit In this Tab", Toast.LENGTH_LONG).show();
                        }
                        else{
                            callToModify(tabPosition,modifyButtonPosition);
                        }

                        return true;
                    case R.id.delete:
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Are You sure...want to delete");
                        builder.setTitle("Delete Record")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        callToDelete(tabPosition,modifyButtonPosition);
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(MainActivity.this, "delete cancelled", Toast.LENGTH_SHORT).show();
                                    }
                                }).create();
                        builder.show();
                        return true;
                    case R.id.share:
                        callToShare(tabPosition,modifyButtonPosition);
                        //Toast.makeText(MainActivity.this, "share:tabPos:"+tabPosition+"\nModifyButtonPosition:"+modifyButtonPosition, Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.show();
    }

    /**Modify class Data */
    public void callToModify(int tabPosition, int modifyButtonPosition){
        ArrayList<classData> classDatas = fragmentData.get(tabPosition);
        classData data = classDatas.get(modifyButtonPosition);

        Date dateTime = data.getDateTime();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.US);
        long milliDate = dateTime.getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(milliDate);
        String dateAndTime = format.format(cal.getTime());

        String date = dateAndTime.substring(0,10);
        String formatedtime = dateAndTime.substring(11,16);

        Intent intent = new Intent(MainActivity.this,AddClassActivity.class);
        intent.putExtra("operation","edit");
        intent.putExtra("id",data.getId().toString());
        intent.putExtra("name",data.getName());
        intent.putExtra("topic",data.getTopic());
        intent.putExtra("professor",data.getProfessor());
        intent.putExtra("date",date);
        intent.putExtra("time",formatedtime);
        intent.putExtra("location",data.getLocation());
        intent.putExtra("userDataId",""+data.getUserDataId());
        startActivity(intent);
    }

    /**Delete class data*/
    public void callToDelete(int tabPosition, int modifyButtonPosition) {
        ArrayList<classData> classDatas = fragmentData.get(tabPosition);
        classData data = classDatas.get(modifyButtonPosition);
        DatabaseAccess access = DatabaseAccess.getDatabaseAccess(MainActivity.this);
        access.delete(data.getId());
        setUpUi(tabPosition);
        String name = data.getName();
        String topic = data.getTopic();
        cancelAlarm(name,topic);
    }

    public void cancelAlarm(String className,String topic){
        Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        alarmIntent.putExtra("name",className);
        alarmIntent.putExtra("topic",topic);
        alarmIntent.setAction(className+topic);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_NO_CREATE);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }

    /**Share class data using other application*/
    public void callToShare(int tabPosition, int modifyButtonPosition){
        ArrayList<classData> classDatas = fragmentData.get(tabPosition);
        classData data = classDatas.get(modifyButtonPosition);
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        String textMessage = "Class Name: " + data.getName() +
                "\nTopic: " + data.getTopic() +
                "\nLocation: " + data.getLocation() +
                "\nProfessor: " + data.getProfessor() +
                "\nDate Time: "+data.getDateTime();
        shareIntent.putExtra(Intent.EXTRA_TEXT,textMessage);
        shareIntent.setType("text/plain");
        startActivity(Intent.createChooser(shareIntent,"Share Class"));
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            moveTaskToBack(true);
        }
    }
}
