/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Custom;

import DAL.DAO;
import java.sql.Date;
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
public class Common {

    private static DAO dao = new DAO();

    static List<Curriculum> curriculum = dao.getAllCurriculum();
    static List<Syllabus> syllabus = dao.getAllSyllabus();

    public static Date getCurrentDate() {
        LocalDate curDate = java.time.LocalDate.now();
        return Date.valueOf(curDate.toString());
    }

    public static String getDateFormat(String pattern, Date date) {
        SimpleDateFormat f = new SimpleDateFormat(pattern);
        return f.format(date);
    }

    public static String getCurriculumCodeByComboID(int cid) {
        for (Curriculum item : curriculum) {
            for (Combo combo : item.getCombo()) {
                if (combo.getCid() == cid) {
                    return item.getCurCode();
                }
            }
        }
        return "";
    }

    public static String getCurriculumCodeByPOID(int poid) {
        for (Curriculum item : curriculum) {
            for (PO po : item.getPo()) {
                if (po.getPoid() == poid) {
                    return item.getCurCode();
                }
            }
        }
        return "";
    }

    public static String getCurriculumCodeByPLOID(int ploid) {
        for (Curriculum item : curriculum) {
            for (PLO plo : item.getPlo()) {
                if (plo.getPloid() == ploid) {
                    return item.getCurCode();
                }
            }
        }
        return "";
    }

    public static String getSyllabusNameENByID(int id) {
        for (Syllabus item : syllabus) {
            if (item.getSlbid() == id) {
                return item.getSlbName_EN();
            }
        }
        return "";
    }
}
