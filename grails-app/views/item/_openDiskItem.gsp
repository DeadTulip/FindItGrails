<div class="form-group">
    <label class="control-label col-sm-2">Name:</label>
    <div class="col-sm-6">
        <g:textField class="form-control" name="itemName" value="${cmd.itemName}" disabled="${readonly == true}"/>
    </div>
</div>

<div class="form-group">
    <label class="control-label col-sm-2">Type:</label>
    <div class="col-sm-2">
        <g:textField class="form-control" name="fileType" value="${cmd.fileType}" disabled="${readonly == true}"/>
    </div>
</div>

<div class="form-group">
    <label class="control-label col-sm-2">Size:</label>
    <div class="col-sm-2">
        <g:textField class="form-control" name="fileSize" value="${cmd.fileSize}" disabled="${readonly == true}"/>
    </div>
</div>