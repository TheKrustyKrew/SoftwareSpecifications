import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import static com.example.chris.thekrustykrew.MainActivity.clubUsers;
import static com.example.chris.thekrustykrew.MainActivity.clubAdmins;
import static com.example.chris.thekrustykrew.MainActivity.clubcount;
import static com.example.chris.thekrustykrew.MainActivity.admincount;
import static com.example.chris.thekrustykrew.MainActivity.groupEntries;
import static com.example.chris.thekrustykrew.MainActivity.journalcount;
import static com.example.chris.thekrustykrew.MainActivity.loggedinuser;
import static com.example.chris.thekrustykrew.MainActivity.selectedEntry;
import static com.example.chris.thekrustykrew.MainActivity.selectedFish;

public class Login extends AppCompatActivity{
	EditText unEditText = (EditText) findViewById(R.id.Username);
    String userNameValue = unEditText.getText().toString();

    EditText pwEditText = (EditText) findViewById(R.id.Password);
    String passwordValue = pwEditText.getText().toString();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    
    public void onButtonClick(View v) {
    	Boolean testAdminUser = false;
    	Boolean testAccountUser = false;
    	
        for(int i = 0; i < clubcount; i++){
            if(clubUsers[i].getUsername().equals(userNameValue)){
                if(clubUsers[i].getPassword().equals(passwordValue)){
                    testAccountUser = true;
                    loggedinuser = clubUsers[i].getUsername();
                    break;
                }
            }
        }

        return testAccountUser;
    	
        for(int i = 0; i < admincount; i++){
            if(clubAdmins[i].getUsername().equals(userNameValue)){
                if(clubAdmins[i].getPassword().equals(passwordValue)){
                    testAdminUser = true;
                    loggedinuser = clubAdmins[i].getUsername();
                    break;
                }
            }
        }

        return testAdminUser;
        
    	if (v.getId() == R.id.) {
    		String password = searchPass(userNameValue);
    		if(passwordValue.equals(password) && testAccountUser) {
    			Intent i = new Intent(this, MainMenu.class);
    			i.putExtra("Username", userNameValue);
    			startActivity(i);
    		}
    		else if(passwordValue.equals(password) && testAdminUser) {
    			Intent i = new Intent(this, AdminMenu.class);
    			i.putExtra("Username", userNameValue);
    			startActivity(i);
    		}
    		else {
    			Toast error = Toast.makeText(Login.this, "Incorrect username or password", 
    					Toast.LENGTH_SHORT);
    			error.show();
    		}
    	}
    }
    
    public String searchPass(String uname){
        db = this.getReadableDatabase();
        String query = "select uname, Password from" + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        String a,b;
        b = "Not Found";
        if(cursor.moveToFirst()){
            do {
                a = cursor.getString(0);

                if(a.equals(uname)){
                    b = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }

        return b;
    }

    public void toHomePage(View view){
        if(verifyAdmin()){
            Intent intent = new Intent(this, AdminMenu.class);
            startActivity(intent);
        }
        if(verifyAccount()){
            Intent intent = new Intent(this, MainMenu.class);
            startActivity(intent);
        }
    }

    public void toRegisterPage(View view){
        Intent intent = new Intent(this, RegisterUser.class);
        startActivity(intent);
    }


}



