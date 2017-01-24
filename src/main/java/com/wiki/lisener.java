package com.wiki;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import java.util.HashMap;

public class lisener implements UpdateListener {
    int num=0;
    String string  ;
    lisener()
    {
        this.num = 0;
    }

    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        for(int i = 0 ; i<newEvents.length;i++)
        {
            string = newEvents[i].getUnderlying().toString();
            num += Integer.valueOf(string.substring(15,string.indexOf(",")));
        }

        for(int i = 0 ; i < newEvents.length ; i++) {

            string = newEvents[i].getUnderlying().toString();

            double percent = (double) Integer.valueOf(string.substring(15,string.indexOf(",")))/num;
            System.out.println("event is come: "
                    + newEvents[i].getUnderlying() + percent*100);
        }
    }
}