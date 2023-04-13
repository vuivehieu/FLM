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
import model.Subject;

/**
 *
 * @author PCM
 */
public class SubjectDAO extends DBContext{
    public List<Subject> findAllSubject(){
        List<Subject> subjects = new ArrayList<>();
        try{
            String sql = "SELECT *"
                    + "from subject;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Subject subject = new Subject();
                subject.setSubjectCode(rs.getString("subjectCode"));
                subject.setSubjectName_EN(rs.getString("subjectName_En"));
                subject.setSubjectName_VI(rs.getString("subjectName_Vi"));
                subject.setSemester(rs.getString("semester"));
                subject.setNoCredit(rs.getInt("noCredit"));
                subject.setNote(rs.getString("note"));
                subject.setIsCombo(rs.getBoolean("isCombo"));
                subject.setComboName(rs.getString("comboName"));
                subject.setIsElective(rs.getBoolean("isElective"));
                subject.setElectiveName(rs.getString("electiveName"));
                subject.setCreateDate(rs.getDate("createDate"));
                subject.setIsActive(rs.getBoolean("isActive"));
                subjects.add(subject);
            }
        }catch(SQLException e){
        System.out.println("SubjectDAO -> findAllSubject(): " + e);
        }
        return subjects;
    }
}
