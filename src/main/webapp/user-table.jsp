<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>user-table</title>
    <%@ include file="head.jsp" %>
</head>

<body>
<%@ include file="header.jsp" %>
<!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">Danh sách thành viên</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
                    <a href="<c:url  value="/user/add" />" class="btn btn-sm btn-success">Thêm mới</a>
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
                            <table class="table" id="example">
                                <thead>
                                    <tr>
                                        <th>No.</th>
                                        <th>First Name</th>
                                        <th>Last Name</th>
                                        <th>Username</th>
                                        <th>Role</th>
                                        <th>#</th>
                                    </tr>
                                </thead>
                                <tbody id="table-body">
                                    <c:if test="${usersList!=null}">
                                        <c:forEach var="user" items="${usersList}">
                                            <tr>
                                                <td>${user.ID}</td>
                                                <td>${user.firstname}</td>
                                                <td>${user.lastname}</td>
                                                <td>${user.username}</td>
                                                <td>${user.roleID}</td>
                                                <td>
                                                    <c:if test="${admin!=null}">
                                                    <a href="<c:url value="/user/work?id=${user.ID}"/> " class="btn btn-sm btn-info">Xem</a>
                                                    <a href="<c:url value="/user/update?id=${user.ID}" />" class="btn btn-sm btn-primary">Sửa</a>
                                                    <a href="#" userID="${user.ID}" class="btn btn-sm btn-danger btn-delete-user">Xóa</a>
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                </tbody>
                            </table>
                            <nav aria-label="Page navigation example">
                              <ul class="pagination justify-content-center">
                                <li class="page-item disabled">
                                  <a class="page-link" href="#" tabindex="-1">Previous</a>
                                </li>
                                <c:forEach begin="1" end="${endP}" var="i">
                                    <li class="page-item ${tag==i ?"active disabled":""}"><a class="page-link" href="<c:url value="/user?pageIndex=${i}"/>">${i}</a></li>
                                </c:forEach>
                                <li class="page-item">
                                  <a class="page-link" href="#">Next</a>
                                </li>
                              </ul>
                            </nav>
                            <c:if test="${msg!=null}">
                                <p style="color:red;">${msg}</p>
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
    <%@ include file="footer.jsp" %>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <%-- <script src="js/jquery.dataTables.js"></script> --%>
    <script src="<c:url value="/js/user-table.js"/>"></script>
    <script>
        $(document).ready(function () {
                $('#example').DataTable();
        });

        function searchByName(param){
            var txtSearch = param.value;
            $.ajax({
                url: "/crm_app/user/search" ,
                type: "GET",
                data: {
                    txt: txtSearch
                },
                success: function (data) {
                    var tb = document.getElementById("table-body");
                    tb.innerHTML = data;
                },
                error: function (jqXhr, textStatus, errorMessage) { // error callback
                    $('p').append('Error: ' + errorMessage);
                }
            });
        }
    </script>
    <!-- /#page-wrapper -->
</body>

</html>