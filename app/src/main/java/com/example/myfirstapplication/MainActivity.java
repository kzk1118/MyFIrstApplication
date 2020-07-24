package com.example.myfirstapplication;

import android.content.Intent;
import android.os.Bundle;
import com.example.myfirstsdk.JsonDto;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import java.io.IOException;

/**
 * SDKでデータを取得し、表示画面に送るクラス。
 *
 * @author Kazuki Itoh
 * @since 1.0
 */
public class MainActivity extends AppCompatActivity {

    // メンバ変数
    String userId = null;
    String id = null;
    String title = null;
    String body = null;

    /**
     * SDKからデータを取得し、表示画面に遷移する。
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendButton = findViewById(R.id.nextPage);
        sendButton.setOnClickListener(new View.OnClickListener() {

            /**
             * ボタン押下時にSDKからデータを取得する。
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), DisplayJSON.class);

                // HTTP通信のため別スレッドを用意
                new Thread(new Runnable() {
                    // SDKの戻り値の格納するDTOオブジェクトを生成
                    JsonDto jsonDto = new JsonDto();

                    /**
                     * SDKからデータを取得する。
                     */
                    @Override
                    public void run() {

                        // SDKのメインアクティビティのオブジェクトを生成
                        com.example.myfirstsdk.MainActivity sdk = new com.example.myfirstsdk.MainActivity();

                        try {
                            // 取得したデータをSDKからDTOオブジェクトに格納する
                            jsonDto = sdk.getJSON();
                        } catch (IOException es) {
                            es.printStackTrace();
                        }

                        // メインスレッドでの処理
                        runOnUiThread(new Runnable() {

                            /**
                             * 取得データを表示画面に送り、画面遷移をする。
                             */
                            @Override
                            public void run() {
                                // 各メンバ変数に取得した値を格納する
                                userId = jsonDto.getUserId();
                                id = jsonDto.getId();
                                title = jsonDto.getTitle();
                                body = jsonDto.getBody();

                                // 表示画面に値を送る
                                intent.putExtra("userId", userId);
                                intent.putExtra("id", id);
                                intent.putExtra("title", title);
                                intent.putExtra("body", body);

                                // 画面遷移
                                startActivity(intent);
                            }
                        });
                    }
                }).start();
            }
        });
    }
}
