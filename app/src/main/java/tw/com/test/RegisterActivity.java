package tw.com.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private EditText account, password;
    RequestQueue queue;
    //String url_login = "https://android-project.onrender.com/register";
    String url_login = "http://aba1one.ddns.net/register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        account = findViewById(R.id.registerAccount);
        password = findViewById(R.id.registerPassword);

        queue= Volley.newRequestQueue(this);
    }

    public void SwitchToLoginPage(View view) {
        finish();
    }

    public void register(View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_login, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("Lin", response);

                if (response.equals("Hello world")) {
                    Toast.makeText(RegisterActivity.this, "註冊成功", Toast.LENGTH_SHORT).show();
                }
                else if (response.equals("username " + account.getText().toString() + " already exist")) {
                    Toast.makeText(RegisterActivity.this, "已存在此帳號", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.v("Lin", "ErrorResponse");
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("username", account.getText().toString());
                map.put("password", password.getText().toString());
                Log.v("Lin", account.getText().toString() + ":" + password.getText().toString());
                return map;
            }
        };
        queue.add(stringRequest);
    }
}