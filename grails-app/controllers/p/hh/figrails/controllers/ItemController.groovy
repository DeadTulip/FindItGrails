package p.hh.figrails.controllers

import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityService
import p.hh.figrails.commands.ItemCommand
import p.hh.figrails.domain.Item
import p.hh.figrails.domain.User
import p.hh.finditgrails.services.ItemService

class ItemController {
    def itemService
    def springSecurityService

    def open() {
        ItemCommand cmd
        if (params.itemId) {
            Item item = itemService.findById(params.long("itemId"))
            cmd = itemService.mapItemToCommand(item)
        } else {
            cmd = new ItemCommand()
        }
        render(view: 'openItem', model: [cmd: cmd])
    }

    def list() {
        List<Item> items = itemService.findAllItemsByUser(springSecurityService.currentUser)

        render(view: 'listItem', model: [items: items])
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
