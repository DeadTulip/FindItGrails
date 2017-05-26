package p.hh.figrails.commands

import p.hh.figrails.domain.Location
import p.hh.figrails.domain.User


class ItemCommand {
    Long ownerId
    String ownerName
    Long itemId
    String itemName
    Long itemSize
    String type
    Date eventStart
    Date eventEnd
    List<String> selectedPeople = []
    String additionalPeople
    List<String> selectedPlaces = []
    String additionalPlaces
    String description

    String getInvolvedPeople() {
        getInvolvedInfo(selectedPeople, additionalPeople)
    }

    String getInvolvedPlaces() {
        getInvolvedInfo(selectedPlaces, additionalPlaces)
    }

    private String getInvolvedInfo(List<String> selectedInfo, String additionalInfo) {
        List<String> additionalInfoList = []
        if(additionalInfo) {
            additionalInfoList = additionalInfo.split(",")*.trim()
        }
        selectedInfo.addAll(additionalInfoList)
        selectedInfo.unique().sort().join(",")
    }
}
