<%-- 
    Document   : grade
    Created on : 29-01-2023, 17:58:56
    Author     : phanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table {

            }
            table > tbody > tr > td {

                padding: 5px;
                background-color: #ccc;
            }
        </style>
    </head>
    <body>
        <jsp:useBean class="DAL.AssessmentDetailDAO" id="dao" />
        <jsp:useBean class="DAL.AssessmentDAO" id="assDAO"/>
        <jsp:useBean class="DAL.GradeDAO" id="gradeDAO"/>
        <jsp:useBean class="Custom.ForJSP" id="custom" />

        <h3>${subjectCode} - ${account.displayName} - ${account.stid}</h3>
        <table >
            <thead>
                <tr>
                    <th>Grade category</th>
                    <th>Grade item</th>
                    <th>Weight</th>
                    <th>Value</th>
                    <th>Comment</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${assDAO.getAssessmentBySubjectCode(subjectCode)}" var="ass">
                    <tr>
                        <td rowspan="${ass.detail.size() + 2}">${ass.category}</td>
                        <c:forEach items="${ass.detail}" var="detail">
                            <c:set var="grade" value="${gradeDAO.getGradeByAssessmentDetailIDAndStid(detail.assdeID, stid)}"/>
                        <tr>
                            <td>${detail.assessmentItem}</td>
                            <td>${detail.weightFormat}</td>
                            <td>${grade.value}</td>
                            <td></td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td>Total</td>
                        <td>${ass.total}</td>
                        <td>${custom.formarNumberFloat(gradeDAO.getTotalGradeOfAssessmentBySubjectCodeANdMore(ass.subjectCode, ass.assid, stid))}</td>
                        <td></td>
                    </tr>
                    </tr>
                </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <td rowspan="2">Course total</td>
                    <td>Average</td>
                    <td colspan="3">${custom.formarNumberFloat(gradeDAO.getAverageGradeOfSubjectBySubjectCodeAndStid(subjectCode, stid))}</td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td colspan="3">
                        <c:if test="${gradeDAO.checkPreRequisite(subjectCode, stid)}">
                            <font color="Green">Passed</font>
                        </c:if>
                        <c:if test="${!gradeDAO.checkPreRequisite(subjectCode, stid)}">
                            <font color="Red">Not Passed</font>
                        </c:if>
                    </td>
                </tr>
            </tfoot>
        </table>
    </body>
</html>
