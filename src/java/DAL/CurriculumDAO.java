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
import model.PaginationModel;

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
    public List<Curriculum> findAllCurriculumByPage(PaginationModel pagination){
        List<Curriculum> curriculums = new ArrayList<>();
        try{
            String sql = "SELECT *"
                    + "from curriculum c ";
            if (!pagination.getSearch().equals("")) {
                    sql += "WHERE (c.curCode LIKE ? OR c.curName_EN LIKE ? OR c.curName_VI LIKE ? OR c.description LIKE ?)";
                }
            sql += " LIMIT "+ (pagination.getPageNo()-1) * pagination.getPageSize() +","+ pagination.getPageSize() +";";
            PreparedStatement st = connection.prepareStatement(sql);
            int i = 1;
            if (!pagination.getSearch().equals("")) {
                st.setString(i++, "%" + pagination.getSearch() + "%");
                st.setString(i++, "%" + pagination.getSearch() + "%");
                st.setString(i++, "%" + pagination.getSearch() + "%");
                st.setString(i++, "%" + pagination.getSearch() + "%");
            }
            ResultSet rs = st.executeQuery();
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
        System.out.println("CurriculumDAO -> findAllCurriculumByPage(): " + e);
        }
        return curriculums;
    }
    public int countAllCurriculum(PaginationModel pagination){
        int count = 0;
        try{
            String sql = "SELECT COUNT(*) as count "
                    + "from curriculum c ";
            if (!pagination.getSearch().equals("")) {
                    sql += "WHERE (c.curCode LIKE ? OR c.curName_EN LIKE ? OR c.curName_VI LIKE ? OR c.description LIKE ?)";
                }
            PreparedStatement st = connection.prepareStatement(sql);
            int i = 1;
            if (!pagination.getSearch().equals("")) {
                st.setString(i++, "%" + pagination.getSearch() + "%");
                st.setString(i++, "%" + pagination.getSearch() + "%");
                st.setString(i++, "%" + pagination.getSearch() + "%");
                st.setString(i++, "%" + pagination.getSearch() + "%");
            }
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                count  = rs.getInt("count");
            }
        }catch(SQLException e){
        System.out.println("CurriculumDAO -> countAllCurriculum(): " + e);
        }
        return (int) Math.ceil((double) count / pagination.getPageSize());
    }
            
            
}
