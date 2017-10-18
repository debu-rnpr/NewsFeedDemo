package app.feed.com;

import android.util.Log;
import android.widget.TextView;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void setDate() throws Exception{
        String date = "2016-01-29T12:27:57.828782+00:00";

            String dateOnly = date.split("T")[0];
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d = sdf.parse(dateOnly);
            SimpleDateFormat monthDate = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
            assertEquals("29 JAN 2016",monthDate.format(d));

    }
}