<g:applyLayout name="main">
    <r:require module="listItems" />
    <div class="container">
        <table data-toggle="table">
            <thead>
            <tr>
                <th>#</th>
                <th data-sortable="true">name</th>
                <th data-sortable="true">dateCreated</th>
                <th data-sortable="true">dateUpdated</th>
                <th data-sortable="true">type</th>
                <th data-sortable="true">size</th>
                <th>edit</th>
            </tr>
            </thead>
            <tbody>
                <g:each in="${items}" var="item">
                    <tr>
                        <td></td>
                        <td>${item.name}</td>
                        <td>${item.dateCreated.format("yyyy-MM-dd")}</td>
                        <td>${item.dateUpdated.format("yyyy-MM-dd")}</td>
                        <td>${item.type}</td>
                        <td>${item.size}</td>
                        <td>
                            <g:if test="${item.owner == currentUser}">
                                <g:link controller="item" action="open" params="[itemId: item.id]">
                                    <span class="glyphicon glyphicon-cog"></span>
                                </g:link>
                            </g:if>
                            <g:else>
                                <g:link controller="item" action="open" params="[itemId: item.id, readonly: true]">
                                    <span class="glyphicon glyphicon-search"></span>
                                </g:link>
                            </g:else>
                        </td>
                    </tr>
                </g:each>
            </tbody>
        </table>
    </div>
</g:applyLayout>