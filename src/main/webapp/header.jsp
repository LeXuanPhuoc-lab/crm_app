<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags"%>
<!-- Preloader -->
<div class="preloader">
    <div class="cssload-speeding-wheel"></div>
</div>
<div id="wrapper">
    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top m-b-0">
        <div class="navbar-header">
            <a class="navbar-toggle hidden-sm hidden-md hidden-lg " href="javascript:void(0)" data-toggle="collapse" data-target=".navbar-collapse">
                <i class="fa fa-bars"></i>
            </a>
            <div class="top-left-part">
                <a class="logo" href="<c:url value="/home"/>">
                    <b>
                        <img src="<c:url value="/plugins/images/pixeladmin-logo.png" />" alt="home" />
                    </b>
                    <span class="hidden-xs">
                        <img src="<c:url value="/plugins/images/pixeladmin-text.png" />" alt="home" />
                    </span>
                </a>
            </div>
            <ul class="nav navbar-top-links navbar-left m-l-20 hidden-xs">
                <li>
                    <form role="search" class="app-search hidden-xs">
                        <input type="text" placeholder="Search..." class="form-control">
                        <a href="">
                            <i class="fa fa-search"></i>
                        </a>
                    </form>
                </li>
            </ul>
            <ul class="nav navbar-top-links navbar-right pull-right">
                <li>
                    <div class="dropdown">
                        <a class="profile-pic dropdown-toggle" data-toggle="dropdown" href="#">
                            <c:if test="${user!=null}">
                                <img src="${user.avartar}" alt="user-img" width="36" class="img-circle" />
                                <b class="hidden-xs">${user.firstname} ${user.lastname}</b>
                            </c:if>
                            <c:if test="${admin!=null}">
                                <img src="${admin.avartar}" alt="user-img" width="36" class="img-circle" />
                                <b class="hidden-xs">${admin.firstname} ${admin.lastname} <span style="font-size: small">(Admin)</span></b>
                            </c:if>
                        </a>
                        <ul class="dropdown-menu">
                        <c:if test="${admin!=null}">
                            <li><a href="<c:url value="/user/profile?id=${admin.ID}"/>">Thông tin cá nhân</a></li>
                            <li><a href="<c:url value="/user/work?id=${admin.ID}" />">Thống kê công việc</a></li>
                        </c:if>
                        <c:if test="${user!=null}">
                            <li><a href="<c:url value="/user/profile?id=${user.ID}"/>">Thông tin cá nhân</a></li>
                            <li><a href="<c:url value="/user/work?id=${user.ID}" />">Thống kê công việc</a></li>
                        </c:if>
                            <li class="divider"></li>
                            <li><a href="<c:url value="/login"/>">Đăng xuất</a></li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
        <!-- /.navbar-header -->
        <!-- /.navbar-top-links -->
        <!-- /.navbar-static-side -->
    </nav>
    <!-- Left navbar-header -->
    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse slimscrollsidebar">
            <ul class="nav" id="side-menu">
                <li style="padding: 10px 0 0;">
                    <a href="<c:url value="/home"/>" class="waves-effect"><i class="fa fa-clock-o fa-fw"
                            aria-hidden="true"></i><span class="hide-menu">Dashboard</span></a>
                </li>
                <c:if test="${admin!=null}">
                <li>
                    <a href="<c:url value="/user"/>" class="waves-effect"><i class="fa fa-user fa-fw"
                            aria-hidden="true"></i><span class="hide-menu">Thành viên</span></a>
                </li>
                </c:if>
                <c:if test="${admin!=null}">
                <li>
                    <a href="<c:url value="/role"/>" class="waves-effect"><i class="fa fa-modx fa-fw"
                            aria-hidden="true"></i><span class="hide-menu">Quyền</span></a>
                </li>
                </c:if>
                <li>
                    <a href="<c:url value="/project"/>" class="waves-effect"><i class="fa fa-briefcase fa-fw"
                            aria-hidden="true"></i><span class="hide-menu">Dự án</span></a>
                </li>
                <li>
                    <a href="<c:url value="/task"/>" class="waves-effect"><i class="fa fa-clipboard fa-fw"
                            aria-hidden="true"></i><span class="hide-menu">Phần việc làm</span></a>
                </li>
                <li>
                    <a href="<c:url value="/work"/>" class="waves-effect"><i class="fa fa-table fa-fw"
                            aria-hidden="true"></i><span class="hide-menu">Công việc</span></a>
                </li>
                <li>
                    <a href="<c:url value="/blank.jsp"/>" class="waves-effect"><i class="fa fa-columns fa-fw"
                            aria-hidden="true"></i><span class="hide-menu">Blank Page</span></a>
                </li>
                <li>
                    <a href="<c:url value="/404.jsp"/>" class="waves-effect"><i class="fa fa-info-circle fa-fw"
                            aria-hidden="true"></i><span class="hide-menu">Error 404</span></a>
                </li>
            </ul>
        </div>
    </div>
    <!-- Left navbar-header end -->
    <!-- /.container-fluid -->
    <footer class="footer text-center"> 2018 &copy; myclass.com </footer>
</div>
<!-- /end-wrapper -->