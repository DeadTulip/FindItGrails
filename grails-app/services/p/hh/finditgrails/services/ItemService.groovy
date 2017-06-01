package p.hh.finditgrails.services

import grails.transaction.Transactional
import p.hh.figrails.commands.ItemCommand
import p.hh.figrails.domain.DiskItem
import p.hh.figrails.domain.DiskLocation
import p.hh.figrails.domain.Item
import p.hh.figrails.domain.Location
import p.hh.figrails.domain.PhysicalItem
import p.hh.figrails.domain.Team
import p.hh.figrails.domain.User

@Transactional
class ItemService {
    def teamService

    Item createItem(ItemCommand command) {
        Item item = mapCommandToItem(command)
        Date now = new Date()
        item.dateCreated = now
        item.dateUpdated = now
        item.save(failOnError: true, flush: true)

        updateTeams(command.sharedTeams, item)
        item
    }

    Item updateItem(ItemCommand command) {
        Item item = mapCommandToItem(command)
        Date now = new Date()
        item.dateUpdated = now
        item.save(failOnError: true, flush: true)

        updateTeams(command.sharedTeams, item)
        item
    }

    private void updateTeams(List<String> teams, Item item) {
        Set<Team> previousSharedTeams = item.sharedTeams
        Set<Team> updatedSharedTeams = []
        teams.each {
            updatedSharedTeams.add(Team.findByTeamName(it))
        }

        Set<Team> addedTeams = updatedSharedTeams - previousSharedTeams
        Set<Team> removedTeams = previousSharedTeams - updatedSharedTeams

        addedTeams.each {
            it.items.add(item)
            it.save(failOnError: true, flush: true)
        }
        removedTeams.each {
            it.items.remove(item)
            it.save(failOnError: true, flush: true)
        }
    }

    ItemCommand mapItemToCommand(Item item) {
        ItemCommand cmd = new ItemCommand()
        cmd.itemId = item.id
        cmd.itemName = item.name
        cmd.selectedPeople = item.involvedPeople.split(",")
        cmd.selectedPlaces = item.involvedPlaces.split(",")
        cmd.description = item.description
        cmd.eventStart = item.eventStartTime
        cmd.eventEnd = item.eventEndTime
        cmd.sharedTeams.addAll(item.sharedTeams*.teamName)

        if (item instanceof DiskItem) {
            Item diskItem = (DiskItem) item
            cmd.itemType = 'disk'
            cmd.fileSize = diskItem.fileSize
            cmd.fileType = diskItem.fileType

        } else {
            cmd.itemType = 'physical'
        }

        cmd
    }

    Item mapCommandToItem(ItemCommand command) {
        if (command.itemType == 'disk') {
            mapCommandToDiskItem(command)
        } else {
            mapCommandToPhysicalItem(command)
        }
    }

    private DiskItem mapCommandToDiskItem(ItemCommand command) {
        DiskItem item
        if (command.itemId) {
            item = (DiskItem) Item.findById(command.itemId)
        } else {
            item = new DiskItem()
        }
        bindItemData(command, item)

        item.fileSize = command.fileSize
        item.fileType = command.fileType
        item
    }

    private PhysicalItem mapCommandToPhysicalItem(ItemCommand command) {
        PhysicalItem item
        if (command.itemId) {
            item = (PhysicalItem) Item.findById(command.itemId)
        } else {
            item = new PhysicalItem()
        }
        bindItemData(command, item)

        item
    }

    private void bindItemData(ItemCommand command, Item item) {
        item.owner = User.findById(command.ownerId)
        item.location = Location.findById(1L)
        item.name = command.itemName
        item.involvedPeople = command.involvedPeople
        item.involvedPlaces = command.involvedPlaces
        item.description = command.description
        item.eventStartTime = command.eventStart
        item.eventEndTime = command.eventEnd
    }

    ItemCommand getItemCommand(Integer itemId) {

    }

    Set<Item> findAllOwnedItemsByUser(User user) {
        Item.findAllByOwner(user)
    }

    Set<Item> findAllAccessibleItemsByUser(User user) {
        Set<Item> items = findAllOwnedItemsByUser(user)
        Set<Team> accessibleTeams = teamService.teamsJoinedByUser(user)

        accessibleTeams.each {
            items.addAll(it.items)
        }
        items
    }

    Item findById(Long id) {
        Item.findById(id)
    }

}
