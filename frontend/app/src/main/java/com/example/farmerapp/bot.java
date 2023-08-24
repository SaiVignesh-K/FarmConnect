package com.example.farmerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.farmerapp.Adaptor.MessageRVAdapter;
import com.example.farmerapp.data.Message;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class bot extends AppCompatActivity  {


    private RecyclerView chatsRV;
    private ImageView sendMsgIB;
    private EditText userMsgEdt;
    private ImageView ivBack,goback;
    private final String USER_KEY = "user";
    private final String BOT_KEY = "bot";


    private RequestQueue mRequestQueue;

    private ArrayList<Message> messageModalArrayList;
    private MessageRVAdapter messageRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot);

        chatsRV = findViewById(R.id.idRVChats);
        sendMsgIB = findViewById(R.id.idIBSend);
        userMsgEdt = findViewById(R.id.idEdtMessage);
        mRequestQueue = Volley.newRequestQueue(this);
        mRequestQueue.getCache().clear();

        messageModalArrayList = new ArrayList<>();
        goback=findViewById(R.id.botback);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bot.this, MainActivity.class);
                startActivity(intent);
            }
        });

        sendMsgIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (userMsgEdt.getText().toString().isEmpty()) {

                    Toast.makeText(bot.this, "Please enter your message..", Toast.LENGTH_SHORT).show();
                    return;
                }


                sendMessage(userMsgEdt.getText().toString());

                userMsgEdt.setText("");
            }
        });

        messageRVAdapter = new MessageRVAdapter(messageModalArrayList, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);


        chatsRV.setLayoutManager(linearLayoutManager);


        chatsRV.setAdapter(messageRVAdapter);
    }

    private void sendMessage(String userMsg) {

        messageModalArrayList.add(new Message(userMsg, USER_KEY));
        messageRVAdapter.notifyDataSetChanged();




        String url = "http://10.0.2.2:8000/bot/query=" + userMsg;

        RequestQueue queue = Volley.newRequestQueue(this);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String botResponse = response.getString("cnt");
                            messageModalArrayList.add(new Message(botResponse, BOT_KEY));
                            messageRVAdapter.notifyItemInserted(messageModalArrayList.size() - 1);
                            chatsRV.scrollToPosition(messageModalArrayList.size() - 1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            messageModalArrayList.add(new Message("Error parsing bot response", BOT_KEY));
                            messageRVAdapter.notifyItemInserted(messageModalArrayList.size() - 1);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        messageModalArrayList.add(new Message("Error: " + error.getMessage(), BOT_KEY));
                        messageRVAdapter.notifyItemInserted(messageModalArrayList.size() - 1);
                    }
                });

        queue.add(jsonObjectRequest);
    }
}