<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="title" value="tcp.page.title"/>

    <tiles:putAttribute name="body">
        <div class="row">
            <h2><spring:message code="tcp.page.title"/></h2>
        </div>
        <div id="page-error" class="row hidden">
            <p class="bg-danger">
                <spring:message code="page.error"/>
            </p>
        </div>
        <div id="page-success" class="row hidden">
            <p class="bg-success">
                <spring:message code="page.success"/>
            </p>
        </div>
        <!--
        <div class="row">
        <form id="ajax-form" class="form-horizontal" role="form" method="post" action="/tcp/get">
        <div class="form-group">
        <div class="col-sm-offset-2 col-sm-2">
        <button value="submit" class="btn btn-primary">TCP test</button>
        </div>
        </div>
        </form>
        </div>-->

        <div id="ad-table">
            <jsp:include page="/WEB-INF/views/tcp/_tablePartial.jsp"/>
        </div>

    </tiles:putAttribute>
</tiles:insertDefinition>
