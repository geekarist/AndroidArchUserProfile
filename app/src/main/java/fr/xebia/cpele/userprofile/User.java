package fr.xebia.cpele.userprofile;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class User {

    @SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int mId;

    @SerializedName("name")
    private String mName;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getId() {
        return mId;
    }

    public void setId(final int id) {
        mId = id;
    }
}
