package fvi.at.ua.notepad;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Build;

import java.io.Serializable;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Vika on 04.06.2017.
 */

public class Note implements Serializable {
    private long mDateTime;
    private String mTitle;
    private String mContent;


    public Note(long mDateTime, String mTitle, String mContent) {
        this.mDateTime = mDateTime;
        this.mTitle = mTitle;
        this.mContent = mContent;
    }

    public void setmDateTime(long mDateTime) {
        this.mDateTime = mDateTime;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public long getmDateTime() {
        return mDateTime;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmContent() {
        return mContent;
    }

/*    public String getDateTimeFormatted(Context context){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", context.getResources().getConfiguration().locale);
            sdf.setTimeZone(TimeZone.getDefault());

        return sdf.format(new Date(mDateTime));
    }*/
}
