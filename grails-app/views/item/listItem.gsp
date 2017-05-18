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
            </tr>
            </thead>
            <tbody>
                <g:each in="${items}" var="item">
                    <tr>
                        <td></td>
                        <td>${item.name}</td>
                        <td>${item.dateCreated}</td>
                        <td>${item.dateUpdated}</td>
                        <td>${item.type}</td>
                        <td>${item.size}</td>
                    </tr>
                </g:each>
            </tbody>
        </table>
    </div>
</g:applyLayout>