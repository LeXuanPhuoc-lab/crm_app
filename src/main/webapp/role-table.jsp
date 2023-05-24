<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>


<!DOCTYPE html>
<html lang="en">

<head>
    <title>role-table</title>
    <%@ include file="head.jsp"%>
</head>

<body>
<%@ include file="header.jsp"%>
<!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Danh sách quyền</h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
                        <a href="<c:url value="/role/add"/>" class="btn btn-sm btn-success">Thêm mới</a>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <div class="row bg-search">
                    <ul class="nav navbar-top-links navbar-right m-l-20 hidden-xs">
                        <li>
                            <form role="search" class="app-search hidden-xs">
                                <input oninput="searchByName(this)" type="text" placeholder="Search..." class="form-control">
                                <a href="">
                                    <i class="fa fa-search"></i>
                                </a>
                            </form>
                        </li>
                    </ul>
                </div><br>
                <!-- /row -->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="white-box">
                            <div class="table-responsive">
                                <c:if test="${rolesList!=null}">
                                <table class="table" id="example">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Tên Quyền</th>
                                            <th>Mô Tả</th>
                                            <th>Hành Động</th>
                                        </tr>
                                    </thead>
                                    <tbody id="table-body">
                                        <c:forEach var="r" items="${rolesList}">
                                            <tr>
                                                <td>${r.ID}</td>
                                                <td>${r.name}</td>
                                                <td>${r.describe}</td>
                                                <td>
                                                    <a href="<c:url value="role/update?id=${r.ID}"/>" class="btn btn-sm btn-primary">Sửa</a>
                                                    <a roleId="${r.ID}" class="btn btn-sm btn-danger btn-delete-role">Xóa</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
            <footer class="footer text-center"> 2018 &copy; myclass.com </footer>
        </div>
        <!-- /#page-wrapper -->
        <%@ include file="footer.jsp"%>
        <script src="<c:url value="/js/role-table.js" />"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script>
            function searchByName(param){
                var txtSearch = param.value;
                $.ajax({
                    url: "/crm_app/role/search",
                    type: "get",
                    data: {
                        txt: txtSearch
                    },
                    success: function (data) {
                        var tb = document.getElementById("table-body");
                        tb.innerHTML = data;
                    },
                    error: function(){
                    }
                });
            }
        </script>
</body>

</html>