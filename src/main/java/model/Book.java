package model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/27.
 */
public class Book implements Serializable {
    private Integer id;
    private String title;

    public Book() {
    }

    public Book(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
