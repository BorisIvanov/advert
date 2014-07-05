<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="title" value="ad.page.title"/>

    <tiles:putAttribute name="body">
        <div class="row">
            <h2><spring:message code="ad.page.title"/></h2>
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
        <div class="row">
            <form id="ajax-form" class="form-horizontal" role="form" method="post" action="/ad/update">

                <input type="hidden" data-field="id" id="id" name="id">

                <div class="form-group">
                    <label for="num" class="col-sm-2 control-label">
                        <spring:message code="ad.num.label"/>
                    </label>

                    <div class="col-sm-6">
                        <input type="text" size="20" class="form-control input-sm" data-field="num" id="num" name="num">

                        <p class="bg-danger hidden"></p>
                    </div>
                </div>

                <div class="form-group">
                    <label for="value" class="col-sm-2 control-label">
                        <spring:message code="ad.value.label"/>
                    </label>

                    <div class="col-sm-6">
                        <input type="text" class="form-control input-sm" size="100" data-field="value" id="value"
                               name="value">

                        <p class="bg-danger hidden"></p>
                    </div>
                </div>

            </form>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-2">
                    <button value="button" class="btn btn-primary" onclick="save('#ad-table')">Save</button>
                </div>
            </div>

        </div>

        <div id="ad-table">
            <jsp:include page="/WEB-INF/views/ad/_tablePartial.jsp"/>
        </div>

    </tiles:putAttribute>
</tiles:insertDefinition>
