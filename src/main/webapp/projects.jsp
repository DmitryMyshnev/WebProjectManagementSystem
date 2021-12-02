<!DOCTYPE html>
<html>
<head>
    <title>Users page</title>
    <jsp:include page="headers.jsp"/>
</head>
<body>

<jsp:include page="navigation.jsp"/>

<div class="container">
    <div class="row">
        <h2>Project page</h2>
    </div>

    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <a href="/project/new" type="button" class="btn btn-primary">New</a>
            </div>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">Description</th>
                <th scope="col">Cost</th>
                <th scope="col">Date</th>
                <th scope="col">Developers</th>
                <th scope="col">Companies</th>
                <th scope="col">Customers</th>
            </tr>
            </thead>
            <tbody>
            <%
            Object[] projects = (Object[]) request.getAttribute("projects");
                for(Object prj : projects) {
                ua.goIt.model.Project project = (ua.goIt.model.Project) prj;
                %>
                <tr>
                    <td><%= project.getId() %></td>
                    <td><%= project.getName() %></td>
                    <td><%= project.getDescription() == null? "":project.getDescription() %></td>
                    <td><%= project.getCost() %></td>
                    <td><%= project.getDate() %></td>
                    <td><%= project.getDevelopersAsString() %></td>
                    <td><%= project.getCompanyAsString() %></td>
                    <td><%= project.getCustomerAsString() %></td>

                    <td>
                        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                            <div class="btn-group me-2" role="group" aria-label="Second group">
                                <a href="/project/edit/<%= project.getId() %>" type="button" class="btn btn-warning">Edit</a>
                                <a href="/project/remove/<%= project.getId() %>" type="button" class="btn btn-danger">Remove</a>
                            </div>
                        </div>
                    </td>
                </tr>
               <% } %>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>