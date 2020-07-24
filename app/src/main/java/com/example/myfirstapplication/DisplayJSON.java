package com.example.myfirstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * MainActivityクラスからデータを受け取り、画面に表示するクラス。
 *
 * @author Kauzki Itoh
 * @since 1.0
 */
public class DisplayJSON extends AppCompatActivity {

    /**
     * 取得データを画面に表示し、戻るボタン押下時は初期画面に戻る。
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();

        // 初期画面から各値を受け取り、表示用にidを指定する
        String userId = intent.getStringExtra("userId");
        TextView textViewForUserId = findViewById(R.id.userId);

        String id = intent.getStringExtra("id");
        TextView textViewForId = findViewById(R.id.id);

        String title = intent.getStringExtra("title");
        TextView textViewForTitle = findViewById(R.id.title);

        String body = intent.getStringExtra("body");
        TextView textViewForBody = findViewById(R.id.body);

        // メインスレッド
        runOnUiThread(new Runnable() {

            /**
             * 画面に値を送る。
             */
            @Override
            public void run() {
                textViewForUserId.setText("userId : " + userId);
                textViewForId.setText("id : " + id);
                textViewForTitle.setText("title : " + title);
                textViewForBody.setText("body : " + body);
            }
        });

        Button returnButton = findViewById(R.id.back_button);
        returnButton.setOnClickListener(new View.OnClickListener() {

            /**
             * 初期画面に戻る。
             * @param v
             */
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
