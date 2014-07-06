<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:if test="${not empty locale}">
    <fmt:setLocale value="${locale}"/>
</c:if>

<c:if test="${empty itemList}">
    <p><spring:message code="ad.page.listIsEmpty"/></p>
</c:if>

<c:if test="${not empty itemList}">

    <table class="table table-hover">
        <thead>
        <tr>
            <th>
                <button onclick="tableItemAdd()" type="button" class="btn btn-primary btn-sm"><span
                        class="glyphicon glyphicon-plus"></span></button>
            </th>
            <th>&nbsp;</th>
            <th><spring:message code="ad.num.label"/></th>
            <th><spring:message code="ad.value.label"/></th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="item" items="${itemList}">
            <tr data-id="${item.id}">
                <td>
                    <button onclick="tableItemEdit(${item.id})" type="button" class="btn btn-primary btn-sm"><span
                            class="glyphicon glyphicon-pencil"></span></button>
                </td>
                <td>
                    <button onclick="tableItemDelete('/ad/delete', ${item.id}, '#ad-table')" type="button"
                            class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-remove"></span></button>
                </td>
                <td data-field="num">${item.num}</td>
                <td data-field="value">${item.value}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</c:if>
