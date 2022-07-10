package in.ac.polydurg.generate_pdf;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SavePDF {
    private PrintJob printJob;
    private Context context;
    private WebView webView;
    private String filename;
    private HtmlParse htmlParse;
    private ViewGroup vg;
    boolean loadingFinished = true;
    boolean redirect = false;

    public SavePDF(Context context, String filename, HtmlParse htmlParse) {
        this.context = context;
        this.vg = ((Activity)context).findViewById(android.R.id.content);
        this.webView = new WebView(context);
        webView.setVisibility(View.GONE);
        vg.addView(webView);
        this.filename = filename;
        this.htmlParse = htmlParse;
        this.webView.loadData(Base64.encodeToString(htmlParse.parse().getHtml().getHtmlBytes(),Base64.NO_PADDING), "text/html", "base64");
        this.webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String urlNewString) {
                if (!loadingFinished) {
                    redirect = true;
                }

                loadingFinished = false;
                view.loadUrl(urlNewString);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap facIcon) {
                loadingFinished = false;
                //SHOW LOADING IF IT ISNT ALREADY VISIBLE
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if(!redirect){
                    loadingFinished = true;
                    PrintTheWebPage(SavePDF.this.webView);
                }

                if(loadingFinished && !redirect){
                    //HIDE LOADING IT HAS FINISHED
                    Log.d("TAG", "onPageFinished: ");

                } else{
                    redirect = false;
                }

            }
        });
        //PrintTheWebPage(this.webView);
    }


    private void PrintTheWebPage(WebView webView) {
        PrintManager printManager = (PrintManager)context.getSystemService(Context.PRINT_SERVICE);
        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter(this.filename);
        assert printManager != null;
        printJob = printManager.print(this.filename, printAdapter, new PrintAttributes.Builder().build());
    }

}

class HtmlParse {
    private String html;
    private HashMap<String,String> vars;
    private String specialPrefix = "{";
    private String specialPostfix = "}";


    public HtmlParse(String html,HashMap<String,String> vars){this.html=html;this.vars=vars;}
    public HtmlParse(String html){this.html=html;this.vars=new HashMap<>();}


    public HtmlParse parse(){
        for (Map.Entry<String, String> e : vars.entrySet()){
            String sp = specialPrefix+e.getKey()+specialPostfix;
            html = html.replace(sp,e.getValue());
        }
        return this;
    }

    public Html getHtml(){
        return  new Html().setHtml(html); }

    // data entry part
    public void putall(List<String> key, List<String> val)    {
        if(key.size()== val.size())
        {
            int count = 0;
            for(String k: key){
                vars.put(k,val.get(count));
                count+=1;
            }
        }

    }
}

class Html {
    private String html;
    public byte[] getHtmlBytes() { return html.getBytes(); }

    public Html setHtml(String html) { this.html = html; return this;}
}

public class GeneratePdf{
    private Context context;
    public GeneratePdf(Context context, String pdfFileName, String htmlTemplate, HashMap<String, String> keyValuePairHashMap){
        HtmlParse htmlParse = new HtmlParse(htmlTemplate,keyValuePairHashMap);
        new SavePDF(context, pdfFileName,htmlParse);
    }

    public GeneratePdf(Context context, String pdfFileName, String htmlTemplate, List<String> listOfkeys, List<String> listOfvalues) {
        HtmlParse htmlParse = new HtmlParse(htmlTemplate);
        htmlParse.putall(listOfkeys,listOfvalues);
        new SavePDF(context, pdfFileName,htmlParse);
    }
}