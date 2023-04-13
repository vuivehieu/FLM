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
import model.Curriculum;

/**
 *
 * @author PCM
 */
public class CurriculumDAO extends DBContext{
    public List<Curriculum> findAllCurriculum(){
        List<Curriculum> curriculums = new ArrayList<>();
        try{
            String sql = "SELECT *"
                    + "from curriculum c;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Curriculum curriculum = new Curriculum();
                curriculum.setCurid(rs.getInt("curid"));
                curriculum.setCurCode(rs.getString("curCode"));
                curriculum.setCurName_EN(rs.getString("curName_EN"));
                curriculum.setCurName_VI(rs.getString("curName_VI"));
                curriculum.setDescription(rs.getString("description"));
                curriculum.setImage(rs.getString("image"));
                curriculum.setIsActive(rs.getBoolean("isActive"));
                curriculum.setIsApproved(rs.getBoolean("isApproved"));
                curriculums.add(curriculum);
            }
        }catch(SQLException e){
        System.out.println("CurriculumDAO -> findAllCurriculum(): " + e);
        }
        return curriculums;
    }
            
            
}
