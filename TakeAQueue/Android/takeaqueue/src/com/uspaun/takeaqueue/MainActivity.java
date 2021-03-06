package com.uspaun.takeaqueue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class MainActivity extends Activity {

    // XML node keys
    static final String KEY_PEOPLES = "people"; // parent node
    static final String KEY_ID = "ID";
    static final String KEY_NAME = "Name";
    static final String KEY_IMAGE = "Image";
    //static final String KEY_NAME = "name";
    //static final String KEY_ITEM = "item";
	
	Button btnCreate, btnDownload;
	EditText etName;
	PostDataAsyncTask mt;
	GetDataFromServerAsyncTask GetDataFS;
	TextView tvInfo, tvContent;
	String pathTo;
	ListView lv;
	LinearLayout llMain;
	int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;
	final ArrayList<String> Names = new ArrayList<String>();
	final ArrayList<String> Images = new ArrayList<String>();
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		btnCreate = (Button) findViewById(R.id.btnCreate);
		etName = (EditText) findViewById(R.id.etName);
		btnDownload = (Button) findViewById(R.id.button1);
		//llMain = (LinearLayout) findViewById(R.id.llMain);
		//new PostDataAsyncTask().execute();
	}
    private static final String TAG = "MainActivity.java";

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void createXML(String name) {
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("peoples");
			doc.appendChild(rootElement);

			// staff elements
			Element staff = doc.createElement("people");
			rootElement.appendChild(staff);

			// set attribute to staff element
			Attr attr = doc.createAttribute("id");
			attr.setValue("1");
			staff.setAttributeNode(attr);

			// shorten way
			// staff.setAttribute("id", "1");

			// firstname elements
			Element firstname = doc.createElement("name");
			firstname.appendChild(doc.createTextNode(name));
			staff.appendChild(firstname);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			File MySDCard = Environment.getExternalStorageDirectory();
			pathTo = MySDCard.toString() + "/TakeAQueue/file.xml";
			//shTo.setText(pathTo);
			StreamResult result = new StreamResult(new File(pathTo));
			//StreamResult result = new StreamResult(new File("/data/data/com.uspaun.takeaqueue/file.xml"));
			transformer.transform(source, result);
			// System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			//pce.printStackTrace();
		} catch (TransformerException tfe) {
			//tfe.printStackTrace();
		}
	}
	
	public void showResult()
	{
		lv = (ListView)findViewById(R.id.listView1);
		final ArrayAdapter<String> adapter;
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Names);
		lv.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		Log.v(TAG, "������ �����");
		/*for(int i=0; i<Images.size(); i++)
		{
			ImageView iv = new ImageView(this);
			//String currIMG = Images.get(i);
		      //Uri currImgUri = Uri.parse(currIMG);
			//iv.setImageURI(currImgUri);
			try {
				iv.setImageDrawable(grabImageFromUrl(Images.get(i)));
			} catch (Exception e) {
				
			}
			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
			    RelativeLayout.LayoutParams.WRAP_CONTENT,
			    RelativeLayout.LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			llMain.addView(iv, lp);
		}*/
		
	}

	private Drawable grabImageFromUrl(String url) throws Exception {
		return Drawable.createFromStream(
				(InputStream) new URL(url).getContent(), "src");
	}
	
	public void parseXML(String URL)
	{
		XMLParser parser = new XMLParser();
		Log.v(TAG, "�������� ���������");
		String xml = parser.getXmlFromUrl(URL); // getting XML
		Log.v(TAG, "����� ���� � XML");
		Document doc = parser.getDomElement(xml); // getting DOM element
		Log.v(TAG, "����� DOM �������");
		NodeList nl = doc.getElementsByTagName(KEY_PEOPLES);
		Log.v(TAG, "������� �������� ���");
		
		for (int i = 0; i < nl.getLength(); i++) {
			Element e = (Element) nl.item(i);
		    Names.add(i, parser.getValue(e, KEY_NAME));
		    Images.add(i, parser.getValue(e, KEY_IMAGE));
		}
		ImageView image1 = (ImageView) findViewById(R.id.imageView1);
		try {
			image1.setImageDrawable(grabImageFromUrl(Images.get(1)));
		} catch (Exception e) {
			
		}
	    Log.v(TAG, "������ ����������");
	}

	public class PostDataAsyncTask extends AsyncTask<String, String, String> {
		 
        //public PostFiles FilesToPost;
        public String URL;
		
		protected void onPreExecute() {
            super.onPreExecute();
            // do stuff before posting data
        }
 
        @Override
        protected String doInBackground(String... strings) {
            try {
                    postFile(URL);
                 
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
 
        @Override
        protected void onPostExecute(String lenghtOfFile) {
            // do stuff after posting data
        }
    }
	
	public class GetDataFromServerAsyncTask extends AsyncTask<String, String, String> {
		 
        public String getURL;
		
		protected void onPreExecute() {
            super.onPreExecute();
            // do stuff before posting data
        }
 
        @Override
        protected String doInBackground(String... strings) {
            try {
                    parseXML(getURL);
                 
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
 
        @Override
        protected void onPostExecute(String lenghtOfFile) {
        }
    }
	
	
    private void postFile(String URL)
    {
    	try{
             
            MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
    		// the file to be posted
            File MySDCard = Environment.getExternalStorageDirectory();
            String textFile = MySDCard.toString() + "/TakeAQueue/file.xml";       
            String imageLink = MySDCard.toString() + "/TakeAQueue/image1.png";
            
            Log.v(TAG, "XMLFileLink: " + textFile + "\nImageFileLink: " + imageLink);
            Log.v(TAG, "postURL: " + URL);
             
            // new HttpClient
            HttpClient httpClient = new DefaultHttpClient();
            //HttpPost httpPost = new HttpPost(PostFiles.URL);
            HttpPost httpPost = new HttpPost(URL);
            File file = new File(textFile);
            FileBody fileBody = new FileBody(file);
            File fileImage = new File(imageLink);
            FileBody fileBodyImage = new FileBody(fileImage);
            
            reqEntity.addPart("file", fileBody);
            reqEntity.addPart("uploadfile", fileBodyImage);
//            File file;
//            FileBody fileBody;
//            for(int i=0; i<PostFiles.Files.size(); i++)
//            {
//                file = new File(PostFiles.Files.get(i));
//                fileBody = new FileBody(file);
//                reqEntity.addPart(PostFiles.Names.get(i), fileBody);
//            }
            httpPost.setEntity(reqEntity);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity resEntity = response.getEntity();
     
            if (resEntity != null) {
                String responseStr = EntityUtils.toString(resEntity).trim();
                Log.v(TAG, "Response: " +  responseStr);
                // you can add an if statement here and do other actions based on the response
            }
             
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public void onBtnDownloadClick(View view) {
		GetDataFS = new GetDataFromServerAsyncTask();
		GetDataFS.getURL = "http://uspaun.pp.ua/download.php";
		GetDataFS.execute();
	    showResult();
	    Log.v(TAG, "��������� showResult()");
	}
	
	public void onMyButtonClick(View view) {
//        File MySDCard = Environment.getExternalStorageDirectory();
//        String textFile = MySDCard.toString() + "/TakeAQueue/file.xml";       
//        String imageLink = MySDCard.toString() + "/TakeAQueue/image1.png";
		createXML(etName.getText().toString());
		mt = new PostDataAsyncTask();
		mt.URL = "http://uspaun.pp.ua/upload.php";
		//mt.FilesToPost.URL = "http://uspaun.pp.ua/upload.php";
		//mt.FilesToPost.Set("file", textFile);
		//mt.FilesToPost.Set("uploadfile", imageLink);
		mt.execute();
		Log.v(TAG, "��������� postFile()");
	}

}
