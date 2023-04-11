/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Custom;

import DAL.DAO;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import model.Combo;
import model.Curriculum;
import model.PLO;
import model.PO;
import model.Syllabus;

/**
 *
 * @author phanh
 */
public class ForJSP {

    private DAO dao = new DAO();

    List<Curriculum> curriculum = dao.getAllCurriculum();
    List<Syllabus> syllabus = dao.getAllSyllabus();

    public int getYear(Date date) {
        return date.getYear() + 1900;
    }

    public String formarNumberFloat(float number) {
        DecimalFormat format = new DecimalFormat("#.#");
        return format.format(number);
    }

    public String getDateFormat(String pattern, Date date) {
        SimpleDateFormat f = new SimpleDateFormat(pattern);
        return f.format(date);
    }

    public String getCurriculumCodeByComboID(int cid) {
        for (Curriculum item : curriculum) {
            for (Combo combo : item.getCombo()) {
                if (combo.getCid() == cid) {
                    return item.getCurCode();
                }
            }
        }
        return "";
    }

    public String getCurriculumCodeByPOID(int poid) {
        for (Curriculum item : curriculum) {
            for (PO po : item.getPo()) {
                if (po.getPoid() == poid) {
                    return item.getCurCode();
                }
            }
        }
        return "";
    }

    public String getCurriculumCodeByPLOID(int ploid) {
        for (Curriculum item : curriculum) {
            for (PLO plo : item.getPlo()) {
                if (plo.getPloid() == ploid) {
                    return item.getCurCode();
                }
            }
        }
        return "";
    }

    public String getSyllabusNameENByID(int id) {
        for (Syllabus item : syllabus) {
            if(item.getSlbid() == id){
                return item.getSlbName_EN();
            }
        }
        return "";
    }

}
