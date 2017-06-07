package p.hh.figrails.controllers

import grails.converters.JSON
import org.springframework.web.multipart.commons.CommonsMultipartFile
import p.hh.figrails.commands.ItemCommand
import p.hh.figrails.domain.Item
import p.hh.figrails.domain.User
import p.hh.figrails.utils.ItemType

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
            cmd = new ItemCommand(itemType: ItemType.getValue(params.itemType))
        }
        render(view: 'openItem', model: [cmd: cmd, readonly: params.boolean("readonly"), message: flash.message])
    }

    def list() {
        List<Item> items = []
        if (params.userId) {
            User user = userService.findUser(params.long("userId"))
            items.addAll(itemService.findAllCreatedItemsByUser(user))

        } else {
            items.addAll(itemService.findAllAccessibleItemsByUser(springSecurityService.currentUser))
        }

        render(view: 'listItem', model: [items: items, currentUser: springSecurityService.currentUser])
    }

    def create(ItemCommand cmd) {
        if(cmd.itemType == ItemType.PHYSICAL) {
            cmd.pictureLocation = itemService.savePicture(request.getFile('picture'))
        }

        User currentUser = springSecurityService.currentUser
        cmd.ownerId = currentUser.id
        cmd.ownerName = currentUser.username
        Item item = itemService.createItem(cmd)
        flash.message = "Creation is successful."
        redirect(controller: 'item', action: 'open', params: [itemId: item.id])
    }

    def update(ItemCommand cmd) {
        if(cmd.itemType == ItemType.PHYSICAL) {
            CommonsMultipartFile file = request.getFile('picture')
            if(file.size) {
                cmd.pictureLocation = itemService.savePicture(file)
            }
        }

        User currentUser = springSecurityService.currentUser
        cmd.ownerId = currentUser.id
        cmd.ownerName = currentUser.username
        Item item = itemService.updateItem(cmd)
        flash.message = "Update is successful."
        redirect(controller: 'item', action: 'open', params: [itemId: item.id])
    }
}
