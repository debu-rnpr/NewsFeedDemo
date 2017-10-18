package app.feed.com.data.entities.response;

import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import app.feed.com.MainApplication;
import app.feed.com.R;

/**
 * Created by debu on 17/10/17.
 */

public class HomeListResponse implements Serializable{
    private String title;
    private String description;
    private String author;
    private List<String> tags;
    private String image;
    private String created_at;
    private int likes;
    private boolean published;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    @BindingAdapter("image")
    public static void setImageResource(ImageView view, String url) {
        Glide.with(view.getContext()).load(url)
                .centerCrop()
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view);
    }


    @BindingAdapter("date")
    public static void setDate(TextView view, String date) {
        try {
            String dateOnly = date.split("T")[0];
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d = sdf.parse(dateOnly);
            SimpleDateFormat monthDate = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
            view.setText(monthDate.format(d));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @BindingAdapter("string_tag")
    public static void setStringTags(TextView view, List<String> tags) {
        if(tags != null && tags.size() > 0){
            Object[] arr = tags.toArray();
            view.setText(Arrays.toString(arr).replace("[","").replace("]",""));
        }
    }
}
