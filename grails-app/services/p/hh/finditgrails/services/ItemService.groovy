package p.hh.finditgrails.services

import grails.transaction.Transactional
import org.springframework.web.multipart.commons.CommonsMultipartFile
import p.hh.figrails.commands.ItemCommand
import p.hh.figrails.domain.DiskItem
import p.hh.figrails.domain.DiskLocation
import p.hh.figrails.domain.Item
import p.hh.figrails.domain.Location
import p.hh.figrails.domain.PhysicalItem
import p.hh.figrails.domain.Team
import p.hh.figrails.domain.User
import p.hh.figrails.utils.ItemType

@Transactional
class ItemService {
    def teamService
    def userService
    def propertyService

    Item createItem(ItemCommand command) {
        Item item = mapCommandToItem(command)
        Date now = new Date()
        item.dateCreated = now
        item.dateUpdated = now
        item.save(failOnError: true)

        shareItemInTeams(item, command.sharedTeams)
        item
    }

    Item updateItem(ItemCommand command) {
        Item item = mapCommandToItem(command)
        Date now = new Date()
        item.dateUpdated = now
        item.save(failOnError: true)

        shareItemInTeams(item, command.sharedTeams)
        item
    }

    DiskLocation savePicture(CommonsMultipartFile multipartFile) {
        if(multipartFile) {
            String location = propertyService.fileStoreLocation()
            String randomName = UUID.randomUUID().toString()
            File storedFile = new File("${location}/${randomName}")
            multipartFile.transferTo(storedFile)

            new DiskLocation(originalName: multipartFile.originalFilename, onDiskName: randomName).save(flush: true)
        } else {
            null
        }
    }

    void shareItemInTeams(Item item, List<String> teams) {
        Set<Team> previousSharedTeams = item.sharedTeams
        Set<Team> updatedSharedTeams = []
        teams.each {
            updatedSharedTeams.add(Team.findByTeamName(it))
        }

        Set<Team> addedTeams = updatedSharedTeams - previousSharedTeams
        Set<Team> removedTeams = previousSharedTeams - updatedSharedTeams

        addedTeams.each {
            it.addToItems(item)
            it.save(failOnError: true, flush: true)
        }
        removedTeams.each {
            it.removeFromItems(item)
            it.save(failOnError: true, flush: true)
        }
    }

    ItemCommand mapItemToCommand(Item item) {
        ItemCommand cmd = new ItemCommand()
        cmd.itemId = item.id
        cmd.itemName = item.name
        String involvedPeople = item.involvedPeople
        cmd.selectedPeople = involvedPeople ? involvedPeople.split(",") : []
        String involvedPlaces = item.involvedPlaces
        cmd.selectedPlaces = involvedPlaces ? involvedPlaces.split(",") : []
        cmd.description = item.description
        cmd.eventStart = item.eventStartTime
        cmd.eventEnd = item.eventEndTime
        cmd.sharedTeams.addAll(item.sharedTeams*.teamName)

        if (item instanceof DiskItem) {
            Item diskItem = (DiskItem) item
            cmd.itemType = ItemType.DISK
            cmd.fileSize = diskItem.fileSize
            cmd.fileType = diskItem.fileType

        } else {
            Item physicalItem = (PhysicalItem) item
            cmd.itemType = ItemType.PHYSICAL
            cmd.pictureLocation = physicalItem.picture
        }

        cmd
    }

    Item mapCommandToItem(ItemCommand command) {
        if (command.itemType == ItemType.DISK) {
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

        if(command.pictureLocation) {
            item.picture = command.pictureLocation
        }
        item
    }

    private void bindItemData(ItemCommand command, Item item) {
        item.owner = userService.findUser(command.ownerId)
        item.location = command.itemLocation
        item.name = command.itemName
        item.involvedPeople = command.involvedPeople
        item.involvedPlaces = command.involvedPlaces
        item.description = command.description
        item.eventStartTime = command.eventStart
        item.eventEndTime = command.eventEnd
    }

    ItemCommand getItemCommand(Integer itemId) {

    }

    Set<Item> findAllCreatedItemsByUser(User user) {
        Item.findAllByOwner(user)
    }

    Set<Item> findAllAccessibleItemsByUser(User user) {
        Set<Item> items = findAllCreatedItemsByUser(user)
        Set<Team> accessibleTeams = teamService.teamsAccessibleByUser(user)

        accessibleTeams.each {
            items.addAll(it.items)
        }
        items
    }

    Item findById(Long id) {
        Item.findById(id)
    }

}
