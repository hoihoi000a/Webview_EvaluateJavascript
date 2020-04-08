package tw.com.demowebinteraction;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    WebView myBrowser;
    EditText Msg;
    Button btnSendMsg;

    /** Called when the activity is first created. */
    @SuppressLint("JavascriptInterface")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myBrowser = (WebView)findViewById(R.id.mybrowser);


        myBrowser.getSettings().setJavaScriptEnabled(true);
        myBrowser.addJavascriptInterface(this, "AndroidFunction");
        myBrowser.loadUrl("file:///android_asset/myPage.html");

        String msgToSend = "msg from android";
        myBrowser.loadUrl("javascript:callFromActivity(\""+msgToSend+"\")");
        Msg = (EditText)findViewById(R.id.msg);
        btnSendMsg = (Button)findViewById(R.id.sendmsg);
        btnSendMsg.setOnClickListener(new Button.OnClickListener(){

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            String msgToSend = Msg.getText().toString();
            myBrowser.loadUrl("javascript:callFromActivity(\""+msgToSend+"\")");

        }});

    }



        @JavascriptInterface
        public void showAndroidToast(String toast){
            Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public void openAndroidDialog(){
            AlertDialog.Builder myDialog
                    = new AlertDialog.Builder(MainActivity.this);
            myDialog.setTitle("DANGER!");
            myDialog.setMessage("You can do what you want!");
            myDialog.setPositiveButton("ON", null);
            myDialog.show();
        }

}
