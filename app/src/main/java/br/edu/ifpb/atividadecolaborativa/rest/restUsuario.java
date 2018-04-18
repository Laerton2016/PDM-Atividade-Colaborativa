package br.edu.ifpb.atividadecolaborativa.rest;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.net.URL;

/**
 * Created by laerton on 06/04/2018.
 */

public class restUsuario extends AsyncTask {

    private  static  final String URLApp = "http://localhost:8080/webService/usuarios/";

    @Override
    protected Object doInBackground(Object[] params) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpRequestBase httpRequest = null;

        if ("pessoa".equals(params[0])) {

            httpRequest = new
                    HttpGet(URL.concat("pessoa").concat("/51250652006"));
            httpRequest.setHeader("Content-type", "application/json");

        } else if ("idade".equals(params[0])) {

            httpRequest = new HttpPost(URL.concat("idade"));
            httpRequest.setHeader("Content-type", "application/json");
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("cpf", "51250652006");
                jsonObject.put("nome", "Uguinho");
                jsonObject.put("dataNascimento", "1986-05-14");

                ((HttpPost) httpRequest).setEntity(new
                        ByteArrayEntity(
                        jsonObject.toString().getBytes("UTF8")));
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if ("todomundo".equals(params[0])) {
            httpRequest = new HttpGet(URL.concat("todomundo"));
            httpRequest.setHeader("Content-type", "application/json");
        }

        try {
            HttpResponse response = httpClient.execute(httpRequest);
            Log.i("ConsumeRESTAsyncTask",
                    StreamToString.doIt(response.getEntity().getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
