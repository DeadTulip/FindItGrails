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
        <g:if test="${cmd.itemId}">
            <div id="divImg">
                <g:img class="img-rounded" src="fileStore/${cmd.pictureLocation.onDiskName}" style="width:auto; max-height: 200px"/>
            </div>
        </g:if>
    </div>

</div>