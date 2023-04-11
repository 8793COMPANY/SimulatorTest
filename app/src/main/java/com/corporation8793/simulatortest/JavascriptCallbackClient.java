package com.corporation8793.simulatortest;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class JavascriptCallbackClient {

    private Context mContext;
    private WebView webView;
    private Button upload_btn;
    private String code;
    private String chapter_id;
    private String hex2str = "";
    private TextView serial_monitor;
    private TextView loading_text;
    private StringBuffer serial_text = new StringBuffer("");
    int count = 0;
    String str="";
    LinearLayout code_upload_progress;


    public JavascriptCallbackClient(Context context, WebView webView, TextView serial_monitor,
                                    TextView loading_text, Button upload_btn, LinearLayout code_upload_progress, String chapter_id, String code) {
        this.mContext = context;
        this.webView = webView;
        this.chapter_id = chapter_id;
        this.code = code;
        this.serial_monitor = serial_monitor;
        this.loading_text = loading_text;
        this.upload_btn = upload_btn;
        this.code_upload_progress = code_upload_progress;
    }

    private String publishEvent(String functionName, String data) {
        StringBuffer buffer = new StringBuffer()
                .append("window.dispatchEvent(\n")
                .append("   new CustomEvent(\"").append(functionName).append("\", {\n")
                .append("           detail: {\n")
                .append("               data: ").append(data).append("\n")
                .append("           }\n")
                .append("       }\n")
                .append("   )\n")
                .append(");");

        Log.e("buffer",buffer.toString());
        return buffer.toString();
    }

    @JavascriptInterface
    public void showToastMessage(final String message) {
        if (message.equals("enabled")){
            code_upload_progress.setVisibility(View.GONE);
            upload_btn.setSelected(true);
            upload_btn.setEnabled(true);
            Log.e("isEanbled",upload_btn.isEnabled()+"");
        }
        Log.e("message",message);
    }

    @JavascriptInterface
    public void showSerialMessage(final String message) {
        Log.e("push check",message);
    }



    // react에서 serial 값 넘어오는 부분
    @JavascriptInterface
    public void showToastLog(final String message)
    {

        Log.e("message",message);
        serial_text.append(message+"\n");

        if (serial_text.length() > 100){
            serial_text.delete(0,50);
        }

        serial_monitor.setText(serial_text);
        scrollBottom(serial_monitor);
//        str += message+"\n";
//        if (count > 5){
//            serial_monitor.setText(str.substring(str.indexOf("\n"),str.length()));
//        }else{
//            serial_monitor.setText(str);
//            count++;
//        }


    }
    @JavascriptInterface
    public void callJavaScriptFunction() {
        Log.e("hi","callJavaScriptFunction()");

        Log.e("code", code);
        int delay = 0;

        if (!code.contains("contents_id:")){

            hex2str = convertString2Hex(chapter_id+"@@"+code);
        }else{
            hex2str = code;
        }
        //  "\""+code.replace("\n","").getBytes()+"\""
        webView.postDelayed(() -> {
            webView.evaluateJavascript(publishEvent("javascriptFunction", "\""+hex2str+"\""),
                    (result) -> {
                        Log.e("result",result);
                        if (result.equals("true") && loading_text.getVisibility() == View.VISIBLE){
                            loading_text.setVisibility(View.GONE);
                        }
//                        Toast.makeText(mContext, "in "+result, Toast.LENGTH_SHORT).show();
                    }
            );
        }, 0);
    }

    private String convertString2Hex(String str){
        StringBuilder stringBuilder = new StringBuilder();

        char[] charArray = str.toCharArray();

        for (char c : charArray) {
            String charToHex = Integer.toHexString(c);
            stringBuilder.append(charToHex);
        }
        return  stringBuilder.toString();
    }

    private void scrollBottom(TextView textView) {
        int lineTop =  textView.getLayout().getLineTop(textView.getLineCount()) ;
        int scrollY = lineTop - textView.getHeight();
        if (scrollY > 0) {
            textView.scrollTo(0, scrollY);
        } else {
            textView.scrollTo(0, 0);
        }
    }
}