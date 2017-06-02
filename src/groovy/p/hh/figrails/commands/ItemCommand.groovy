package p.hh.figrails.commands

import grails.validation.Validateable
import p.hh.figrails.domain.DiskLocation
import p.hh.figrails.utils.ItemType

class ItemCommand {
    ItemType itemType
    Long ownerId
    String ownerName
    Long itemId
    String itemName
    Long fileSize
    String fileType
    Date eventStart
    Date eventEnd
    List<String> selectedPeople = []
    String additionalPeople
    List<String> selectedPlaces = []
    String additionalPlaces
    String description
    List<String> sharedTeams = []
    DiskLocation pictureLocation


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
