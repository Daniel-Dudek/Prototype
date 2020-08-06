package es.businessmind.prototype;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;


public class Items implements Parcelable {

    private String id;
    private String titleItem;
    private String descripcion;
    private String precio;

    public Items() {

    }

    public Items(String id, String titleItem, String descripcion, String precio) {
        if(id == null) {
            id = UUID.randomUUID().toString();
        }
        this.id = id;
        this.titleItem = titleItem;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    protected Items(Parcel in) {
        id = in.readString();
        titleItem = in.readString();
        descripcion = in.readString();
        precio = in.readString();
    }

    public static final Creator<Items> CREATOR = new Creator<Items>() {
        @Override
        public Items createFromParcel(Parcel in) {
            return new Items(in);
        }

        @Override
        public Items[] newArray(int size) {
            return new Items[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitleItem() {
        return titleItem;
    }

    public void setTitleItem(String titleItem) {
        this.titleItem = titleItem;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(titleItem);
        dest.writeString(descripcion);
        dest.writeString(precio);
    }
}
