package com.corporation8793.simulatortest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Button component_close_btn;
    String chapter_id = "3-2";
    String title = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HashMap<String,String> map6 = new HashMap<String,String>(){{//초기값 지정
            put("3-2","void setup() {\n" +
                    "pinMode(8,OUTPUT);\n" +
                    "}\n" +
                    "void loop() {\n" +
                    "digitalWrite(8,HIGH);\n" +
                    "delay(1000);\n" +
                    "digitalWrite(8,LOW);\n" +
                    "delay(1000);\n" +
                    "}");
            put("3-3","void setup() {\n" +
                    "pinMode(8,OUTPUT);\n" +
                    "}\n" +
                    "void loop() {\n" +
                    "digitalWrite(8,HIGH);\n" +
                    "delay(500);\n" +
                    "digitalWrite(8,LOW);\n" +
                    "delay(200);\n" +
                    "}");
            put("3-4","void setup() {\n" +
                    "pinMode(13,OUTPUT);\n" +
                    "}\n" +
                    "void loop() {\n" +
                    "digitalWrite(13,HIGH);\n" +
                    "delay(200);\n" +
                    "digitalWrite(13,LOW);\n" +
                    "delay(400);\n" +
                    "}");
            put("5-2","void setup() {\n" +
                    "pinMode(7,INPUT);\n" +
                    " Serial.begin(9600);\n" +
                    "}\n" +
                    "void loop() {\n" +
                    "if(digitalRead(7)==true){\n" +
                    "  Serial.println(\"ON\");  \n" +
                    "}else{\n" +
                    "  Serial.println(\"OFF\");\n" +
                    "}\n" +
                    "delay(100);\n" +
                    "}");
            put("5-3","void setup() {\n" +
                    "pinMode(5,INPUT);\n" +
                    " Serial.begin(9600);\n" +
                    "}\n" +
                    "void loop() {\n" +
                    "if(digitalRead(5)==true){\n" +
                    "  Serial.println(\"ON\");  \n" +
                    "}else{\n" +
                    "  Serial.println(\"OFF\");\n" +
                    "}\n" +
                    "delay(100);\n" +
                    "}");
            put("5-4","void setup() {\n" +
                    "pinMode(5,INPUT);\n" +
                    "pinMode(10,OUTPUT);\n" +
                    " Serial.begin(9600);\n" +
                    "}\n" +
                    "void loop() {\n" +
                    "if(digitalRead(5)==true){\n" +
                    "  Serial.println(\"ON\");\n" +
                    "  digitalWrite(10,HIGH);  \n" +
                    "}else{\n" +
                    "  Serial.println(\"OFF\");\n" +
                    "  digitalWrite(10,LOW);\n" +
                    "}\n" +
                    "delay(100);\n" +
                    "}");

        }};

        WebView webView = findViewById(R.id.simulator_web_view);
        EditText guide_text = findViewById(R.id.guide_text);
        Button upload_btn = findViewById(R.id.upload_btn);
        TextView code_view  = findViewById(R.id.code_view);
        TextView hint_text  = findViewById(R.id.hint_text);
        TextView loading_text = findViewById(R.id.loading_text);
        LinearLayout code_upload_progress = findViewById(R.id.code_upload_progress);
        component_close_btn = findViewById(R.id.component_close_btn);

        code_view.setMovementMethod(new ScrollingMovementMethod());

        upload_btn.setSelected(true);


        component_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chapter_id = guide_text.getText().toString().trim();
                title = ArduinoCode.chapter_code(chapter_id);
                webView.addJavascriptInterface(new JavascriptCallbackClient(getApplicationContext(), webView, code_view, loading_text, upload_btn, code_upload_progress,
                        chapter_id,"contents_id:"+chapter_id),"android");

//        webView.loadUrl("https://master.d3u1psek9w7brx.amplifyapp.com/");
                webView.loadUrl("http://localhost:8080/");
//                if (guide_text.getText().toString().equals("3"))

            }
        });




        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);

                int errorCode = error.getErrorCode();
                Log.e("errorCode",errorCode+"");


                loading_text.setVisibility(View.GONE);
                webView.loadData("현재 사용이 불가능합니다.","text/html; charset=utf-8","UTF-8");


                switch (errorCode) {
                    case ERROR_AUTHENTICATION:
                        break;               // 서버에서 사용자 인증 실패
                    case ERROR_BAD_URL:
                        break;                           // 잘못된 URL
                    case ERROR_CONNECT:
                        break;                          // 서버로 연결 실패
                    case ERROR_FAILED_SSL_HANDSHAKE:
                        break;    // SSL handshake 수행 실패
                    case ERROR_FILE:
                        break;                                  // 일반 파일 오류
                    case ERROR_FILE_NOT_FOUND:
                        break;               // 파일을 찾을 수 없습니다
                    case ERROR_HOST_LOOKUP:
                        break;           // 서버 또는 프록시 호스트 이름 조회 실패
                    case ERROR_IO:
                        break;                              // 서버에서 읽거나 서버로 쓰기 실패
                    case ERROR_PROXY_AUTHENTICATION:
                        break;   // 프록시에서 사용자 인증 실패
                    case ERROR_REDIRECT_LOOP:
                        break;               // 너무 많은 리디렉션
                    case ERROR_TIMEOUT:
                        break;                          // 연결 시간 초과
                    case ERROR_TOO_MANY_REQUESTS:
                        break;     // 페이지 로드중 너무 많은 요청 발생
                    case ERROR_UNKNOWN:
                        break;                        // 일반 오류
                    case ERROR_UNSUPPORTED_AUTH_SCHEME:
                        break; // 지원되지 않는 인증 체계
                    case ERROR_UNSUPPORTED_SCHEME:
                        break;          // URI가 지원되지 않는 방식
                }
                Log.e("error", "(WEBVIEW)onReceivedError : " + errorCode );
            }

            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return super.shouldInterceptRequest(view, request);
            }
        });


        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//               title="hi";
                code_upload_progress.setVisibility(View.VISIBLE);
                upload_btn.setSelected(false);
                upload_btn.setEnabled(false);
                webView.addJavascriptInterface(new JavascriptCallbackClient(getApplicationContext(), webView, code_view, loading_text, upload_btn, code_upload_progress,
                        chapter_id,title.replace("\n","")),"android");
//               webView.loadUrl("https://master.d3u1psek9w7brx.amplifyapp.com/");
                webView.loadUrl("http://192.168.0.5:8080/");

            }
        });


//
//        arduino_code.setText(" void setup() {\n" +
//                "   pinMode(7, OUTPUT); \n" +
//                " }\n" +
//                "\n" +
//                " void loop() {\n" +
//                "   digitalWrite(7, HIGH);\n" +
//                "   delay(5000);\n" +
//                "   digitalWrite(7, LOW);\n" +
//                "   delay(5000);\n" +
//                " }");



        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.setLayerType(View.LAYER_TYPE_HARDWARE,null);



//        confirm_btn.setOnClickListener(Confirm_Btn);
//        if (Cancel_Btn != null) {
//            cancel_btn.setOnClickListener(Cancel_Btn);
//        } else {
//            cancel_btn.setVisibility(View.GONE);
//        }
    }
}