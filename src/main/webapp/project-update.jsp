<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>addProject</title>
    <%@ include file="head.jsp" %>
</head>

<body>
<%@ include file="header.jsp" %>
    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">Sửa dự án</h4>
                </div>
            </div>
            <!-- /.row -->
            <!-- .row -->
            <div class="row">
                <div class="col-md-2 col-12"></div>
                <div class="col-md-8 col-xs-12">
                    <div class="white-box">
                        <form class="form-horizontal form-material" action="<c:url value="/project/update" />" method="POST">
                            <div class="form-group">
                                <input type="hidden" name="id" value="${project.ID}">
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Tên dự án</label>
                                <div class="col-md-12">
                                    <input type="text" placeholder="Tên công việc"
                                           class="form-control form-control-line" name="name" value="${project.name}"> </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Ngày bắt đầu</label>
                                <div class="col-md-12">
                                    <input type="text" placeholder="dd/MM/yyyy"
                                           class="form-control form-control-line" name="createDate" value="${project.createDate}"> </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Ngày kết thúc</label>
                                <div class="col-md-12">
                                    <input type="text" placeholder="dd/MM/yyyy"
                                           class="form-control form-control-line" name="endDate" value="${project.endDate}"> </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <c:if test="${msg!=null}">
                                        <p style="color:red;">${msg}</p>
                                    </c:if>
                                    <button type="submit" class="btn btn-success">Save</button>
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
<%@ include file="footer.jsp" %>
</body>

</html>