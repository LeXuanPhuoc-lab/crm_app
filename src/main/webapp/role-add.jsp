<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <title>role-add</title>
    <%@ include file="head.jsp"%>
</head>
<body>
<%@ include file="header.jsp"%>
    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">Thêm mới quyền</h4>
                </div>
            </div>
            <!-- /.row -->
            <!-- .row -->
            <div class="row">
                <div class="col-md-2 col-12"></div>
                <div class="col-md-8 col-xs-12">
                    <div class="white-box">
                        <form action="<c:url value="/role/add" />" method="POST" class="form-horizontal form-material">
                            <div class="form-group">
                                <label class="col-md-12">Tên quyền</label>
                                <div class="col-md-12">
                                    <input type="text" placeholder="Tên quyền" name="name"
                                        class="form-control form-control-line" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Ngày Tạo</label>
                                <div class="col-md-12">
                                    <input type="date" placeholder="Ngày Tạo" name="createDate" class="form-control form-control-line" />
                                </div>
                            </div><div class="form-group">
                                <label class="col-md-12">Mô tả</label>
                                <div class="col-md-12">
                                    <input type="text" placeholder="Mô tả" name="describe" class="form-control form-control-line" />
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <button type="submit" class="btn btn-success">Add Role</button>
                                    <a href="<c:url value="/role"/>" class="btn btn-primary">Quay lại</a>
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