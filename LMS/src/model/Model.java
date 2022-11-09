package model;

import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.sql.Timestamp;

public interface Model {

    public long getId();

    public void setId(long id);

    public void setCreated_at(Timestamp created_at);

    public Timestamp getCreated_at();

    public void setUpdated_at(Timestamp updated_at);

    public Timestamp getUpdated_at();

    public void setDeleted_at(Timestamp deleted_at);

    public Timestamp getDeleted_at();

    public boolean insert() throws SQLException;

    public boolean update() throws SQLException;

    public boolean delete() throws SQLException;
}
