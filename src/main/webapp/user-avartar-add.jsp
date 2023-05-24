<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>


<!DOCTYPE html>
<html lang="en">

<head>
    <title>Avartar-add</title>
<%@ include file="head.jsp"%>
</head>

<body>
<%@ include file="header.jsp"%>
    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">Change Avartar</h4>
                </div>
            </div>
            <!-- /.row -->
            <!-- .row -->
            <div class="row">
                <div class="col-md-2 col-12"></div>
                <div class="col-md-8 col-xs-12">
                    <div class="white-box">
                        <form class="form-horizontal form-material" action="<c:url value="/user/avartar" />" method="POST">
                            <div class="form-group">
                                <label class="col-md-12">Link hình ảnh</label>
                                <div class="col-md-12">
                                    <input type="hidden" name="id" value="${userID}">
                                    <input type="url" placeholder="Link hình ảnh"
                                           class="form-control form-control-line" name="avartar" value=""/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <c:if test="${msg!=null}">
                                        <p style="color: red;">${msg}</p>
                                    </c:if>
                                    <button type="submit" class="btn btn-success">Change avartar</button>
                                    <a href="profile" class="btn btn-primary">Quay lại</a>
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