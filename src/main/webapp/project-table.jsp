<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Group-Work</title>
    <%@ include file="head.jsp" %>
</head>

<body>
<%@ include file="header.jsp" %>
    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">Danh sách dự án</h4>
                </div>
                <c:if test="${admin!=null}">
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
                    <a href="<c:url value="/project/add"/>" class="btn btn-sm btn-success">Thêm mới</a>
                </div>
                </c:if>
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
                            <table class="table" id="example">
                                <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Tên Dự Án</th>
                                    <th>Ngày Bắt Đầu</th>
                                    <th>Ngày Kết Thúc</th>
                                    <c:if test="${admin!=null}">
                                    <th>Hành Động</th>
                                    </c:if>
                                </tr>
                                </thead>
                                <tbody id="table-body">
                                    <c:set var="update" value="${cookie['updateProjectID'].getValue()}"/>
                                    <c:forEach var="p" items="${projectslist}">
                                        <c:if test="${!update.equals(p.ID)}">
                                        <tr>
                                            <td>${p.ID}</td>
                                            <td>${p.name}</td>
                                            <td>${p.createDate}</td>
                                            <td>${p.endDate}</td>
                                            <c:if test="${admin!=null}">
                                            <td>
                                                <a href="<c:url value="/project/work?id=${p.ID}"/>" class="btn btn-sm btn-info">Xem</a>
                                                <a href="<c:url value="/project/update?id=${p.ID}"/>" class="btn btn-sm btn-primary">Sửa</a>
                                                <a href="#" projectID="${p.ID}" class="btn btn-sm btn-danger btn-delete-project">Xóa</a>
                                            </td>
                                            </c:if>
                                        </tr>
                                        </c:if>
                                    </c:forEach>
                                </tbody>
                            </table>
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
    <%@ include file="footer.jsp" %>
    <script src="<c:url value="/js/project-table.js"/>"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script>
        function searchByName(param){
            var txtSearch = param.value;
            $.ajax({
                url: "/crm_app/project/search",
                type: "GET",
                data: {
                    txt: txtSearch
                },

                success: function(data){
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