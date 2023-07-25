package tw.com.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class LoginActivity extends AppCompatActivity {
    private EditText account, password;
    RequestQueue queue;
    //String url_login = "https://android-project.onrender.com/login";
    String url_login = "http://aba1one.ddns.net/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        account = findViewById(R.id.account);
        password = findViewById(R.id.password);

        queue= Volley.newRequestQueue(this);
//        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putString("account","222");
//        intent.putExtras(bundle);
//        startActivity(intent);
    }

    public void login(View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_login, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("Lin", response);

                if (response.equals("Hello world")) {
                    Toast.makeText(LoginActivity.this, "登入成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("account",account.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else if (response.equals("no such username " + account.getText().toString())){
                    Toast.makeText(LoginActivity.this, "無此帳號", Toast.LENGTH_SHORT).show();
                }
                else if (response.equals("wrong password")) {
                    Toast.makeText(LoginActivity.this, "密碼錯誤", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.v("Lin", "ErrorResponse:" + error.getMessage());
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
//        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putString("account",account.getText().toString());
//        intent.putExtras(bundle);
//        startActivity(intent);
    }

    public void SwitchToRegisterPage(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}