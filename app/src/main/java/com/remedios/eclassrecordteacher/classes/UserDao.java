package com.remedios.eclassrecordteacher.classes;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert (Users users);

    @Update
    void update(Users users);

    @Query("delete from Users where id=:id")
    void delete(int id);

    @Query("Select * from Users")
    List<Users> getAllUsers();

}
