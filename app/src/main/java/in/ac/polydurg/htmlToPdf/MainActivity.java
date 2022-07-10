package in.ac.polydurg.htmlToPdf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.ac.polydurg.generate_pdf.GeneratePdf;

public class MainActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        //////////// common template  ////////////////
        String htmlTemplate="<h1>Course : {course} , age :{age}</h1>";

        ///////////// keyValuePair to be replaced//////////////
        HashMap<String,String> keyValuePairHashMap = new HashMap<>();
        keyValuePairHashMap.put("course","CSE11");
        keyValuePairHashMap.put("age","90");
        ////////////////////////////////  generate PDF//////////////
          button.setOnClickListener(view -> {
              GeneratePdf generatePdf = new GeneratePdf(MainActivity.this, "Report1",htmlTemplate,keyValuePairHashMap);

          });
         //////////////////////////// if keys and values are passed as list order should be maintain

        List<String> keys = new ArrayList<>();
        keys.add("course"); // key directly or by loop from database
        keys.add("age");
        List<String> vals = new ArrayList<>();
        vals.add("IT");
        vals.add("90");
        //GeneratePdf generatePDF1 = new GeneratePdf(MainActivity.this, "aReport1",htmlTemplate,keys,vals);

    }
}