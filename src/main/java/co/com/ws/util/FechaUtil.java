package co.com.ws.util;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;

public class FechaUtil {

    public static Date toDate(XMLGregorianCalendar calendar){
        if(calendar == null) {
            return null;
        }
        return calendar.toGregorianCalendar().getTime();
    }

}
