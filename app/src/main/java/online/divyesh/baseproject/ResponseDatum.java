
package online.divyesh.baseproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseDatum {
    @SerializedName("_id")
    @Expose
    public int _id;
    @SerializedName("format")
    @Expose
    public String format;
    @SerializedName("width")
    @Expose
    public long width;
    @SerializedName("height")
    @Expose
    public long height;
    @SerializedName("filename")
    @Expose
    public String filename;
    @SerializedName("id")
    @Expose
    public long id;
    @SerializedName("author")
    @Expose
    public String author;
    @SerializedName("author_url")
    @Expose
    public String authorUrl;
    @SerializedName("post_url")
    @Expose
    public String postUrl;

}
