package com.example.mediplus.Database;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences userSession;
    SharedPreferences.Editor editor;
    Context context;

    //session users
    public static final String SESSION_USERSESSION ="userLoginSession";
    public static final String SESSION_REMEMBER_ME ="rememberMe";

    //usersession
    private static final String IS_LOGIN ="IsLoggedIn";
    public static final String KEY_FULLNAME="fullName";
    public static final String KEY_ADDRESS="address";
    public static final String KEY_EMAIL="email";
    public static final String KEY_PHONENUMBER="phoneNo";
    public static final String KEY_DATE="date";
    public static final String KEY_GENDER="gender";
    public static final String KEY_PASSWORD="password";


    //remember me
    private static final String IS_REMEMBER_ME="IsLRememberMe";
    public static final String KEY_RMPHONENUMBER="phoneNo";
    public static final String KEY_RMPASSWORD="password";


   public SessionManager(Context _context, String sessionName){

       context =_context;
       userSession= _context.getSharedPreferences("userLoginSession",Context.MODE_PRIVATE);
       editor=userSession.edit();


   }

   public void createLoginSession(String fullName, String address, String email ,String phoneNo ,String date ,String gender, String password ){

       editor.putBoolean(IS_LOGIN, true);

       editor.putString(KEY_FULLNAME, fullName);
       editor.putString(KEY_ADDRESS, address);
       editor.putString(KEY_EMAIL, email);
       editor.putString(KEY_PHONENUMBER, phoneNo);
       editor.putString(KEY_DATE, date);
       editor.putString(KEY_GENDER, gender);
       editor.putString(KEY_PASSWORD, password);

       editor.commit();
   }

   public HashMap<String, String> getUserDetailFromSession(){
       HashMap<String, String> userData =new HashMap<String, String>();

       userData.put(KEY_FULLNAME, userSession.getString(KEY_FULLNAME,null));
       userData.put(KEY_ADDRESS, userSession.getString(KEY_ADDRESS,null));
       userData.put(KEY_EMAIL, userSession.getString(KEY_EMAIL,null));
       userData.put(KEY_PHONENUMBER, userSession.getString(KEY_PHONENUMBER,null));
       userData.put(KEY_GENDER, userSession.getString(KEY_GENDER,null));
       userData.put(KEY_DATE, userSession.getString(KEY_DATE,null));
       userData.put(KEY_PASSWORD, userSession.getString(KEY_PASSWORD,null));

       return userData;



   }
    //usersession

    public boolean checkLogin(){
        if(userSession.getBoolean(IS_LOGIN, true)){
            return true;
        }else{
            return false;
        }
    }


    //rememberme
    public void createRememberMeSession(String phoneNo, String password){

        editor.putBoolean(IS_REMEMBER_ME, true);
        editor.putString(KEY_RMPHONENUMBER, phoneNo);
        editor.putString(KEY_RMPASSWORD, password);

        editor.commit();


    }
    public HashMap<String, String> getRememberMeSession(){
        HashMap<String, String> userData = new HashMap<>();

        userData.put(KEY_RMPHONENUMBER, userSession.getString(KEY_RMPHONENUMBER,null));
        userData.put(KEY_RMPASSWORD, userSession.getString(KEY_RMPASSWORD,null));

        return userData;

    }

    public boolean checkRememberMe(){
        if(userSession.getBoolean(IS_REMEMBER_ME, false)){
            return true;
        }else{
            return false;
        }
    }

   public void logoutFromSession(){

       editor.clear();
       editor.commit();
   }

}
