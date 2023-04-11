package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.StudentSubject;

public class StudentDAO extends DBContext {

    private final static String SELECT_QUERY_STUDENTSUBJECT = " ss.[stid]\n"
            + "      ,ss.[subjectCode]\n"
            + "      ,ss.[isLearning]\n"
            + "      ,ss.[startDate]\n"
            + "      ,ss.[endDate]\n"
            + "      ,ss.[status]\n";

    public StudentSubject getStatusOfSubjectByStidAndSubjectCode(String stid, String subjectCode) {

        try {
            String sql = "SELECT " + SELECT_QUERY_STUDENTSUBJECT
                    + " FROM [dbo].[Student_Subject] ss\n"
                    + " WHERE ss.stid = ? and ss.subjectCode = ?";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, stid);
            st.setString(2, subjectCode);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                StudentSubject studentSubject = new StudentSubject(rs.getString("stid"), rs.getString("subjectCode"), rs.getInt("isLearning"),
                         rs.getDate("startDate"), rs.getDate("endDate"), rs.getString("status"));
                return studentSubject;
            }
        } catch (SQLException e) {
            System.out.println("StudentDAO -> getStatusOfSubjectByStid(): " + e);
        }
        return null;
    }

}
