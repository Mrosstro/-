package mrosstro.guessnunber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private final static int NUMBER_LENGTH = 3;
    EditText inputText;
    TextView respond;
    private List<String> numList;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputText = (EditText) findViewById(R.id.input_number);
        respond = (TextView) findViewById(R.id.respond);

        getRandomNumber();
    }

    private void getRandomNumber(){
        List<String> list = new ArrayList<String>();

        for(int i = 0; i < 10; i++){
            list.add(String.valueOf(i));
        }

        numList = new ArrayList<String>();

        for(int i = 0; i < NUMBER_LENGTH; i++){
            int index = (int)(Math.random() * list.size());
            numList.add(list.get(index));
            result += list.get(index);
            list.remove(index);
        }
    }

    private void showMsg(String msg, boolean isClear){
        respond.setText(msg);
        if(isClear){
            inputText.setText("");
        }
    }

    public void gusee_Click(View view){
        String intputNumber = inputText.getText().toString().trim();

        if(intputNumber.length() == 0){
            showMsg(this.getString(R.string.error), true);
            return;
        }

        List<String> guessNunberList = new ArrayList<String>();
        Set<String> set = new HashSet<>();

        for(int i = 0; i < intputNumber.length(); i++){
            String s = intputNumber.substring(i, i + 1);
            guessNunberList.add(s);
            set.add(s);
        }

        if(set.size() != 3){
            showMsg(this.getString(R.string.error), true);
            return;
        }

        int a = 0;
        int b = 0;

        for(int i = 0; i < guessNunberList.size(); i++ ){
            int index = numList.indexOf(guessNunberList.get(i));

            if(index == -1){
                continue;
            } else if(index == i) {
                a++;
            } else {
                b++;
            }
        }

        if (a == NUMBER_LENGTH){
            showMsg("恭喜你答對了 " + result, false);
        } else {
            showMsg( a + "A" + b + "B", true);
        }
    }


}
