<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>


<!DOCTYPE html>
<html lang="en">

<head>
    <title>add-WorkOn</title>
    <%@ include file="head.jsp"%>
</head>

<body>
<%@ include file="header.jsp"%>
    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">Thêm mới công việc</h4>
                </div>
            </div>
            <!-- /.row -->
            <!-- .row -->
            <div class="row">
                <div class="col-md-2 col-12"></div>
                <div class="col-md-8 col-xs-12">
                    <div class="white-box">
                        <form class="form-horizontal form-material" action="<c:url value="/work/add"/>" method="POST">
                            <div class="form-group">
                                <label class="col-md-12">Dự án</label>
                                <div class="col-md-12">
                                <select class="form-control form-control-line" name="projectID" value="">
                                    <c:forEach var="p" items="${projectsList}">
                                        <option value="${p.ID}">${p.name}</option>
                                    </c:forEach>
                                </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Tên công việc</label>
                                <div class="col-md-12">
                                <select class="form-control form-control-line" name="taskID" value="">
                                    <c:forEach var="t" items="${tasksList}">
                                        <option value="${t.ID}">${t.name}</option>
                                    </c:forEach>
                                </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Người thực hiện</label>
                                <div class="col-md-12">
                                    <select class="form-control form-control-line" name="employeeID" value="">
                                        <c:forEach var="u" items="${usersList}">
                                            <option value="${u.ID}">ID:${u.ID} ${u.firstname} ${u.lastname}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Ngày bắt đầu</label>
                                <div class="col-md-12">
                                    <input type="text" placeholder="dd/MM/yyyy"
                                           class="form-control form-control-line" name="createDate" value="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Ngày kết thúc</label>
                                <div class="col-md-12">
                                    <input type="text" placeholder="dd/MM/yyyy"
                                           class="form-control form-control-line" name="endDate" value="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Trạng Thái</label>
                                <div class="col-md-12">
                                    <select class="form-control form-control-line" name="statusID" value="">
                                        <c:forEach var="st" items="${statusList}">
                                            <option value="${st.ID}">${st.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <c:if test="${msg!=null}">
                                        <p style="color:red;">${msg}</p>
                                    </c:if>
                                    <button type="submit" class="btn btn-success">Lưu lại</button>
                                    <a href="addTask" class="btn btn-primary">Quay lại</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-md-2 col-12"></div>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->
        <footer class="footer text-center"> 2018 &copy; myclass.com </footer>
    </div>
    <!-- /#page-wrapper -->
<%@ include file="footer.jsp"%>
</body>
</html>