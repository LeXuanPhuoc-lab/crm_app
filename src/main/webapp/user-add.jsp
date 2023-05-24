<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>user-add</title>
    <%@ include file="head.jsp"%>
</head>

<body>
<%@ include file="header.jsp"%>
<!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">Thêm mới thành viên</h4>
                </div>
            </div>
            <!-- /.row -->
            <!-- .row -->
            <div class="row">
                <div class="col-md-2 col-12"></div>
                <div class="col-md-8 col-xs-12">
                    <div class="white-box">
                        <form class="form-horizontal form-material" action="<c:url value="/user/add"/>" method="POST">
                            <div class="form-group">
                                <label class="col-md-12">First Name</label>
                                <div class="col-md-12">
                                    <input type="text" placeholder="First name"
                                        class="form-control form-control-line" name="firstname" value=""> </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Last Name</label>
                                <div class="col-md-12">
                                    <input type="text" placeholder="Last name"
                                        class="form-control form-control-line" name="lastname" value=""> </div>
                            </div>
                            <div class="form-group">
                                <label for="example-email" class="col-md-12">Email</label>
                                <div class="col-md-12">
                                    <input type="email" placeholder="johnathan@admin.com"
                                        class="form-control form-control-line" name="email"
                                        id="example-email" value=""> </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Password</label>
                                <div class="col-md-12">
                                    <input type="password" value="" class="form-control form-control-line" name="password">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Phone No</label>
                                <div class="col-md-12">
                                    <input type="text" placeholder="123 456 7890"
                                        class="form-control form-control-line" name="phone" value=""> </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-12">Select Country</label>
                                <div class="col-sm-12">
                                <select class="form-control form-control-line" name="country">
                                    <c:if test="${countryList!=null}">
                                        <c:forEach var="c" items="${countryList}">
                                            <option value="${c.ID}">${c.name}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-12">Role</label>
                                <div class="col-sm-12">
                                <select class="form-control form-control-line" name="role">
                                    <c:if test="${roleList!=null}">
                                        <c:forEach var="r" items="${roleList}">
                                            <option value="${r.ID}">${r.describe}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <c:if test="${msg!=null}">
                                        <p style="color: red;">${msg}</p>
                                        <% session.removeAttribute("msg");%>
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <button type="submit" class="btn btn-success">Add User</button>
                                    <a href="user-table.html" class="btn btn-primary">Quay lại</a>
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