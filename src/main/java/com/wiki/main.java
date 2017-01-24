package com.wiki;
import com.espertech.esper.client.*;
import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;
import org.json.JSONException;
import org.json.* ;

import java.net.URISyntaxException;

import java.util.Random;
import java.util.StringTokenizer;


import java.io.*;

public class main implements IOCallback{


    public static void main(String [] argv) throws IOException, URISyntaxException  {

        System.out.println("Connected");
        String query = null;
        Configuration config = null;
        config = new Configuration();
        config.addEventType("rc", rc.class.getName());


        EPServiceProvider epservice = EPServiceProviderManager.getDefaultProvider(config);
        final EPRuntime cepRT = epservice.getEPRuntime();
        EPAdministrator cepAdm = epservice.getEPAdministrator();

        //cepSta -->lisener
//        EPStatement cepStatement = cepAdm.createEPL("select change,count(change) from " +
//                "rc.win:length_batch(180) " + "group by change");
//
//        cepStatement.addListener(new lisener());
        EPStatement cepStatementLimit = cepAdm.createEPL("select change,count(change) from " +
                "rc.win:length_batch(180) " + "group by change order by count(change) desc limit 20");
        cepStatementLimit.addListener(new lisener());
//ث       System.out.print(cepStatement);

        final SocketIO socket = new SocketIO("http://stream.wikimedia.org/rc");
        socket.connect(new io.socket.IOCallback() {
            @Override
            public void onDisconnect() {
                System.out.print("dis");
            }

            @Override
            public void onConnect() {
                System.out.println(" On Connection ");
                //logger.info("Server connected.");
                String str = "commons.wikimedia.org";
                socket.emit("subscribe", str);

            }

            @Override
            public void onMessage(String s, IOAcknowledge ioAcknowledge) {

            }

            @Override
            public void onMessage(JSONObject jsonObject, IOAcknowledge ioAcknowledge) {
                try {
                    System.out.println("Server said:" + jsonObject.toString(2));
                    //writer.println();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void on(String s, IOAcknowledge ioAcknowledge, Object... objects) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(objects[0].toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                StringTokenizer st2 = new StringTokenizer(objects[0].toString(), ",");
                try {
                    JSONObject obj = new JSONObject(objects[0].toString());
                    while (st2.hasMoreElements()) {
                        if (st2.nextElement().equals("\"type\"" + ":" + "\"edit\"")  ) {

                            GenerateChar(cepRT,jsonObject.getString("title"));
                            System.out.println("----------------------------------");
                            System.out.println("TITLE: " + obj.get("title"));
                            System.out.println("TYPE: " + obj.get("type"));
                        }
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(SocketIOException e) {

            }
        });

    }

    @Override
    public void onDisconnect() {

    }

    @Override
    public void onConnect() {

    }

    @Override
    public void onMessage(String s, IOAcknowledge ioAcknowledge) {

    }

    @Override
    public void onMessage(JSONObject jsonObject, IOAcknowledge ioAcknowledge) {

    }

    @Override
    public void on(String s, IOAcknowledge ioAcknowledge, Object... objects) {

    }

    @Override
    public void onError(SocketIOException e) {

    }




    public static void GenerateChar(EPRuntime cepRT,String sChange) {
    for(int i = 0 ; i<sChange.length();i++) {
        long timeStamp = System.currentTimeMillis();
        char temp = sChange.charAt(i);
        temp = Character.toUpperCase(temp);
        if(!Character.isLetter(temp))
            continue;
        rc changeObject = new rc(temp, timeStamp);
        cepRT.sendEvent(changeObject);
    }
}




}//big main
