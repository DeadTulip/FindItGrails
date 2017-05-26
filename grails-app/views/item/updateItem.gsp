<g:applyLayout name="main">

    <r:script>
        gUrl['itemCreate'] = '<g:createLink controller="item" action="create"/>'
    </r:script>
    <r:require module="updateItem" />


    <div class="container">
        <h2>Add Item</h2>
        <form id='itemForm' class="form-horizontal" action="#">
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <div class="checkbox">
                        <label><input type="checkbox">Is a physical item?</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="inputItemName">Name:</label>
                <div class="col-sm-6">
                    <g:textField class="form-control" id="inputItemName" name="itemName" value="${cmd.itemName}" />
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="inputType">Type:</label>
                <div class="col-sm-2">
                    <g:textField class="form-control" id="inputType" name="type" value="${cmd.type}" />
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="inputItemSize">Size:</label>
                <div class="col-sm-2">
                    <g:textField class="form-control" id="inputItemSize" name="itemSize" value="${cmd.itemSize}" />
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="inputEventStart">Event start:</label>
                <div class="col-md-3" id="eventStartDateContainer">
                    <g:textField class="form-control" id="inputEventStart" name="eventStart" value="${cmd.eventStart}" />
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="inputEventEnd">Event end:</label>
                <div class="col-md-3" id="eventEndDateContainer">
                    <g:textField class="form-control" id="inputEventEnd" name="eventEnd" value="${cmd.eventEnd}" />
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="inputSelectedPeople">Involved people:</label>
                <div class="col-sm-8">
                    <figr:involvedPeopleSelect class="form-control" id="inputSelectedPeople" name="selectedPeople" selectedOptions="${cmd.selectedPeople}" />
                    <label class="control-label">Additional people:</label>
                    <g:textArea class="form-control" id="inputAdditionalPeople" rows="3" name="additionalPeople" />
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="inputSelectedPlaces">Involved places:</label>
                <div class="col-sm-8">
                    <figr:involvedPlaceSelect class="form-control" id="inputSelectedPlaces" name="selectedPlaces" selectedOptions="${cmd.selectedPlaces}" />
                    <label class="control-label">Additional places:</label>
                    <g:textArea class="form-control" id="inputAdditionalPlaces" rows="3" name="additionalPlaces" />
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="inputDescription">Description:</label>
                <div class="col-sm-8">
                    <textarea class="form-control" id="inputDescription" rows="10" name="description">${cmd.description}</textarea>
                </div>
            </div>

        </form>
        <div class="col-sm-offset-2 col-sm-10">
            <button class="btn btn-primary" id="btnAdd">Add</button>
        </div>
    </div>

    <div id="successDialog" title="Basic dialog">
        <p>Dialog box</p>
    </div>

</g:applyLayout>