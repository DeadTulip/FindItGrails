<%@ page import="p.hh.figrails.utils.ItemType;" %>
<g:applyLayout name="main">
    <r:require module="openItem" />

    <div class="container">
    <h2>
        <g:if test="${cmd.itemId}">Update ${cmd.itemType} Item</g:if>
        <g:else>Add ${cmd.itemType} Item</g:else>
    </h2>
        <g:if test="${message}">
            <div class="alert alert-success">${message}</div>
        </g:if>
        <g:uploadForm id='itemForm' class="form-horizontal" controller="item" action="${cmd.itemId ? 'update' : 'create'}">
            <g:hiddenField name="itemId" value="${cmd.itemId}" />
            <g:hiddenField name="itemType" value="${cmd.itemType}" />

            <div class="form-group">
                <label class="control-label col-sm-2" for="inputSelectedPlaces">Shared teams:</label>
                <div class="col-sm-8">
                    <figr:sharedTeamSelect class="form-control" id="inputSharedTeams" name="sharedTeams" selectedOptions="${cmd.sharedTeams}" disabled="${readonly == true}"/>
                </div>
            </div>

            <g:if test="${cmd.itemType == ItemType.DISK}">
                <g:render template="openDiskItem" model="[cmd: cmd, readonly: readonly]"/>
            </g:if>
            <g:else>
                <g:render template="openPhysicalItem" model="[cmd: cmd, readonly: readonly]"/>
            </g:else>


            <div class="form-group">
                <label class="control-label col-sm-2" for="inputEventStart">Event start:</label>
                <div class="col-sm-3" id="eventStartDateContainer">
                    <g:textField class="form-control" id="inputEventStart" name="eventStart" value="${cmd.eventStart?.format("dd/MM/yyyy")}" disabled="${readonly == true}"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="inputEventEnd">Event end:</label>
                <div class="col-sm-3" id="eventEndDateContainer">
                    <g:textField class="form-control" id="inputEventEnd" name="eventEnd" value="${cmd.eventEnd?.format("dd/MM/yyyy")}" disabled="${readonly == true}"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="inputSelectedPeople">Involved people:</label>
                <div class="col-sm-8">
                    <figr:involvedPeopleSelect class="form-control" id="inputSelectedPeople" name="selectedPeople" selectedOptions="${cmd.selectedPeople}" disabled="${readonly == true}"/>
                    <label class="control-label">Additional people:</label>
                    <g:textArea class="form-control" id="inputAdditionalPeople" rows="3" name="additionalPeople" disabled="${readonly == true}"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="inputSelectedPlaces">Involved places:</label>
                <div class="col-sm-8">
                    <figr:involvedPlaceSelect class="form-control" id="inputSelectedPlaces" name="selectedPlaces" selectedOptions="${cmd.selectedPlaces}" disabled="${readonly == true}"/>
                    <label class="control-label">Additional places:</label>
                    <g:textArea class="form-control" id="inputAdditionalPlaces" rows="3" name="additionalPlaces" disabled="${readonly == true}"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="inputDescription">Description:</label>
                <div class="col-sm-8">
                    <g:textArea class="form-control" id="inputDescription" rows="10" name="description" disabled="${readonly == true}">${cmd.description}</g:textArea>
                </div>
            </div>

            <div class="col-sm-offset-2 col-sm-10">
                <g:if test="${!readonly}">
                    <button class="btn btn-primary" type="submit">
                        <g:if test="${cmd.itemId}">Update</g:if>
                        <g:else>Add</g:else>
                    </button>
                </g:if>
            </div>

        </g:uploadForm>
    </div>

</g:applyLayout>