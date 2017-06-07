package p.hh.figrails.services

import grails.test.spock.IntegrationSpec
import p.hh.figrails.commands.ItemCommand
import p.hh.figrails.domain.DiskItem
import p.hh.figrails.domain.Item
import p.hh.figrails.domain.Team
import p.hh.figrails.domain.User
import p.hh.figrails.util.FigFactory
import p.hh.figrails.utils.ItemType
import p.hh.finditgrails.services.ItemService

/**
 * Created by Atos on 7-6-2017.
 */
class ItemServiceIntegrationSpec extends IntegrationSpec {
    ItemService itemService

    def "test shareItemInTeams when item has not yet been shared"() {
        given:
        User user = FigFactory.giveUser()
        Team team = FigFactory.giveTeam(user)
        Item item = FigFactory.giveDiskItem(user)
        assert team.items.size() == 0

        when:
        itemService.shareItemInTeams(item, [team.teamName])

        then:
        team.items.size() == 1
        team.items.contains(item)
    }

    def "test shareItemInTeams when item has been shared"() {
        given:
        User user = FigFactory.giveUser()
        Team team1 = FigFactory.giveTeam(user)
        Team team2 = FigFactory.giveTeam(user)
        Item item = FigFactory.giveDiskItem(user)

        and:
        assert team1.items.size() == 0
        assert team2.items.size() == 0

        itemService.shareItemInTeams(item, [team1.teamName])

        assert team1.items.size() == 1
        assert team2.items.size() == 0

        when:
        itemService.shareItemInTeams(item, [team2.teamName])

        then:
        team1.items.size() == 0
        team2.items.size() == 1
        team2.items.contains(item)
    }

    def "test createItem"() {
        given:
        User user = FigFactory.giveUser()
        ItemCommand cmd = new ItemCommand()
        cmd.with {
            itemType = ItemType.DISK
            itemName = 'tempName'
            itemLocation = FigFactory.giveDiskLocation()
            ownerId = user.id
            ownerName = user.username
            fileSize = 1L
            fileType = 'pdf'
            eventStart = new Date()
            eventEnd = new Date()
            selectedPeople = ['Abby', 'Bob']
            additionalPeople = 'Cathy'
            selectedPlaces = ['Amsterdam']
            additionalPlaces = 'Utrecht,Rotterdam'
            description = 'test'
        }

        when:
        Item item = itemService.createItem(cmd)
        assert item instanceof DiskItem
        DiskItem ditem = (DiskItem) item
        ditem.save(failOnError: true, flush: true)

        then:
        ditem.id != null
        ditem.name == cmd.itemName
        ditem.ownerId == cmd.ownerId
        ditem.eventStartTime == cmd.eventStart
        ditem.eventEndTime == cmd.eventEnd
        ditem.involvedPeople == cmd.involvedPeople
        ditem.involvedPlaces == cmd.involvedPlaces
        ditem.description == cmd.description
        ditem.dateCreated.format('dd/MM/yyyy') == new Date().format('dd/MM/yyyy')
        ditem.dateUpdated.format('dd/MM/yyyy') == ditem.dateCreated.format('dd/MM/yyyy')
        ditem.description == cmd.description
    }
}
