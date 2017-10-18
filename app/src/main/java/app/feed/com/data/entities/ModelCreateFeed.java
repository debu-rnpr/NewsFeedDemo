package app.feed.com.data.entities;

import android.databinding.BindingAdapter;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import app.feed.com.MainApplication;
import app.feed.com.R;

/**
 * Created by debu on 15/10/17.
 */

public class ModelCreateFeed {
    private String title="";
    private String description = MainApplication.getInstance().getString(R.string.demo_description_twitter);
    private String author = "debu.roy89@gmail.com";
    private List<String> tags;
    private String stringTags="";
    private String image = MainApplication.getInstance().getString(R.string.demo_image);

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

    public String getStringTags() {
        return stringTags;
    }

    public void setStringTags(String stringTags) {
        this.stringTags = stringTags;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
