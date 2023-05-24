<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>user-detail</title>
    <%@ include file="head.jsp"%>
</head>

<body>
    <%@ include file="header.jsp"%>
<!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">Chi tiết thành viên</h4>
                </div>
            </div>
            <!-- /.row -->
            <!-- .row -->
            <div class="row">
                <div class="col-md-4 col-xs-12">
                    <div class="white-box">
                        <div class="user-bg"> <img width="100%" alt="user" src="plugins/images/large/img1.jpg">
                            <div class="overlay-box">
                                <div class="user-content">
                                    <a href="javascript:void(0)"><img src="${userInfo.avartar}"
                                            class="thumb-lg img-circle" alt="img"></a>
                                        <h4 class="text-white">${userInfo.firstname} ${userInfo.lastname}</h4>
                                        <h5 class="text-white">${userInfo.username}</h5>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="col-md-8 col-xs-12">
                    <!-- BEGIN THỐNG KÊ -->
<div class="row">
    <!--col -->
    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
    <c:if test="${perList!=null}">
        <c:set var="p" value="${perList}"/>
        <div class="white-box">
            <div class="col-in row">
                <div class="col-xs-12">
                    <h3 class="counter text-right m-t-15 text-danger">${p.get(0)}%</h3>
                </div>
                <div class="col-xs-12">
                    <i data-icon="E" class="linea-icon linea-basic"></i>
                    <h5 class="text-muted vb text-center">CHƯA BẮT ĐẦU</h5>
                </div>
                <div class="col-md-12 col-sm-12 col-xs-12">
                    <div class="progress">
                        <div class="progress-bar progress-bar-danger" role="progressbar"
                            aria-valuenow="${p.get(0)}" aria-valuemin="0" aria-valuemax="100"
                            style="width: ${p.get(0)}%"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /.col -->
    <!--col -->
    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
        <div class="white-box">
            <div class="col-in row">
                <div class="col-xs-12">
                    <h3 class="counter text-right m-t-15 text-megna">${p.get(1)}%</h3>
                </div>
                <div class="col-xs-12">
                    <i class="linea-icon linea-basic" data-icon="&#xe01b;"></i>
                    <h5 class="text-muted vb text-center">ĐANG THỰC HIỆN</h5>
                </div>
                <div class="col-md-12 col-sm-12 col-xs-12">
                    <div class="progress">
                        <div class="progress-bar progress-bar-megna" role="progressbar"
                            aria-valuenow="${p.get(1)}" aria-valuemin="0" aria-valuemax="100"
                            style="width: ${p.get(1)}%"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /.col -->
    <!--col -->
    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
        <div class="white-box">
            <div class="col-in row">
                <div class="col-xs-12">
                    <h3 class="counter text-right m-t-15 text-primary">${p.get(2)}%</h3>
                </div>
                <div class="col-xs-12">
                    <i class="linea-icon linea-basic" data-icon="&#xe00b;"></i>
                    <h5 class="text-muted vb text-center">HOÀN THÀNH</h5>
                </div>
                <div class="col-md-12 col-sm-12 col-xs-12">
                    <div class="progress">
                        <div class="progress-bar progress-bar-primary" role="progressbar"
                            aria-valuenow="${p.get(2)}" aria-valuemin="0" aria-valuemax="100"
                            style="width: ${p.get(2)}%"></div>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
    </div>
    <!-- /.col -->
</div>
<!-- END THỐNG KÊ -->

                </div>
            </div><br />
            <!-- /.row -->
            <!-- BEGIN DANH SÁCH CÔNG VIỆC -->
            <h4>DANH SÁCH CÔNG VIỆC</h4>
            <div class="row">
                <div class="col-md-4">
                    <div class="white-box">
                        <h3 class="box-title">Chưa thực hiện</h3>
                        <div class="message-center">
                        <c:if test="${notDoneList!=null}">
                            <c:forEach var="item" items="${notDoneList}">
                                <a href="profile">
                                    <div class="mail-contnet">
                                        <h5 style="color: red;">${item.projectID}</h5>
                                        <span class="mail-desc"></span>
                                        <span class="time">Bắt đầu: ${item.createDate}</span>
                                        <span class="time">Kết thúc: ${item.endDate}</span>
                                    </div>
                                </a>
                            </c:forEach>
                        </c:if>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="white-box">
                        <h3 class="box-title">Đang thực hiện</h3>
                        <div class="message-center">
                        <c:if test="${isDoingList!=null}">
                            <c:forEach var="item" items="${isDoingList}">
                                <a href="profile">
                                    <div class="mail-contnet">
                                        <h5 style="color: red;">${item.projectID}</h5>
                                        <span class="mail-desc"></span>
                                        <span class="time">Bắt đầu: ${item.createDate}</span>
                                        <span class="time">Kết thúc: ${item.endDate}</span>
                                    </div>
                                </a>
                            </c:forEach>
                        </c:if>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="white-box">
                        <h3 class="box-title">Đã hoàn thành</h3>
                        <div class="message-center">
                        <c:if test="${doneList!=null}">
                            <c:forEach var="item" items="${doneList}">
                                <a href="profile">
                                    <div class="mail-contnet">
                                        <h5 style="color: red;">${item.projectID}</h5>
                                        <span class="mail-desc"></span>
                                        <span class="time">Bắt đầu: ${item.createDate}</span>
                                        <span class="time">Kết thúc: ${item.endDate}</span>
                                    </div>
                                </a>
                            </c:forEach>
                        </c:if>
                        </div>
                    </div>
                </div>
            </div>
            <div>
                <c:if test="${msg!=null}">
                    <h2 style="color:red;">${msg}</h2>
                </c:if>
            <div>
            <!-- END DANH SÁCH CÔNG VIỆC -->
        </div>
        <!-- /.container-fluid -->
        <footer class="footer text-center"> 2018 &copy; myclass.com </footer>
    </div>
    <!-- /#page-wrapper -->
<%@ include file="footer.jsp"%>
</body>

</html>