package p.hh.figrails.services

import grails.test.spock.IntegrationSpec
import p.hh.figrails.domain.Item
import p.hh.figrails.domain.User
import p.hh.figrails.util.FigFactory
import p.hh.finditgrails.services.UserService


class UserServiceIntegrationSpec extends IntegrationSpec {
    UserService userService

    void "test allOwnedItems"() {
        given:
        User user = FigFactory.giveUser()
        Item item1 = FigFactory.giveDiskItem(user)
        item1.involvedPeople = "Abby,Bob,Cathy"
        item1.involvedPlaces = "Utrecht"
        item1.save()

        Item item2 = FigFactory.giveDiskItem(user)
        item2.involvedPeople = "Cathy,Bob,Tom,Max"
        item2.involvedPlaces = "Rotterdam,Amsterdam"
        item2.save()

        when:
        List<Item> ownedItems = userService.allOwnedItems(user)
        List<String> people = userService.allInvolvedPeople(user)
        List<String> places = userService.allInvolvedPlaces(user)

        then:
        ownedItems == [item1, item2]
        people == ["Abby", "Bob", "Cathy", "Max", "Tom"]
        places == ["Amsterdam", "Rotterdam", "Utrecht"]
    }

}
