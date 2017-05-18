<g:applyLayout name="main">

    <r:require module="updateItem" />

    <div class="container">
        <h2>Add Item</h2>
        <form class="form-horizontal" action="#">
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <div class="checkbox">
                        <label><input type="checkbox">Is a physical item?</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="inputName">Name:</label>
                <div class="col-sm-6">
                    <input type="email" class="form-control" id="inputName" name="name">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="inputType">Type:</label>
                <div class="col-sm-2">
                    <input type="type" class="form-control" id="inputType" name="type">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="inputSize">Size:</label>
                <div class="col-sm-2">
                    <input type="type" class="form-control" id="inputSize" name="size">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="inputEventStart">Event start:</label>
                <div class="col-md-3" id="eventStartDateContainer">
                    <input type="text" class="form-control" id="inputEventStart" name="eventStart">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="inputEventEnd">Event end:</label>
                <div class="col-md-3" id="eventEndDateContainer">
                    <input type="text" class="form-control" id="inputEventEnd" name="eventEnd">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="inputPeople">Involved people:</label>
                <div class="col-sm-8">
                    <select class="form-control" id="inputKnownPeople" multiple="multiple">
                        <option value="1">Option 1</option>
                        <option value="2" selected="selected">Option 2</option>
                        <!-- Option 3 will be selected in advance ... -->
                        <option value="3" selected="selected">Option 3</option>
                    </select>
                    <label class="control-label">Additional people:</label>
                    <textarea class="form-control" id="inputPeople" rows="3" name="additionalPeople"></textarea>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="inputPlaces">Involved places:</label>
                <div class="col-sm-8">
                    <select class="form-control" id="inputKnownPlaces" multiple="multiple">
                        <option value="1">Option 1</option>
                        <option value="2" selected="selected">Option 2</option>
                        <!-- Option 3 will be selected in advance ... -->
                        <option value="3" selected="selected">Option 3</option>
                    </select>
                    <label class="control-label">Additional places:</label>
                    <textarea class="form-control" id="inputPlaces" rows="3" name="additionalPlaces"></textarea>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="inputDescription">Description:</label>
                <div class="col-sm-8">
                    <textarea class="form-control" id="inputDescription" rows="10" name="description"></textarea>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Submit</button>
                </div>
            </div>
        </form>
    </div>

</g:applyLayout>