package p.hh.figrails.controllers

import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityService
import p.hh.figrails.commands.ItemCommand
import p.hh.figrails.domain.Item
import p.hh.figrails.domain.User
import p.hh.finditgrails.services.ItemService
import p.hh.finditgrails.services.UserService

class ItemController {
    def itemService
    def userService
    def springSecurityService

    def open() {
        ItemCommand cmd
        if (params.itemId) {
            Item item = itemService.findById(params.long("itemId"))
            cmd = itemService.mapItemToCommand(item)
        } else {
            cmd = new ItemCommand()
            cmd.itemType = params.itemType
        }
        render(view: 'openItem', model: [cmd: cmd])
    }

    def list() {
        List<Item> items = []
        if (params.userId) {
            User user = userService.findUserById(params.long("userId"))
            items.addAll(itemService.findAllOwnedItemsByUser(user))

        } else {
            items.addAll(itemService.findAllAccessibleItemsByUser(springSecurityService.currentUser))
        }

        render(view: 'listItem', model: [items: items, currentUser: springSecurityService.currentUser])
    }

    def create(ItemCommand cmd) {
        User currentUser = springSecurityService.currentUser
        cmd.ownerId = currentUser.id
        cmd.ownerName = currentUser.username
        Item item = itemService.createItem(cmd)
        render([itemId: item.id] as JSON)
    }

    def update(ItemCommand cmd) {
        User currentUser = springSecurityService.currentUser
        cmd.ownerId = currentUser.id
        cmd.ownerName = currentUser.username
        Item item = itemService.updateItem(cmd)
        render([itemId: item.id] as JSON)
    }
}
