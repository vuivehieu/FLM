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
import model.PreRequisite;

/**
 *
 * @author PCM
 */
public class PrerequisiteDAO extends DBContext{
    public List<PreRequisite> findAllPreRequisites(){
        List<PreRequisite> preRequisites = new ArrayList<>();
        try{
            String sql = "SELECT *"
                    + "from prerequisite;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                PreRequisite preRequisite = new PreRequisite();
                preRequisite.setPreReqCode(rs.getString("preReqCode"));
                preRequisite.setPreReqID(rs.getInt("preID"));
                preRequisite.setSlbid(rs.getInt("slbid"));
                preRequisite.setSubjectCode(rs.getString("subjectCode"));
                preRequisites.add(preRequisite);
            }
        }catch(SQLException e){
        System.out.println("PrerequisiteDAO -> findAllPreRequisites(): " + e);
        }
        return preRequisites;
    }
}
