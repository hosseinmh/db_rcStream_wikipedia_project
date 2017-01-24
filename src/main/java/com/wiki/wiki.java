package com.wiki;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import sun.print.PSPrinterJob;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pdes on 16/01/2017.
 */
public class wiki {
    private String event;
    private String item;
    private int user;
    private String link;


    public wiki(String event, int user, String item, String link) {
        this.item = item;
        this.user = user;
        this.link = link;
        this.event = event;
    }

    public String getEvent() {
        return event;
    }

    public String getItem() {
        return item;
    }

    public String getLink() {
        return link;
    }

    public int getUser() {
        return user;
    }


//
//    public void setEvent(String event) {
//        this.event = event;
//    }
//
//    public void setItem(String item) {
//        this.item = item;
//    }
//
//    public void setUser(int user) {
//        this.user = user;
//    }
//
//    public void setLink(String link) {
//        this.link = link;
//    }
//
//    public EPServiceProvider getEpservice() {
//        return epservice;
//    }
//
//    public void setEpservice(EPServiceProvider epservice) {
//        this.epservice = epservice;
//    }
//
//    public String getQuery() {
//        return query;
//    }
//
//    public void setQuery(String query) {
//        this.query = query;
//    }
//
//    public EPStatement getStatement() {
//        return statement;
//    }
//
//    public void setStatement(EPStatement statement) {
//        this.statement = statement;
//    }
//

    String text    =
            "This is the text to be searched " +
                    "for occurrences of the http:// pattern.";

    String patternString = ".*http://.*";

    Pattern pattern = Pattern.compile(patternString);

    Matcher matcher = pattern.matcher(text);
    boolean matches = matcher.matches();
}
