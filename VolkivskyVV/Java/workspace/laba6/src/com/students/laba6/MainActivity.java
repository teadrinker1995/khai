package com.students.laba6;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.laba.R;

public class MainActivity extends Activity implements OnClickListener{

	List<String> strings = new ArrayList<String>(100);
	List<Integer> numbers = new ArrayList<Integer>();
	Button btnOne;
	Button btnTwo;
	Button btnThree;
	Button btnFour;
	TextView text;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOne=(Button)findViewById(R.id.button1);
        btnTwo=(Button)findViewById(R.id.button2);
        btnThree=(Button)findViewById(R.id.button3);
        btnFour=(Button)findViewById(R.id.button4);
        text = (TextView)findViewById(R.id.textView2);
        btnOne.setOnClickListener((OnClickListener) this);
        btnTwo.setOnClickListener((OnClickListener) this);
        btnThree.setOnClickListener((OnClickListener) this);
        btnFour.setOnClickListener((OnClickListener) this);
        
        readFile();
		text.setText(strings.get(1));
		btnTwo.setEnabled(false);
    	btnThree.setEnabled(false);
    	btnFour.setEnabled(false);
    	btnTwo.setText("");
    	btnThree.setText("");
    	btnFour.setText("");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    void readFile() {
  		strings.add("");
  		BufferedReader br = null;
          try {
        	  Resources res = getResources();
            br = new BufferedReader(new InputStreamReader(res.openRawResource(0x7f040000),"Cp1251"));
            String pieceOfText = "";
            String num = "";
            while ((num = br.readLine()) != null) {
          	  String tmp = "";
          	  pieceOfText = "";
          	  while (!(tmp = br.readLine()).contains("$")) {
                    pieceOfText+=tmp;
                }
          	  strings.add(pieceOfText);
              }
          } catch (FileNotFoundException e) {
        	  text.setText(e.toString());
          } catch (IOException e) {
        	  text.setText(e.toString());
          } 
          try {
			br.close();
		} catch (IOException e) {
			text.setText(e.toString());
		}
      }
    
    void gameOver(){
    	btnOne.setEnabled(false);
    	btnTwo.setEnabled(false);
    	btnThree.setEnabled(false);
    	btnFour.setEnabled(false);
    	btnOne.setText("");
    	btnTwo.setText("");
    	btnThree.setText("");
    	btnFour.setText("");
    }
    
    void findNumbers(){
    	numbers = new ArrayList<Integer>();
    	String p = (String) text.getText();
    	String n = "";
    	for(int i = 0; i< (p.length()-5); i++){
    		if((p.charAt(i)=='н'&& p.charAt(i+1)=='а' && p.charAt(i+2)==' ')) 
    		   if(p.charAt(i+3)=='1'|| p.charAt(i+3)=='2'|| p.charAt(i+3)=='3'||
    		    p.charAt(i+3)=='4'|| p.charAt(i+3)=='5'|| p.charAt(i+3)=='6'||
    		    p.charAt(i+3)=='7'|| p.charAt(i+3)=='8'|| p.charAt(i+3)=='9'){
    			n+=(p.charAt(i+3));
    			if(p.charAt(i+4)=='1'|| p.charAt(i+4)=='2'|| p.charAt(i+4)=='3'||
    		    		    p.charAt(i+4)=='4'|| p.charAt(i+4)=='5'|| p.charAt(i+4)=='6'||
    		    		    p.charAt(i+4)=='7'|| p.charAt(i+4)=='8'|| p.charAt(i+4)=='9' || p.charAt(i+4)=='0'){
    							n+=(p.charAt(i+4));
    								if(p.charAt(i+5)=='0')
    									n+=(0);
    		    		}
    		}
    		if(!n.equals("")){
        		numbers.add(Integer.valueOf(n));
        		n = "";
        	}
    		else gameOver();
    	}
    }
    
    void setButtons(){
    	if(numbers.size() == 1) {
    		btnOne.setEnabled(true);
        	btnTwo.setEnabled(false);
        	btnThree.setEnabled(false);
        	btnFour.setEnabled(false);
        	btnTwo.setText("");
        	btnThree.setText("");
        	btnFour.setText("");
        	btnOne.setText(numbers.get(0).toString());
		}
    	else if(numbers.size() == 2) {
    		btnOne.setEnabled(true);
        	btnTwo.setEnabled(true);
        	btnThree.setEnabled(false);
        	btnFour.setEnabled(false);
        	btnOne.setText(numbers.get(0).toString());
        	btnTwo.setText(numbers.get(1).toString());
        	btnThree.setText("");
        	btnFour.setText("");
		}
    	else if(numbers.size() == 3) {
    		btnOne.setEnabled(true);
        	btnTwo.setEnabled(true);
        	btnThree.setEnabled(true);
        	btnFour.setEnabled(false);
        	btnOne.setText(numbers.get(0).toString());
        	btnTwo.setText(numbers.get(1).toString());
        	btnThree.setText(numbers.get(2).toString());
        	btnFour.setText("");
		}
    	else if(numbers.size() == 4) {
    		btnOne.setEnabled(true);
        	btnTwo.setEnabled(true);
        	btnThree.setEnabled(true);
        	btnFour.setEnabled(true);
        	btnOne.setText(numbers.get(0).toString());
        	btnTwo.setText(numbers.get(1).toString());
        	btnThree.setText(numbers.get(2).toString());
        	btnFour.setText(numbers.get(3).toString());
		}
    }
    
    @Override
	public void onClick(View v) {
			if(v.getId()==R.id.button1){
				text.setText(strings.get(Integer.valueOf((String) btnOne.getText())));
				findNumbers();
	    		setButtons();
	    	}
			if(v.getId()==R.id.button2){
				text.setText(strings.get(Integer.valueOf((String) btnTwo.getText())));
				findNumbers();
	    		setButtons();
	    	}
			if(v.getId()==R.id.button3){
				text.setText(strings.get(Integer.valueOf((String) btnThree.getText())));
				findNumbers();
	    		setButtons();
	    	}
			if(v.getId()==R.id.button4){
				text.setText(strings.get(Integer.valueOf((String) btnFour.getText())));
				findNumbers();
	    		setButtons();
	    	}
		} 
    public boolean onOptionsItemSelected(MenuItem item)
    {
		text.setText(strings.get(1));
		findNumbers();
		setButtons();
    	return super.onOptionsItemSelected(item);
    }
}
