package iipo.models;

import java.util.List;

/**
 * Created by user on 23.05.2016.
 */
public class TaskModel {
    private int id;
    private String title;
    private int status;
    private String info;
    private String dateFinish;
    private String dateStart;
    private int groupId;
    private UserModel author;
    private List<UserModel> listUsers;

    public TaskModel(int id, String title, int status, String info, String dateFinish, String dateStart, int groupId, UserModel author, List<UserModel> listUsers) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.info = info;
        this.dateFinish = dateFinish;
        this.dateStart = dateStart;
        this.groupId = groupId;
        this.author = author;
        this.listUsers = listUsers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(String dateFinish) {
        this.dateFinish = dateFinish;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public UserModel getAuthor() {
        return author;
    }

    public void setAuthor(UserModel author) {
        this.author = author;
    }

    public List<UserModel> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<UserModel> listUsers) {
        this.listUsers = listUsers;
    }
}
