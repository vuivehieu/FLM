/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Role;

/**
 *
 * @author PCM
 */
public class RoleDAO extends DBContext{
    public List<Role> getAllRole(){
        List<Role> roleList = new ArrayList<>();
        try{
            String sql = "Select r.rid,"
                    + "rname from role r";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Role role = new Role(rs.getInt("rid"), rs.getString("rname"));
                roleList.add(role);
            }
        }catch(SQLException e){
                        System.out.println("RoleDAO -> getAllRole(): " + e);
        }
        return roleList;
    }
}
