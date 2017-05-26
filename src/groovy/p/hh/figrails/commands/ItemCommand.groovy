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
        selectedPeople.addAll(additionalPeople.split(",")*.trim())
        selectedPeople.unique().sort()
    }

    String getInvolvedPlaces() {
        selectedPlaces.addAll(additionalPlaces.split(",")*.trim())
        selectedPlaces.unique().sort()
    }
}
