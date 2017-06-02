<div class="form-group">
    <label class="control-label col-sm-2" for="inputItemName">Name:</label>
    <div class="col-sm-6">
        <div>
            <g:textField class="form-control" id="inputItemName" name="itemName" value="${cmd.itemName}" disabled="${readonly == true}"/>
        </div>
        <div>
            <g:hasErrors bean="${cmd}" field="itemName">
                <div class="errors">
                    <g:renderErrors bean="${cmd}" field="itemName" as="list" />
                </div>
            </g:hasErrors>
        </div>
    </div>

</div>

<div class="form-group">
    <label class="control-label col-sm-2" for="inputItemName">Picture:</label>
    <div class="col-sm-6">
        <g:if test="${readonly != true}" >
            <input type="file" name="picture" />
        </g:if>
        <g:img class="img-rounded" src="c:/_Work/fileStore/af1b6e08-3cda-40a3-bcfe-25358a759b5d" />
    </div>

</div>