package p.hh.finditgrails.services

import grails.transaction.Transactional
import p.hh.figrails.commands.ItemCommand
import p.hh.figrails.domain.DiskLocation
import p.hh.figrails.domain.Item
import p.hh.figrails.domain.Location
import p.hh.figrails.domain.User

@Transactional
class ItemService {

    Item createItem(ItemCommand command) {
        Item item = mapCommandToItem(command)
        Date now = new Date()
        item.dateCreated = now
        item.dateUpdated = now
        item.save(failOnError: true, flush: true)
    }

    Item mapCommandToItem(ItemCommand command) {
        Item item
        if (command.itemId) {
            item = Item.findById(command.itemId)
        } else {
            item = new Item()
        }

        item.owner = User.findById(command.ownerId)
        item.location = Location.findById(1L)
        item.size = command.itemSize
        item.name = command.itemName
        item.involvedPeople = command.involvedPeople
        item.involvedPlaces = command.involvedPlaces
        item.description = command.description
        item.eventStartTime = command.eventStart
        item.eventEndTime = command.eventEnd
        item.type = command.type
        item
    }

    ItemCommand getItemCommand(Integer itemId) {

    }
}
